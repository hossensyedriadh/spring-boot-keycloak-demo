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
public final class IntrospectionRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 3935956491950675296L;

    private String token;
}
