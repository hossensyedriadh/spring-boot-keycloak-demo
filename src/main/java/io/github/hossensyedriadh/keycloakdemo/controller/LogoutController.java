package io.github.hossensyedriadh.keycloakdemo.controller;

import io.github.hossensyedriadh.keycloakdemo.model.LogoutRequest;
import io.github.hossensyedriadh.keycloakdemo.service.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logout", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LogoutController {
    private final LogoutService logoutService;

    @Autowired
    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> logout(@RequestBody LogoutRequest logoutRequest) {
        this.logoutService.logout(logoutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
