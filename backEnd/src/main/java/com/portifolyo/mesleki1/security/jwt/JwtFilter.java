package com.portifolyo.mesleki1.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portifolyo.mesleki1.dtos.LoginDto;
import com.portifolyo.mesleki1.entity.User;
import com.portifolyo.mesleki1.services.UserServices;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final UserServices userServices;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public JwtFilter(AuthenticationManager authenticationManager, UserServices userServices, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userServices = userServices;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().contains("login")) {
            JwtUtils jwtUtils = new JwtUtils();
            ObjectMapper o = new ObjectMapper();
            LoginDto loginDto = o.readValue(request.getInputStream().readAllBytes(), LoginDto.class);
            User u = this.userServices.findByEmail(loginDto.email());

            if(u.getEmail().equals(loginDto.email()) && bCryptPasswordEncoder.matches(loginDto.password(),u.getPassword())) {
               String token = jwtUtils.crateToken(loginDto.email());
                response.setHeader(AUTHORIZATION,String.format("Bearer %s",token ));
                UserDetails user = userServices.loadUserByUsername(u.getEmail());
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u.getEmail(),"",user.getAuthorities()));
                filterChain.doFilter(request,response);

            }

            else response.sendError(403,"Kullanıcı bulunamadı");
        }
        String auth = request.getHeader(AUTHORIZATION);
        boolean validateToken = false;
        if(!Objects.isNull(auth) && !Strings.isBlank(auth) && !Strings.isEmpty(auth)) {
            if(auth.contains("bearer ")) {
                String token = auth.substring(7);
                JwtUtils jwt = new JwtUtils();
               validateToken = jwt.ValidateToken(token);
            }

        }

        if (validateToken || request.getServletPath().contains("/public")) {
            filterChain.doFilter(request,response);
        }
        else response.sendError(404);


    }
}
