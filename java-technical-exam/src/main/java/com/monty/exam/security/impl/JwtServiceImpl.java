package com.monty.exam.security.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.monty.exam.configuration.ApplicationProperties;
import com.monty.exam.core.model.User;
import com.monty.exam.security.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
	
	private static final Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);

    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 1 day

    @Autowired
    private  ApplicationProperties applicationProperties;
    
    public String generateToken(User user) {
    	log.info("generater token for user with email {}",user.getEmail());
    	return Jwts.builder()
            .subject(user.getEmail())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(getSigningKey())
            .compact();
    }

    public String extractUsername(String token) {
    	log.info("extracting  user Email from token");

        return Jwts.parser()
            .setSigningKey(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
    	log.info("checking if token expired");
    	
        return Jwts.parser()
            .setSigningKey(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration()
            .before(new Date());
    }

    private SecretKey getSigningKey() {
        String secretKey = applicationProperties.secretKey;
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
