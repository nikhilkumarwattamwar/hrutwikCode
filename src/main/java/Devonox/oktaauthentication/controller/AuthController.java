package Devonox.oktaauthentication.controller;



import Devonox.oktaauthentication.dto.JwtResponse;
import Devonox.oktaauthentication.dto.LoginRequest;
import Devonox.oktaauthentication.dto.RegisterRequest;
import Devonox.oktaauthentication.security.JwtUtil;
import Devonox.oktaauthentication.service.OktaAuthService;
import Devonox.oktaauthentication.service.impl.OktaAuthServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final OktaAuthService oktaService;
    private final JwtUtil jwtUtil;

    @Value("${okta.api.token}")
    private String apiToken;

    public AuthController(OktaAuthServiceImpl oktaService, JwtUtil jwtUtil) {
        this.oktaService = oktaService;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody LoginRequest request) {

        return oktaService.authenticate(request.getUsername(), request.getPassword())
                .flatMap(authenticated -> {

                    if (!authenticated) {
                        return Mono.just(
                                ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                        .body("Invalid username or password")
                        );
                    }

                    Mono<String> nameMono =
                            oktaService.getUserFullName(
                                    request.getUsername(),
                                    apiToken
                            );

                    Mono<List<String>> rolesMono =
                            oktaService.getUserGroups(
                                    request.getUsername(),
                                    apiToken
                            );

                    return Mono.zip(nameMono, rolesMono)
                            .map(tuple -> {

                                String fullName = tuple.getT1();
                                List<String> roles = tuple.getT2();

                                String jwt = jwtUtil.generateToken(
                                        request.getUsername(),
                                        roles,
                                        fullName

                                );

                                return ResponseEntity.ok(new JwtResponse(jwt));
                            });
                });
    }




    @PostMapping("/register")
    public Mono<ResponseEntity<String>> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return oktaService.createUser(request, apiToken)
                .flatMap(userId ->
                        oktaService.assignRoleToUser(
                                        userId,
                                        request.getRole(),
                                        apiToken
                                )
                                .thenReturn(
                                        ResponseEntity.ok(
                                                "User created and role assigned successfully"
                                        )
                                )
                                .onErrorResume(ex ->
                                        Mono.just(
                                                ResponseEntity.ok(
                                                        "User created, but role assignment failed. Please contact admin."
                                                )
                                        )
                                )
                );
    }


}
