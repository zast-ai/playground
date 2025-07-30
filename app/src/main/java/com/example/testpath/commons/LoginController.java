package com.example.testpath.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        private String message;
        private boolean success;

        public LoginResponse(String message, boolean success) {
            this.message = message;
            this.success = success;
        }

        public String getMessage() { return message; }
        public boolean isSuccess() { return success; }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            return ResponseEntity.ok()
                .body(new LoginResponse("login success", true));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body(new LoginResponse("username or password error", false));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new LoginResponse("Logout successful", true));
    }
} 