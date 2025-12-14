package com.security.springSecurity.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class jwtUtil {

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Value("${jwt.token.validity}")
    private Long tokenValidity;

    @Autowired
    UserDetailsService userDetailsService;

    private SecretKey getKey(){
        byte[] arr = Decoders.BASE64.decode(secretKey);
        if(arr.length < 32)
            throw new IllegalArgumentException("key has size less than 32");

        return Keys.hmacShaKeyFor(arr);
    }

    public String generateToken(String userName){
        return Jwts
                .builder()
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //methods to implement

    //extract allClaims
    //extract Subject
    //extract expiration
    //validate token
    //get user

    public Claims getClaims(final String token){
        return Jwts
                .parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    public String extractSubject(final String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(final String token){
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }

    public UserDetails userDetails(final String token){
        String username = extractSubject(token);
        return userDetailsService.loadUserByUsername(username);
    }

    public boolean validateToken(final String token, UserDetails userDetails){
        Date expiry = extractExpiration(token);
        String subject = extractSubject(token);

        if(subject == userDetails.getUsername()){
            return expiry.before(new Date());
        }
        else{
            throw new UsernameNotFoundException("Wrong Username");
        }
    }

}
