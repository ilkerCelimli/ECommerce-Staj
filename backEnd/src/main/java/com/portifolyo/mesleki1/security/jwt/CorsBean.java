package com.portifolyo.mesleki1.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsBean {

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration c = new CorsConfiguration();
        c.setAllowCredentials(true);
        c.setAllowedHeaders(List.of("*"));
        c.setAllowedMethods(List.of("GET","PUT","POST","DELETE","OPTIONS"));
        List<String> allowedHeaders = new ArrayList<>();
        allowedHeaders.add("Content-type");
        allowedHeaders.add("x-auth-token");
        allowedHeaders.add("x-xsrf-token");
        allowedHeaders.add("Content-Disposition");
        allowedHeaders.add("Origin");
        allowedHeaders.add("Access-Control-Request-Method");
        allowedHeaders.add("Access-Control-Request-Headers");
        allowedHeaders.add("X-PINGOTHER");
        allowedHeaders.add("Cache-Control");
        allowedHeaders.add("Pragma");
        allowedHeaders.add("Expires");
        c.setAllowedHeaders(allowedHeaders);
        c.setExposedHeaders(List.of("x-auth-token","x-xsrf-token","xsrf-token","Origin"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",c);
        return source;
    }
}
