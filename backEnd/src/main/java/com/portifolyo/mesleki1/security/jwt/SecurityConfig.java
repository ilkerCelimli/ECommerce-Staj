package com.portifolyo.mesleki1.security.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final SecurityFilter securityFilter;
    public SecurityConfig(JwtFilter jwtFilter, SecurityFilter securityFilter) {
        this.jwtFilter = jwtFilter;
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("**/public/**").permitAll();
        http.addFilterAfter(new SecurityFilter(), WebAsyncManagerIntegrationFilter.class);
        http.addFilterAt(jwtFilter, SecurityFilter.class);
        return http.build();
    }

}
