package com.portifolyo.mesleki1.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.portifolyo.mesleki1.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtils {


    private String secret = "MySuperSecretKey";

    private int expirationTime = 3600000;

    private Algorithm algorithm = Algorithm.HMAC256(secret);



    public boolean ValidateToken(String token) {
       DecodedJWT jwt = JWT.require(algorithm).build().verify(token);

       if(jwt.getExpiresAt().after(Date.from(Instant.now()))) {
           return true;
       }
       else return false;
    }

    public String extractEmail(String token) {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        return jwt.getSubject();
    }

    public String refreshToken(String token) {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        return JWT.create().withSubject(jwt.getSubject())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    public String crateToken(String email) {
      return  JWT.create().withClaim("email",email).withExpiresAt(new Date(System.currentTimeMillis()+expirationTime))
                .sign(algorithm);
    }


}
