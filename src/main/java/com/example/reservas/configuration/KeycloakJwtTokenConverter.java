package com.example.reservas.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class KeycloakJwtTokenConverter implements Converter<Jwt, AbstractAuthenticationToken >{

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;
    private final TokenConverterProperties properties;

    public KeycloakJwtTokenConverter
            (JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter,
             TokenConverterProperties tokenConverterProperties) {
        this.jwtGrantedAuthoritiesConverter = jwtGrantedAuthoritiesConverter;
        this.properties = tokenConverterProperties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                extractResourceRoles(jwt).stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        return properties.getPrincipalAttribute()
                .map(jwt::getClaimAsString)
                .orElse(jwt.getClaimAsString(JwtClaimNames.SUB));
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("realm_access");
        // imprimir en un log el contenido de resourceAccess, con el SLF4J
        //log.info("resourceAccess: " + resourceAccess);
        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(properties.getResourceId());
        //log.info("resource: " + resource);

        if (resourceAccess != null) {
            Collection<String> roles = (Collection<String>) resourceAccess.get("roles");
            if (roles != null) {
                //log.info("Roles: " + roles);
                return roles.stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toSet());
            }
        }

        return Collections.emptySet();
    }

}