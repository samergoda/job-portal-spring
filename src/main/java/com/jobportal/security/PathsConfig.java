package com.jobportal.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PathsConfig {

    @Bean(name = "publicPaths")
    public List<String> publicPaths() {
        return List.of(
                "/api/contacts/public",
                "/api/{v}/auth/login/public",
                "/api/{v}/companies/public",
                "/api/{v}/auth/register/public",
                "/api/{v}/csrf-token/public",
                "/api/{v}/logging/public",
                "/api/swagger-ui.html",
                "/swagger-ui/**",
                "/api/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }

    @Bean(name = "securedPaths")
    public List<String> securedPaths() {
        return List.of(
                "/api/**"
        );
    }

    @Bean(name = "adminPaths")
    public List<String> adminPaths() {
        return List.of(
                "/api/{v}/contacts/admin",
                "/api/{v}/contacts/sort/admin",
                "/api/{v}/contacts/page/admin",
                "/api/{v}/contacts/${id}/status/admin"
        );
    }

}