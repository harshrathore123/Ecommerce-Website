package com.authservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Service;

import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Base64;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }

    private SecretKey getSigningKey(){
        //Base64 Secret Decode
        //Decoded bytes se SecretKeySpec object banao
        //HmacSHA256 algorithm use karo

        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return new SecretKeySpec(keyBytes,"HmacSHA256");
    }

    public String generateToken(Long userId, String email){
        Instant now = Instant.now();

        // Token Expiration calculate karna
        Instant expirationTime = now.plusMillis(expiration);

        // Jwt Claims create karna
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(String.valueOf(userId))
                .claim("email",email)
                .issuedAt(now)
                .expiresAt(expirationTime)
                .build();

        // Jwt Encode aur Sign karna

        // Token return karna
    }
}
