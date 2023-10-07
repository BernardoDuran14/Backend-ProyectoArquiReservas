package com.example.reservas.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.Serializable;
import java.util.Optional;

@Slf4j
@Configuration
@EnableJpaAuditing
public class InjectConfiguration {

    @Bean
    public AuditorAware<String> auditorAware1() {
        log.info("auditorAware");
        return () -> {
            return  Optional.of("ADMIN");
        };
    }

}