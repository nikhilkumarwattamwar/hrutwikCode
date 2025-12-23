package Devonox.oktaauthentication.controller;

import Devonox.oktaauthentication.service.OktaAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;


@RestController
public class UserController {


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/api/user/dashboard")
    public String userDashboard() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return "User Dashboard Accessed "+username;
    }

}
