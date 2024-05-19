package com.vozhov.caesarapi.service;

import com.vozhov.caesarapi.payload.request.auth.AuthenticationRequest;
import com.vozhov.caesarapi.payload.request.auth.RegisterRequest;
import com.vozhov.caesarapi.payload.response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
