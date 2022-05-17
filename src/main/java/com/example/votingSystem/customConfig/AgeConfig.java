package com.example.votingSystem.customConfig;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@RequiredArgsConstructor
@Getter
@Setter
public class AgeConfig {
    private Integer age;
}
