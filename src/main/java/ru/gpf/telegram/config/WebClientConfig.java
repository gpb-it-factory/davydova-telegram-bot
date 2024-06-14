package ru.gpf.telegram.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder()
                .errorHandler(new WebClientErrorHandler())
                .build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.setConnectTimeout(Duration.ofMillis(10000));
        restTemplateBuilder.setReadTimeout(Duration.ofMillis(10000));
        return restTemplateBuilder;
    }
}
