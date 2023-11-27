package com.example.reservas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class GlobalSecurityConfiguration
{
    private final KeycloakJwtTokenConverter keycloakJwtTokenConverter;

    public GlobalSecurityConfiguration(TokenConverterProperties properties)
    {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        this.keycloakJwtTokenConverter =
                new KeycloakJwtTokenConverter(jwtGrantedAuthoritiesConverter, properties);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( authorizeHttpRequests -> {
            authorizeHttpRequests
                    .requestMatchers("/api/v1/floors/all").hasAnyRole("ADMIN", "USER", "EMPLOYEE")
                    .requestMatchers("/api/v1/vehicles/all").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/v1/vehicles/{id}").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/v1/vehicles/save").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/v1/vehicles/save_both").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/v1/vehicles/delete/{id}").hasAnyRole("ADMIN", "USER")
                    .anyRequest().authenticated();
        });
        http.oauth2ResourceServer( (oauth2) -> {
            oauth2.jwt( (jwt) -> {
                jwt.jwtAuthenticationConverter(keycloakJwtTokenConverter);
            });
        });

        http.sessionManagement((session) -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.cors(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(java.util.Arrays.asList("*"));
        //configuration.setAllowedOrigins(java.util.Arrays.asList( "http://localhost:4200", "http://host.docker.internal:4200"));
        configuration.setAllowedMethods(java.util.Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(java.util.Arrays.asList("*"));
        configuration.setExposedHeaders(java.util.Arrays.asList("Access-Control-Allow-Origin"));
        configuration.setAllowCredentials(true);
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }



}