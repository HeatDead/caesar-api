package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.config.JwtService;
import com.vozhov.caesarapi.entity.RoleEntity;
import com.vozhov.caesarapi.entity.UserEntity;
import com.vozhov.caesarapi.payload.request.auth.AuthenticationRequest;
import com.vozhov.caesarapi.payload.request.auth.RegisterRequest;
import com.vozhov.caesarapi.payload.response.AuthenticationResponse;
import com.vozhov.caesarapi.repository.RoleRepository;
import com.vozhov.caesarapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        Optional<UserEntity> ue = userRepository.findByUsername(request.getUsername());
        if (ue.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<RoleEntity> ro = roleRepository.findById(request.getRole());
        if(ro.isPresent()) {
            UserEntity user = UserEntity.builder()
                    .name(request.getName())
                    .surname(request.getSurname())
                    .patronymic(request.getPatronymic())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(ro.get())
                    .build();
            userRepository.save(user);
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        AuthenticationResponse resp = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
