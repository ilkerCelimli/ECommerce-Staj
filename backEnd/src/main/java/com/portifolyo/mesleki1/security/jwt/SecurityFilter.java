package com.portifolyo.mesleki1.security.jwt;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getServletPath().contains("public")) {
            String token = request.getHeader(AUTHORIZATION);

                if(Strings.isBlank(token) || Strings.isEmpty(token) || Objects.isNull(token)) {
                    response.sendError(403,"This is private api");
                }
                        filterChain.doFilter(request,response);

        }
        filterChain.doFilter(request,response);
    }
}
