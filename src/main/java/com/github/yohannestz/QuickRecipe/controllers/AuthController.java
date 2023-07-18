package com.github.yohannestz.QuickRecipe.controllers;

import com.github.yohannestz.QuickRecipe.config.JwtService;
import com.github.yohannestz.QuickRecipe.domain.AuthRequest;
import com.github.yohannestz.QuickRecipe.domain.AuthResponse;
import com.github.yohannestz.QuickRecipe.domain.User;
import com.github.yohannestz.QuickRecipe.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signUp")
    public ResponseEntity<AuthResponse> register(@RequestBody User request) {
        User user = new User(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getDisplayName(), request.getPhotoUrl(),
                request.getThemeColor(), request.getThemeName());
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User foundUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(foundUser);

        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }
}
