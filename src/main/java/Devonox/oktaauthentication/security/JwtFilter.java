//package Devonox.oktaauthentication.security;
//
//
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.io.IOException;
//import java.util.List;
//
//public class JwtFilter implements Filter {
//
//    private final JwtUtil jwtUtil;
//
//    public JwtFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    public void doFilter(
//            ServletRequest request,
//            ServletResponse response,
//            FilterChain chain
//    ) throws IOException, ServletException {
//
//        HttpServletRequest http = (HttpServletRequest) request;
//        String auth = http.getHeader("Authorization");
//
//        if (auth != null && auth.startsWith("Bearer ")) {
//
//            Claims claims = jwtUtil.parseToken(auth.substring(7));
//
//            List<String> roles = claims.get("roles", List.class);
//
//            var authorities = roles.stream()
//                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
//                    .toList();
//
//            SecurityContextHolder.getContext().setAuthentication(
//                    new UsernamePasswordAuthenticationToken(
//                            claims.getSubject(),
//                            null,
//                            authorities
//                    )
//            );
//        }
//
//        chain.doFilter(request, response);
//    }
//}



package Devonox.oktaauthentication.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

//        String auth = request.getHeader("Authorization");
//
//        if (auth != null && auth.startsWith("Bearer ")) {
//
//            Claims claims = jwtUtil.parseToken(auth.substring(7));
//
//            List<String> roles = claims.get("roles", List.class);
//
//            var authorities = roles.stream()
//                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
//                    .toList();
//
//            System.out.println("JWT ROLES = " + roles);
//            System.out.println("AUTHORITIES = " + authorities);
//
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(
//                            claims.getSubject(),
//                            null,
//                            authorities
//                    );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.debug(
//                    "JWT authenticated user='', roles= :",
//                    claims.getSubject(),
//                    roles
//            );
//        }
//
//        chain.doFilter(request, response);
//    }

        String auth = request.getHeader("Authorization");

        if (auth != null && auth.startsWith("Bearer ")) {

            try {
                String token = auth.substring(7);

                var claims = jwtUtil.parseToken(token);

                List<String> roles = claims.get("roles", List.class);

                var authorities = roles.stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                        .toList();

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                claims.getSubject(),
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                log.debug(
                        "JWT authenticated user='', roles= :",
                        claims.getSubject(),
                        roles
                );

            } catch (Exception ex) {

                log.warn(
                        "JWT authentication failed for request  : ",
                        request.getRequestURI(),
                        ex.getMessage()
                );

                SecurityContextHolder.clearContext();
            }
        }

        chain.doFilter(request, response);
    }
}
