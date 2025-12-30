package Devonox.oktaauthentication.service;


import Devonox.oktaauthentication.dto.RegisterRequest;
import Devonox.oktaauthentication.enums.Role;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OktaAuthService {

    /**
     * Authenticates a user using Okta credentials.
     *
     * @param username user login identifier
     * @param password user secret
     * @return authentication result
     */
    Mono<Boolean> authenticate(String username, String password);

    /**
     * Returns Okta group names assigned to the user.
     *
     * @param userLogin user login identifier
     * @param apiToken Okta API token
     * @return list of group names
     */
    Mono<List<String>> getUserGroups(String userLogin, String apiToken);

    /**
     * Returns the full name of the user from okta.
     *
     * @param login user login identifier
     * @param apiToken Okta API token
     * @return user full name
     */
    Mono<String> getUserFullName(String login, String apiToken);

    /**
     * creates a new user in Okta.
     *
     * @param request registration details
     * @param apiToken Okta API token
     * @return Okta user identifier
     */
    Mono<String> createUser(RegisterRequest request, String apiToken);

    /**
     * Assigns an appliction role to a user.
     *
     * @param userId Okta user identifier
     * @param role application role
     * @param apiToken Okta API token
     */
    Mono<Void> assignRoleToUser(String userId, Role role, String apiToken);

    /**
     * get group id for given role.
     *
     * @param role application role
     * @return Okta group id
     */
    String getGroupIdForRole(Role role);

}

