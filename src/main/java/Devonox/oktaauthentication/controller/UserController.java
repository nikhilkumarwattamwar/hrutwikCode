package Devonox.oktaauthentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


    @PreAuthorize("hasRole(@roles.USER)")
    @GetMapping("/api/user/dashboard")
    public String userDashboard() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return "User Dashboard Accessed "+username;
    }

}
