package io.github.hossensyedriadh.keycloakdemo.service;

import io.github.hossensyedriadh.keycloakdemo.model.LogoutRequest;

public sealed interface LogoutService permits LogoutServiceImpl {
    void logout(LogoutRequest logoutRequest);
}
