package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow all origins, headers, and methods
        config.addAllowedOrigin("*");  // In production, replace with specific origins
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // Allow credentials (if needed)
        config.setAllowCredentials(true);

        // Register the configuration
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}