package com.polytech.codev.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.polytech.codev.model.Profile;
import com.polytech.codev.payload.request.LoginRequest;
import com.polytech.codev.payload.request.SignupRequest;
import com.polytech.codev.payload.response.MessageResponse;
import com.polytech.codev.payload.response.JwtResponse;
import com.polytech.codev.model.User;
import com.polytech.codev.repository.ProfileRepository;
import com.polytech.codev.repository.UserRepository;
import com.polytech.codev.security.jwt.JwtUtils;
import com.polytech.codev.security.services.UserDetailsImpl;
import com.polytech.codev.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        loginRequest.getPassword();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<Profile> profiles = this.profileRepository.getProfileByUser(userDetails.getId())
                .orElseThrow(() -> new EntityNotFoundException());

        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                profiles.get(0).getId(),
                userDetails.getEmail(),
                userDetails.getFirstname(),
                userDetails.getLastname(),
                jwt,
                "Bearer"
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname()
        );
        userRepository.saveAndFlush(user);
        profileRepository.saveAndFlush(new Profile(user));

        LoginRequest request = new LoginRequest(signUpRequest.getEmail(), signUpRequest.getPassword());
        return this.authenticateUser(request);
    }
}
