package io.github.hossensyedriadh.keycloakdemo.configuration.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().setConnectTimeout(Duration.of(1, ChronoUnit.MINUTES))
                .setReadTimeout(Duration.of(1, ChronoUnit.MINUTES)).build();
    }
}
