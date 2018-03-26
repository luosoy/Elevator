package com.luosoy.common.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;


public class TokenHelper {

    @Value("${jwt.secret}")
    public String SECRET;
    @Value("${jwt.tokenHeader}")
    public String tokenHeader;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            if(token.indexOf(tokenHeader) >= 0){
                //token的字符是Bearer tokenString
                final Claims claims = this.getAllClaimsFromToken(token.substring(tokenHeader.length()).trim());
                username = claims.getSubject();
            }else {
                username = null;
            }
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}