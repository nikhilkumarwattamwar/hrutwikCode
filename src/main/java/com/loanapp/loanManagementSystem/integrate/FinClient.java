package com.loanapp.loanManagementSystem.integrate;

import com.loanapp.loanManagementSystem.dto.loan.LoginRequestDto;
import com.loanapp.loanManagementSystem.dto.loan.LoginResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FinClient {

    private final WebClient webClient;
    private String registerEndpoint;
    private String loginEndpoint;

    public FinClient(WebClient.Builder builder,
                     @Value("${fin.service.base-url}")String baseUrl,
                     @Value("${fin.service.auth.register-endpoint}")String registerEndpoint,
                     @Value("${fin.service.auth.login-endpoint}")String loginEndpoint) {

        this.webClient = builder
                .baseUrl(baseUrl)
                .build();
        this.loginEndpoint=loginEndpoint;
        this.registerEndpoint=registerEndpoint;
    }

    public Mono<String> register(AuthRequest request) {
        return webClient
                .post()
                .uri(registerEndpoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class);

    }

    public Mono<LoginResponseDto> login(LoginRequestDto request) {
        return webClient
                .post()
                .uri(loginEndpoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(LoginResponseDto.class);
    }

    public static class AuthRequest {
        private String name;
        private String email;
        private String password;
        private String role;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    public static class AuthResponse {
        private String token;
        private String email;
        private String role;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
