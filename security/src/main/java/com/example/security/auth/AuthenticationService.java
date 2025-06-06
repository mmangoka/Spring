package com.example.security.auth;


import com.example.security.config.JwtService;
import com.example.security.user.Role;
import com.example.security.user.User;
import com.example.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtservice;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user =
                User.builder().
                firstname(request.getFirstname()).
                lastname(request.getLastname()).
                userEmail(request.getUserEmail()).
                password(passwordEncoder.encode(request.getPassword())).
                role(Role.USER)
                 .build();

        repository.save(user);
        var jwtToken = jwtservice.generateToken(user);


        return AuthenticationResponse.builder().
                token(jwtToken).
                build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()
                )
        );

        var user = repository.findByUserEmail(request.getEmail()).
                orElseThrow();

        var jwtToken = jwtservice.generateToken(user);

        return AuthenticationResponse.builder().
                token(jwtToken).
                build();
    }
}
