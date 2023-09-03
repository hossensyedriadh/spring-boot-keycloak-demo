package io.github.hossensyedriadh.keycloakdemo.service;

import io.github.hossensyedriadh.keycloakdemo.model.AuthenticationRequest;
import io.github.hossensyedriadh.keycloakdemo.model.AuthenticationResponse;

public sealed interface AuthenticationService permits AuthenticationServiceImpl {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
