package Devonox.oktaauthentication.service.impl;


import Devonox.oktaauthentication.dto.RegisterRequest;
import Devonox.oktaauthentication.enums.Role;
import Devonox.oktaauthentication.security.JwtUtil;
import Devonox.oktaauthentication.service.OktaAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;



/**
 * Service responsible for authenticating users and managing user data in Okta.
 *
 * This service handles authentication, user creation,group-based role assignment, and user profile retrieval.
 */
@Service
public class OktaAuthServiceImpl implements OktaAuthService {

    private static final Logger log = LoggerFactory.getLogger(OktaAuthServiceImpl.class);

    private final WebClient webClient;


    private final String authnPath;


    private final JwtUtil jwtUtil;

    private final String userByLoginPath;


    private  final String userGroupsPath;


    private  final String createUserPath;

   private final String assignUserToGroupPath;



    private final String adminGroupId;

    private final String userGroupId;


    private final String loanOfficerGroupId;



    public OktaAuthServiceImpl(WebClient.Builder builder, @Value("${okta.org.url}") String oktaUrl,
                               @Value("${okta.authn.path}")String authnPath,
                               @Value("${okta.users.groups.path}") String userGroupsPath,
                               @Value("${okta.users.by-login.path}") String userByLoginPath,
                               @Value("${okta.users.create.path}") String createUserPath,
                               @Value("${okta.groups.assign-user.path}")String assignUserToGroupPath,
                               @Value("${okta.group.loan-officer}") String loanOfficerGroupId,
                               @Value("${okta.group.user}")String userGroupId,
                               @Value("${okta.group.admin}")String adminGroupId,
                               JwtUtil jwtUtil)

    {
        this.authnPath = authnPath;
        this.userGroupsPath = userGroupsPath;
        this.userByLoginPath = userByLoginPath;
        this.createUserPath = createUserPath;
        this.assignUserToGroupPath = assignUserToGroupPath;
        this.adminGroupId =adminGroupId;
        this.userGroupId = userGroupId;
        this.loanOfficerGroupId = loanOfficerGroupId;
        this.jwtUtil=jwtUtil;
        this.webClient = builder
                .baseUrl(oktaUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
        log.info("OktaService initilized with base url: "+oktaUrl);
    }




    /**
     * Authentictes a user using Okta credentials.
     *
     * @param username user login identifier
     * @param password user secret
     * @return authentication result
     */
    public Mono<Boolean> authenticate(String username, String password) {
        log.info("Authenticate User with UseName : " + username);

        Map<String, String> body = Map.of("username", username, "password", password );

        return webClient.post()
                .uri(authnPath)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .map(resp -> {
                  boolean success=  "SUCCESS".equals(resp.get("status"));
                  log.info("Authentication result : "+username+" "+success);
                  return success;

                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    log.error("Okta Authentication failed : "+ username+"   "+ex.getResponseBodyAsString());
                    return Mono.just(false);
                });
    }

    /**
     *
     * get the groups assign to the user from okta.
     * @param apiToken Okta API token
     * @return list of group names
     */
    public Mono<List<String>> getUserGroups(String userLogin, String apiToken) {

        log.info("Fetching groups for users : "+ userLogin);

        return webClient.get()

                .uri(userGroupsPath, userLogin)
                .header(HttpHeaders.AUTHORIZATION, "SSWS " + apiToken)
                .retrieve()
                .bodyToFlux(Map.class)
                .map(group -> (Map<String, Object>) group.get("profile"))
                .map(profile -> profile.get("name").toString())
                .collectList()
                .doOnSuccess(groups ->
                        log.info("User  belongs  groups: ", userLogin, groups)
                );
    }



    /**
     *
     * get the Full name assign to the user from okta.
     * @param login (username ) user identifier
     * @param apiToken Okta API token
     * @return Full name  of user from okta
     */
    public Mono<String> getUserFullName(String login, String apiToken) {

        log.debug("Fetching fll name for user : ", login);
        return webClient.get()

                .uri(userByLoginPath, login)
                .header(HttpHeaders.AUTHORIZATION, "SSWS " + apiToken)
                .retrieve()
                .bodyToMono(Map.class)
                .map(resp -> {
                    Map<String, Object> profile = (Map<String, Object>) resp.get("profile");
                    String firstName = profile.get("firstName").toString();
                    String lastName = profile.get("lastName").toString();

                    log.info("Fetch Full Name : " +firstName +" "+lastName +" : "+login);
                    return firstName + " " + lastName;
                });

    }

    /**
     *
     * Create the new user in okta .
     * @param request get the user data
     * @param apiToken Okta API token
     * @return message that user created successfully
     */
    public Mono<String> createUser(RegisterRequest request, String apiToken) {

        log.info("Creating Okta suer with email : "+ request.getEmail() +" name : "+request.getName());

        String[] names = request.getName().split(" ", 2);
        String firstName = names[0];
        String lastName = names.length > 1 ? names[1] : "";

        Map<String, Object> body = Map.of(

                "profile", Map.of("firstName", firstName, "lastName", lastName,
                        "email", request.getEmail(),
                        "login", request.getEmail()
                ),

                "credentials", Map.of(

                        "password", Map.of("value", request.getPassword()
                        )
                )
        );

        return webClient.post()
                .uri(createUserPath)
                .header(HttpHeaders.AUTHORIZATION, "SSWS " + apiToken)
                .bodyValue(body)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(err -> {
                                    log.error("Okta user creation failed: ", err);
                                    return Mono.error(
                                            new RuntimeException("Okta error: " + err)
                                    );
                                })
                )
                .bodyToMono(Map.class)
                .map(resp -> {
                    String userId = resp.get("id").toString();
                    log.info("Okta user created successfully. UserId: ", userId);
                    return userId;
                });

    }


    /**
     *
     * Assign role to the user within okta.
     * @param userId  okta user identifier
     * @param apiToken Okta API token
     * @param role user role
     * @return Full name  of user from okta
     */
    public Mono<Void> assignRoleToUser(String userId, Role role, String apiToken) {

        String groupId = getGroupIdForRole(role);

        log.info("Assigning role to user ", role, groupId, userId);


        return webClient.put()

                .uri(assignUserToGroupPath, groupId, userId)
                .header(HttpHeaders.AUTHORIZATION, "SSWS " + apiToken)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(s->log.info("Role assign to Use Successfully "+role +" "+userId));

    }




    /**
     * Resolves the Okta group identifier for a given role.
     *
     * @param role application role
     * @return Okta group identifier
     */
    public String getGroupIdForRole(Role role) {
        return switch (role) {
            case USER -> userGroupId;
            case LOAN_OFFICER -> loanOfficerGroupId;
            case ADMIN -> adminGroupId;
        };
    }





}
