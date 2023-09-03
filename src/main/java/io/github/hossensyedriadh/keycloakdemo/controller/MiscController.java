package io.github.hossensyedriadh.keycloakdemo.controller;

import io.github.hossensyedriadh.keycloakdemo.model.IntrospectionRequest;
import io.github.hossensyedriadh.keycloakdemo.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/misc", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MiscController {
    private final MiscService miscService;

    @Autowired
    public MiscController(MiscService miscService) {
        this.miscService = miscService;
    }

    @PostMapping(value = "/introspect", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> introspect(@RequestBody IntrospectionRequest introspectionRequest) {
        String response = this.miscService.introspect(introspectionRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
