package com.example.google.login.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // You can retrieve the authenticated user's details here, if needed
        // For example, you can retrieve the OAuth2User
        // OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Define the target URL you want to redirect to
        String targetUrl = "www.google.com";  // You can customize this URL as needed

        // Perform the redirect
        response.sendRedirect(targetUrl);
    }
}
