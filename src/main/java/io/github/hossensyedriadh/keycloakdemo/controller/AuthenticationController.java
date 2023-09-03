package io.github.hossensyedriadh.keycloakdemo.controller;

import io.github.hossensyedriadh.keycloakdemo.model.AccessTokenRenewalRequest;
import io.github.hossensyedriadh.keycloakdemo.model.AuthenticationRequest;
import io.github.hossensyedriadh.keycloakdemo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(this.authenticationService.authenticate(authenticationRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/renew", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> renewAccessToken(@RequestBody AccessTokenRenewalRequest accessTokenRenewalRequest) {
        return new ResponseEntity<>(this.authenticationService.renewToken(accessTokenRenewalRequest), HttpStatus.OK);
    }
}
