package com.example.reservas.service.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@FeignClient(name = "keycloak", url = "http://192.168.1.211:8080/realms/parking/")
public interface KeycloakService {

    @PostMapping(value = "/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    Map<String,?> getToken(@RequestBody Map<String, ?> data);
}
