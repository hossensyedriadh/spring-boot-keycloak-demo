package io.github.hossensyedriadh.keycloakdemo.service;

import io.github.hossensyedriadh.keycloakdemo.model.AccessTokenRenewalRequest;
import io.github.hossensyedriadh.keycloakdemo.model.AccessTokenRenewalResponse;
import io.github.hossensyedriadh.keycloakdemo.model.AuthenticationRequest;
import io.github.hossensyedriadh.keycloakdemo.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public final class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${token-endpoint}")
    private String tokenEndpoint;

    @Value("${client-id}")
    private String clientId;

    @Value("${client-secret}")
    private String clientSecret;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("username", authenticationRequest.getUsername());
        request.add("password", authenticationRequest.getPassword());
        request.add("grant_type", "password");
        request.add("client_id", this.clientId);
        request.add("client_secret", this.clientSecret);

        HttpHeaders httpHeaders = new HttpHeaders(request);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(request, httpHeaders);

        return restTemplate.postForEntity(tokenEndpoint, entity, AuthenticationResponse.class).getBody();
    }

    @Override
    public AccessTokenRenewalResponse renewToken(AccessTokenRenewalRequest renewalRequest) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("grant_type", "refresh_token");
        request.add("client_id", this.clientId);
        request.add("client_secret", this.clientSecret);
        request.add("refresh_token", renewalRequest.getRefreshToken());

        HttpHeaders headers = new HttpHeaders(request);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForEntity(this.tokenEndpoint, entity, AccessTokenRenewalResponse.class).getBody();
    }
}
