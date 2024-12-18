package com.sacak.forumcivillian.config;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.sacak.forumcivillian.entity.Admin;
import com.sacak.forumcivillian.entity.User;
import com.sacak.forumcivillian.repository.AdminRepository;
import com.sacak.forumcivillian.repository.UserRepository;
import com.sacak.forumcivillian.utility.JwtManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtManager jwtManager;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            try {
                Optional<Long> idOpt = jwtManager.validateToken(token);
                if (idOpt.isPresent()) {
                    Long id = idOpt.get();

                    Optional<Admin> adminOptional = adminRepository.findById(id);
                    if (adminOptional.isPresent()) {
                        Admin admin = adminOptional.get();
                        setAuthentication(admin, "ADMIN");
                    }
                    else {
                        Optional<User> userOptional = userRepository.findById(id);
                        if (userOptional.isPresent()) {
                            User user = userOptional.get();
                            if(user.getIsVerified()){
                                setAuthentication(user, user.getUserRole().toString());
                            }

                        }
                    }


                }
            } catch (JWTDecodeException e) {
                logger.error("JwtDecode Exception", e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Object principal, String authority) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                principal, null, List.of(new SimpleGrantedAuthority(authority))
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}