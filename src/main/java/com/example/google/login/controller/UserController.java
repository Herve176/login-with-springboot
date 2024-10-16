package com.example.google.login.controller;




import com.example.google.login.model.User;
import com.example.google.login.service.GoogleVerificationService;
import com.example.google.login.service.JwtService;
import com.example.google.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private GoogleVerificationService googleVerificationService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody User user){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getEmail());
        else
            return "Login Failed";

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/auth/google")
    public ResponseEntity<String> verifyUser(@RequestBody String idToken) {
        boolean isVerified = googleVerificationService.verifyGoogleToken(idToken);
        if (isVerified) {
            User userFromToken = googleVerificationService.extractUserFromToken(idToken);
            if (userFromToken != null) {
                User user = service.getUserByEmail(userFromToken.getEmail());
                if (user == null) {
                    user = service.saveUser(userFromToken);
                }
                return ResponseEntity.ok(jwtService.generateToken(user.getEmail()));
            } else {
                return ResponseEntity.status(401).body("Invalid Google token");
            }
        } else {
            return ResponseEntity.status(401).body("Invalid Google token");
        }
    }
}