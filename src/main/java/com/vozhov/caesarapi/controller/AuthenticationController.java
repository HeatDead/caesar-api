package com.vozhov.caesarapi.controller;

import com.vozhov.caesarapi.payload.request.auth.AuthenticationRequest;
import com.vozhov.caesarapi.payload.request.auth.RegisterRequest;
import com.vozhov.caesarapi.payload.response.AuthenticationResponse;
import com.vozhov.caesarapi.service.AuthenticationService;
import com.vozhov.caesarapi.service.AuthenticationServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
         return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
