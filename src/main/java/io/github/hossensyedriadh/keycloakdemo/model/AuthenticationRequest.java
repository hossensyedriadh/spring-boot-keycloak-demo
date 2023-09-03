package io.github.hossensyedriadh.keycloakdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class AuthenticationRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 6016523311887926223L;

    private String username;

    private String password;
}
