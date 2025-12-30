package Devonox.oktaauthentication.controller;

import Devonox.oktaauthentication.dto.JwtResponse;
import Devonox.oktaauthentication.dto.LoginRequest;
import Devonox.oktaauthentication.dto.RegisterRequest;
import Devonox.oktaauthentication.enums.Role;
import Devonox.oktaauthentication.security.JwtUtil;
import Devonox.oktaauthentication.service.impl.OktaAuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.DisplayName;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private OktaAuthServiceImpl oktaService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    private static final String API_TOKEN = "dummy-token";

    @BeforeEach
    void injectApiToken() throws Exception {
        Field field = AuthController.class.getDeclaredField("apiToken");
        field.setAccessible(true);
        field.set(authController, API_TOKEN);
    }


    @Test
    @DisplayName("login(): should return true when username and password are authenticated and responds with JWT token")
    void login_success_shouldReturnJwt() {

        LoginRequest request = new LoginRequest();
        request.setUsername("user@test.com");
        request.setPassword("password");

        when(oktaService.authenticate("user@test.com", "password"))
                .thenReturn(Mono.just(true));

        when(oktaService.getUserFullName("user@test.com", API_TOKEN))
                .thenReturn(Mono.just("Test User"));

        when(oktaService.getUserGroups("user@test.com", API_TOKEN))
                .thenReturn(Mono.just(List.of("USER")));

        when(jwtUtil.generateToken("user@test.com", List.of("USER"), "Test User"))
                .thenReturn("jwt-token");

        ResponseEntity<?> response =
                authController.login(request).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JwtResponse body = (JwtResponse) response.getBody();
        assertNotNull(body);
        assertEquals("jwt-token", body.getToken());
    }

    @Test
    @DisplayName("login(): should return false when username and password are invalid and responds with UNAUTHORIZED 401")
    void login_invalidCredentials_shouldReturn401() {

        LoginRequest request = new LoginRequest();
        request.setUsername("user@test.com");
        request.setPassword("wrong");

        when(oktaService.authenticate("user@test.com", "wrong"))
                .thenReturn(Mono.just(false));

        ResponseEntity<?> response =
                authController.login(request).block();

        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password", response.getBody());
    }


    @Test
    @DisplayName("register(): should create user and assign role successfully")
    void register_success_shouldAssignRole() {

        RegisterRequest request = new RegisterRequest();
        request.setEmail("user@test.com");
        request.setPassword("Password@123");
        request.setRole(Role.USER);

        when(oktaService.createUser(request, API_TOKEN))
                .thenReturn(Mono.just("okta-user-id"));

        when(oktaService.assignRoleToUser("okta-user-id", Role.USER, API_TOKEN))
                .thenReturn(Mono.empty());

        ResponseEntity<String> response =
                authController.register(request).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                "User created and role assigned successfully",
                response.getBody()
        );
    }

    @Test
    @DisplayName("register(): should return fallback message when role assignment fails")
    void register_roleAssignmentFails_shouldReturnFallbackMessage() {

        RegisterRequest request = new RegisterRequest();
        request.setEmail("admin@test.com");
        request.setPassword("Password@123");
        request.setRole(Role.ADMIN);

        when(oktaService.createUser(request, API_TOKEN))
                .thenReturn(Mono.just("okta-user-id"));

        when(oktaService.assignRoleToUser("okta-user-id", Role.ADMIN, API_TOKEN))
                .thenReturn(Mono.error(new RuntimeException("Okta error")));

        ResponseEntity<String> response =
                authController.register(request).block();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(
                "User created, but role assignment failed. Please contact admin.",
                response.getBody()
        );
    }
}
