package com.loanapp.loanManagementSystem.integrate;

import com.loanapp.loanManagementSystem.dto.user.UserDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class OktaClient {
    public AuthResponse register(UserDto dto, String password, String role) {
        AuthResponse response = new AuthResponse();
        response.setEmail(dto.getEmail());
        response.setRole(role);
        return response;
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