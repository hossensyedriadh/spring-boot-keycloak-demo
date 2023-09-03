package io.github.hossensyedriadh.keycloakdemo.service;

import io.github.hossensyedriadh.keycloakdemo.model.IntrospectionRequest;

public interface MiscService {
    String introspect(IntrospectionRequest request);
}
