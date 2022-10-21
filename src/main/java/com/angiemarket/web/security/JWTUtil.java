package com.angiemarket.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String KEY = "ange1";

    public String generateToken(UserDetails userDetails){
            return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256, KEY).compact(); //crea el web token y permite retornalos a traves de los user details

    }

    public boolean validateToken(String token, UserDetails  userDetails){
        return userDetails.getUsername().equals(extraUsername(token)) && !isTokenExpired(token);
    }

    public String extraUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    //Claims son objetos dentro del JWT
    private Claims getClaims(String token){
        //cuando verifique que la firma sea correcta, va a obtener lo objetos de jwt por separado
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
