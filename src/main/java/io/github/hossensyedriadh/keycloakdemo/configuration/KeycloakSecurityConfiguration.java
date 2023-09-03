package io.github.hossensyedriadh.keycloakdemo.configuration;

import io.github.hossensyedriadh.keycloakdemo.converter.KeycloakJwtConverter;
import io.github.hossensyedriadh.keycloakdemo.entrypoint.BearerAuthenticationEntryPoint;
import io.github.hossensyedriadh.keycloakdemo.handler.ResourceAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class KeycloakSecurityConfiguration {
    private final KeycloakJwtConverter keycloakJwtConverter;
    private final BearerAuthenticationEntryPoint bearerAuthenticationEntryPoint;
    private final ResourceAccessDeniedHandler resourceAccessDeniedHandler;

    @Autowired
    public KeycloakSecurityConfiguration(KeycloakJwtConverter keycloakJwtConverter, BearerAuthenticationEntryPoint bearerAuthenticationEntryPoint,
                                         ResourceAccessDeniedHandler resourceAccessDeniedHandler) {
        this.keycloakJwtConverter = keycloakJwtConverter;
        this.bearerAuthenticationEntryPoint = bearerAuthenticationEntryPoint;
        this.resourceAccessDeniedHandler = resourceAccessDeniedHandler;
    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(CsrfConfigurer::disable).cors(CorsConfigurer::disable).sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(this.resourceAccessDeniedHandler)
                        .authenticationEntryPoint(this.bearerAuthenticationEntryPoint)).authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.requestMatchers("/authentication/**")
                        .permitAll().anyRequest().authenticated()).oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                        httpSecurityOAuth2ResourceServerConfigurer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(this.jwtAuthenticationConverter())));

        return httpSecurity.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(this.keycloakJwtConverter);
        return jwtAuthenticationConverter;
    }
}
