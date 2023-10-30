package com.example.reservas.configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@ConfigurationProperties(prefix = "token")
public class TokenConverterProperties
{
    private String resourceId;
    private String principalAttribute;

    public String getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(String resourceId)
    {
        this.resourceId = resourceId;
    }

    public Optional<String> getPrincipalAttribute()
    {
        return Optional.ofNullable(principalAttribute);
    }

    public void setPrincipalAttribute(String principalAttribute)
    {
        this.principalAttribute = principalAttribute;
    }

}