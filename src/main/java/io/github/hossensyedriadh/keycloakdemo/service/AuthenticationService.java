package io.github.hossensyedriadh.keycloakdemo.service;

import io.github.hossensyedriadh.keycloakdemo.model.AccessTokenRenewalRequest;
import io.github.hossensyedriadh.keycloakdemo.model.AccessTokenRenewalResponse;
import io.github.hossensyedriadh.keycloakdemo.model.AuthenticationRequest;
import io.github.hossensyedriadh.keycloakdemo.model.AuthenticationResponse;

public sealed interface AuthenticationService permits AuthenticationServiceImpl {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AccessTokenRenewalResponse renewToken(AccessTokenRenewalRequest renewalRequest);
}
