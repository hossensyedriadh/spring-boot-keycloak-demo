package io.github.hossensyedriadh.keycloakdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public final class AccessTokenRenewalRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -8932530638158939365L;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
