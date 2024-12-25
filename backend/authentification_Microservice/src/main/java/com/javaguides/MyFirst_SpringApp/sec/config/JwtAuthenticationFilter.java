package com.javaguides.MyFirst_SpringApp.sec.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        // Capture the request path
        String requestPath = request.getRequestURI();
        System.out.println("Request URI: " + requestPath);  // Log the request path

        // Skip JWT validation for public auth endpoints
        if (requestPath.startsWith("/api/auth")) {
            System.out.println("Skipping JWT validation for: " + requestPath);  // Log skipped requests
            filterChain.doFilter(request, response);  // Skip the filter for public endpoints
            return;
        }

        // Retrieve the Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // If the Authorization header is missing or doesn't start with "Bearer ", skip the request
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("No JWT token found or invalid token: " + authHeader);  // Log missing/invalid tokens
            filterChain.doFilter(request, response);
            return;
        }

        // Extract the JWT token from the Authorization header
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        System.out.println("Extracted user email: " + userEmail);  // Log the extracted email

        // If the user email is not null and no authentication exists in the context, proceed with authentication
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("User authenticated: " + userEmail);  // Log successful authentication
            } else {
                System.out.println("Invalid JWT token for user: " + userEmail);  // Log invalid token
            }
        }

        filterChain.doFilter(request, response);
    }
}

