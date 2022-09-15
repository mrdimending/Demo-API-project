package com.jumpee.commerce.utils;

import com.jumpee.commerce.exception.AccessDeniedException;
import com.jumpee.commerce.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static String secret = "This_is_secret";
    private static long expiryDuration = 60 * 60;

    public String generateJwt(User user)
    {

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);

        Claims claims = Jwts.claims()
                .setIssuer(Integer.toString(user.getId()))
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);


//        claims.put("type", user.getUserType());
//        claims.put("name", user.getName());
//        claims.put("emailId", user.getEmailId());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public void verify(String authorization) throws Exception 
    {

        try 
        {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
        }  
        catch(Exception e) 
        {
            throw new AccessDeniedException();
        }

    }
}