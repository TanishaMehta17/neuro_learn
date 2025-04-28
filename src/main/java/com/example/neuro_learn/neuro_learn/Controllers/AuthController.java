package com.example.neuro_learn.neuro_learn.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.neuro_learn.neuro_learn.Config.JwtRequest.JwtResponse;
import com.example.neuro_learn.neuro_learn.Entities.User;
import com.example.neuro_learn.neuro_learn.Repositories.UserRepo;
import com.example.neuro_learn.neuro_learn.Services.UserService;
import com.example.neuro_learn.neuro_learn.utils.ResourceNotFoundException;
import com.example.neuro_learn.neuro_learn.Config.*;

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest request) {
        System.out.println("\n\n---------------- LOGIN API DEBUGGING ----------------");
        System.out.println("Received Email: " + request.getEmail());
        System.out.println("Received Password (raw): " + request.getPassword());

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            System.out.println("User not found with email: " + request.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        User user = optionalUser.get();

        String storedPassword = user.getPassword();
        System.out.println("Stored Password in DB (encoded or raw): " + storedPassword);

        boolean isPasswordMatched = passwordEncoder.matches(request.getPassword(), storedPassword);

        System.out.println("Encrypting received password and comparing with stored password...");
        System.out.println("Result of passwordEncoder.matches() = " + isPasswordMatched);

        if (!isPasswordMatched) {
            System.out.println("Password does NOT match ❌");
            System.out.println("Received Password (raw): " + request.getPassword());
            System.out.println("Stored Password (encoded or raw): " + storedPassword);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        System.out.println("Password MATCHED ✅");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtUtil.generateToken(userDetails.getUsername());

        System.out.println("Generated JWT Token: " + token);
        System.out.println("-------------------------------------------------------\n\n");

        return ResponseEntity.ok(new JwtResponse(token));
    }

    
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        System.out.println("\n\n---------------- SIGNUP API DEBUGGING ----------------");
        System.out.println("Received Email: " + user.getEmail());
        System.out.println("Received Password (raw): " + user.getPassword());

        // 1. Check if user already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            System.out.println("User already exists with email: " + user.getEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists!");
        }

        // 2. Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        System.out.println("Encoded Password (to be stored): " + encodedPassword);

        // 3. Save the user
        userRepository.save(user);
        System.out.println("User saved successfully!");

        System.out.println("------------------------------------------------------\n\n");
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/TokenisValid")
    public ResponseEntity<Boolean> isTokenValid(@RequestHeader("token") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.ok(false);
        }

        boolean isValid = jwtUtil.validateToken(token);
        if (!isValid)
            return ResponseEntity.ok(false);

        String email = jwtUtil.extractUsername(token);
        Optional<User> user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user.isPresent());
    }

    @GetMapping("/")
    public ResponseEntity<?> getUser(@RequestHeader("token") String token) {
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        String email = jwtUtil.extractUsername(token);
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.ok(user.get());
    }
}
