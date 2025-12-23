package Devonox.oktaauthentication.service;

import Devonox.oktaauthentication.dto.RegisterRequest;
import Devonox.oktaauthentication.dto.Role;
import Devonox.oktaauthentication.security.JwtUtil;
import Devonox.oktaauthentication.service.impl.OktaAuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OktaAuthServiceTest {

        private OktaAuthServiceImpl oktaAuthService;
        private ExchangeFunction exchangeFunction;

        private static final String API_TOKEN = "test-token";

        @BeforeEach
        void setup() {

            exchangeFunction = mock(ExchangeFunction.class);

            WebClient.Builder builder = WebClient.builder()
                    .exchangeFunction(exchangeFunction);

            oktaAuthService = new OktaAuthServiceImpl(
                    builder,
                    "https://okta.test.com",
                    "/authn",
                    "/users/{login}/groups",
                    "/users/{login}",
                    "/users",
                    "/groups/{groupId}/users/{userId}",
                    "LOAN_GROUP",
                    "USER_GROUP",
                    "ADMIN_GROUP",new JwtUtil("kl",5655)
            );
        }


    @Test
    void authenticate_success_shouldReturnTrue() {

        ClientResponse response = ClientResponse.create(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("{\"status\":\"SUCCESS\"}")
                .build();

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.just(response));

        Boolean result = oktaAuthService
                .authenticate("user@test.com", "password")
                .block();

        assertTrue(result);
    }

    @Test
    void authenticate_error_shouldReturnFalse() {

        WebClientResponseException ex =
                WebClientResponseException.create(
                        401,
                        "Unauthorized",
                        HttpHeaders.EMPTY,
                        null,
                        null
                );

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.error(ex));

        Boolean result =
                oktaAuthService.authenticate("user@test.com", "wrong")
                        .block();

        assertFalse(result);
    }



    @Test
    void getUserGroups_shouldReturnGroupNames() {

        ClientResponse response = ClientResponse.create(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("""
                        [
                          {"profile":{"name":"USER"}},
                          {"profile":{"name":"ADMIN"}}
                        ]
                        """)
                .build();

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.just(response));

        List<String> groups =
                oktaAuthService.getUserGroups("user@test.com", API_TOKEN).block();

        assertNotNull(groups);
        assertEquals(2, groups.size());
        assertTrue(groups.contains("USER"));
        assertTrue(groups.contains("ADMIN"));
    }

    @Test
    void getUserGroups_error_shouldThrowException() {

        WebClientResponseException ex =
                WebClientResponseException.create(
                        401,
                        "Unauthorized",
                        HttpHeaders.EMPTY,
                        null,
                        null
                );

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.error(ex));

        assertThrows(
                Exception.class,
                () -> oktaAuthService
                        .getUserGroups("user@test.com", API_TOKEN)
                        .block()
        );
    }


    @Test
    void getUserFullName_shouldReturnFullName() {

        ClientResponse response = ClientResponse.create(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("""
                {
                  "profile": {
                    "firstName": "Hrutwik",
                    "lastName": "Kale"
                  }
                }
                """)
                .build();

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.just(response));

        String name =
                oktaAuthService.getUserFullName("user@test.com", API_TOKEN)
                        .block();

        assertEquals("Hrutwik Kale", name);
    }

    @Test
    void getUserFullName_error_shouldThrowException() {

        WebClientResponseException ex =
                WebClientResponseException.create(
                        404,
                        "Not Found",
                        HttpHeaders.EMPTY,
                        null,
                        null
                );

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.error(ex));

        assertThrows(
                Exception.class,
                () -> oktaAuthService
                        .getUserFullName("user@test.com", API_TOKEN)
                        .block()
        );
    }


    @Test
    void createUser_shouldReturnUserId() {

        RegisterRequest request = new RegisterRequest();
        request.setName("Hrutwik Kale");
        request.setEmail("user@test.com");
        request.setPassword("Password@123");

        ClientResponse response = ClientResponse.create(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("{\"id\":\"okta-user-id\"}")
                .build();

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.just(response));

        String userId =
                oktaAuthService.createUser(request, API_TOKEN).block();

        assertEquals("okta-user-id", userId);
    }

    @Test
    void createUser_errorStatus_shouldThrowRuntimeException() {

        RegisterRequest request = new RegisterRequest();
        request.setName("Hrutwik Kale");
        request.setEmail("user@test.com");
        request.setPassword("Password@123");

        ClientResponse response = ClientResponse.create(HttpStatus.BAD_REQUEST)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body("Okta validation failed")
                .build();

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.just(response));

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> oktaAuthService
                        .createUser(request, API_TOKEN)
                        .block()
        );

        assertTrue(ex.getMessage().contains("Okta error"));
    }


    @Test
    void assignRoleToUser_shouldCompleteSuccessfully() {

        ClientResponse response = ClientResponse.create(HttpStatus.OK).build();

        when(exchangeFunction.exchange(any()))
                .thenReturn(Mono.just(response));

        Void result =
                oktaAuthService
                        .assignRoleToUser("user-id", Role.USER, API_TOKEN)
                        .block();

        assertNull(result);
    }


    @Test
    void getGroupIdForRole_shouldReturnCorrectGroupIds() {

        assertEquals("USER_GROUP",
                oktaAuthService.getGroupIdForRole(Role.USER));

        assertEquals("ADMIN_GROUP",
                oktaAuthService.getGroupIdForRole(Role.ADMIN));

        assertEquals("LOAN_GROUP",
                oktaAuthService.getGroupIdForRole(Role.LOAN_OFFICER));
    }
}
