package Devonox.oktaauthentication.service;


import Devonox.oktaauthentication.dto.JwtResponse;
import Devonox.oktaauthentication.dto.LoginRequest;
import Devonox.oktaauthentication.dto.RegisterRequest;
import Devonox.oktaauthentication.dto.Role;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface OktaAuthService {

    Mono<Boolean> authenticate(String username, String password);

    Mono<List<String>> getUserGroups(String userLogin, String apiToken);

    Mono<String> getUserFullName(String login, String apiToken);

    Mono<String> createUser(RegisterRequest request, String apiToken);

    Mono<Void> assignRoleToUser(String userId, Role role, String apiToken);

    String getGroupIdForRole(Role role);

}

