package io.github.hossensyedriadh.keycloakdemo.service;

import io.github.hossensyedriadh.keycloakdemo.model.LogoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public final class LogoutServiceImpl implements LogoutService {
    private final RestTemplate restTemplate;

    @Autowired
    public LogoutServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${logout-endpoint}")
    private String logoutEndpoint;

    @Value("${client-id}")
    private String clientId;

    @Value("${client-secret}")
    private String clientSecret;

    @Override
    public void logout(LogoutRequest logoutRequest) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("refresh_token", logoutRequest.getRefreshToken());
        request.add("client_id", this.clientId);
        request.add("client_secret", this.clientSecret);

        HttpHeaders headers = new HttpHeaders(request);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(request, headers);

        this.restTemplate.postForLocation(this.logoutEndpoint, entity);
    }
}
