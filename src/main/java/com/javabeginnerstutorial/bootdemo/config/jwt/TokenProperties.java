package com.javabeginnerstutorial.bootdemo.config.jwt;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
@ConditionalOnProperty(name="appsecurity.method", havingValue = "JWT")
public class TokenProperties {

	private String secret;
	private long validityInMs;
}
