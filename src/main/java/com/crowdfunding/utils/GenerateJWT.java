package com.crowdfunding.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GenerateJWT {

    public static String createToken(String user ){

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg", "HS256")
                .setId(user)
                .setIssuedAt(now)
                .setSubject("M4k4anbakso")
                .setIssuer("B4mB4ng")
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
//                .setExpiration(new Date(System.currentTimeMillis() +  40 * 1000))
//                .signWith(SignatureAlgorithm.HS256, "4man53lamatLanc4r8arok4h");
                .signWith(SignatureAlgorithm.HS512, "4man53lamatLanc4r8arok4h");

        return builder.compact();

    }


    public static Claims validateToken (String Token){

        Claims claims = null;

        claims = Jwts.parser().setSigningKey("4man53lamatLanc4r8arok4h")
                .parseClaimsJws(Token)
                .getBody();

        return claims;

    }

}
