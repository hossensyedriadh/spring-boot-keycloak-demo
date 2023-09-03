package io.github.hossensyedriadh.keycloakdemo.service;

import io.github.hossensyedriadh.keycloakdemo.model.IntrospectionRequest;
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
public final class MiscServiceImpl implements MiscService {
    private final RestTemplate restTemplate;

    @Autowired
    public MiscServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${introspection-endpoint}")
    private String introspectionEndpoint;

    @Value("${client-id}")
    private String clientId;

    @Value("${client-secret}")
    private String clientSecret;

    @Override
    public String introspect(IntrospectionRequest introspectionRequest) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("client_id", this.clientId);
        request.add("client_secret", this.clientSecret);
        request.add("token", introspectionRequest.getToken());

        HttpHeaders headers = new HttpHeaders(request);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(request, headers);

        return this.restTemplate.postForObject(this.introspectionEndpoint, entity, String.class);
    }
}
