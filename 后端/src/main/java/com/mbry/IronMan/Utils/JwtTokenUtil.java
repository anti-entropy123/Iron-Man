package com.mbry.IronMan.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -1301625591608910415L;

    private static final String CLAIM_KEY_USERNAME = "userId";
    private static final String CLAIM_KEY_AUTH = "auth";
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String[] getIdAndAuthFromToken(String token) {
        String id, auth;
        try {
            final Claims claims = getClaimsFromToken(token);
            id = claims.get(CLAIM_KEY_USERNAME).toString();
            auth = claims.get(CLAIM_KEY_AUTH).toString();
        } catch (Exception e) {
            id = null;
            auth = null;
        }
        return new String[]{id, auth};
    }

    public String getOpenIdFromToken(String token){
        try {
            final Claims claims = getClaimsFromToken(token);
            return claims.get(CLAIM_KEY_USERNAME).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String generateToken(String id, String auth) {
        if(auth == null){
            auth = "common";
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, id);
        claims.put(CLAIM_KEY_AUTH, auth);
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}

