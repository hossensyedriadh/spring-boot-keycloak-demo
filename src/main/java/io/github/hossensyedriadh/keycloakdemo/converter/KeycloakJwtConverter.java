package io.github.hossensyedriadh.keycloakdemo.converter;

import com.nimbusds.oauth2.sdk.util.MapUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class KeycloakJwtConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(@NonNull Jwt source) {
        Map<String, Object> realmAccess = source.getClaimAsMap("realm_access");
        Map<String, List<String>> resourceAccess = (Map<String, List<String>>) source.getClaimAsMap("resource_access").get("test-client");

        if (MapUtils.isNotEmpty(realmAccess) || MapUtils.isNotEmpty(resourceAccess)) {
            List<String> realmRoles = new ArrayList<>(Collections.singletonList(realmAccess.get("roles").toString()));
            realmRoles.addAll(resourceAccess.get("roles"));

            return realmRoles.stream().map(rn -> new SimpleGrantedAuthority("ROLE_" + rn))
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
