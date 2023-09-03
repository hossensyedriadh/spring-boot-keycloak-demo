package io.github.hossensyedriadh.keycloakdemo.controller;

import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TestController {
    @PreAuthorize("hasAuthority('ROLE_admin')")
    @GetMapping("/admin")
    public ResponseEntity<?> testAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "KeyCloak IAM Integration successful");
        jsonObject.addProperty("principal", jwt.getClaim("preferred_username").toString());
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_user')")
    @GetMapping("/user")
    public ResponseEntity<?> testUser() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "KeyCloak IAM Integration successful");
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
}
