package org.freeride.shootbug.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TokenUtil {
    private static final String SECRET = "dde7fa6b-4591-4496-ae2c-cf7c3f84e81e";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long EXPIRE_DURATION = TimeUnit.DAYS.toMillis(7);

    public static String generateJwt(UserDetails userDetails) {
        Map<String, Object> claims = Map.of("roles", userDetails.getAuthorities());
        return Jwts.builder().setIssuer("shoot-bug")
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setExpiration(generateExpire())
                .setIssuedAt(new Date())
                .signWith(KEY).compact();
    }

    private static Date generateExpire() {
        return new Date(System.currentTimeMillis() + EXPIRE_DURATION);
    }

    public static String extractUsernameFromJwt(String jwt) {
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(jwt).getBody().getSubject();
    }

    public static boolean isJwtExpired(String jwt) {
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(jwt).getBody().getExpiration().before(new Date());
    }
}
