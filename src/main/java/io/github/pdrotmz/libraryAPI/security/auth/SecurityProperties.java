package io.github.pdrotmz.libraryAPI.security.auth;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.api.security.secret")
@Getter
@Setter
public class SecurityProperties {
    private String key;
}
