package dio.dio.spring.security.jwt.security;

import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JWTObject jwtObject) {
        if (!prefix.equalsIgnoreCase(HEADER_AUTHORIZATION)) {
            throw new IllegalArgumentException("Invalid token prefix.");
        }
        String token = Jwts.builder().setSubject(jwtObject.getSubject()).setIssuedAt(jwtObject.getIssuedAt())
                .setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles())).signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return prefix + " " + token;
    }

    public static JWTObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        if (!prefix.equalsIgnoreCase(HEADER_AUTHORIZATION)) {
            throw new IllegalArgumentException("Invalid token prefix.");
        }
        JWTObject object = new JWTObject();
        token = token.substring(prefix.length() + 1); // Remove the prefix and space
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            object.setSubject(claims.getSubject());
            object.setExpiration(claims.getExpiration());
            object.setIssuedAt(claims.getIssuedAt());
            object.setRoles((List<String>) claims.get(ROLES_AUTHORITIES)); // Cast to List<String>
        } catch (ClassCastException e) {
            throw new RuntimeException("Failed to parse roles from JWT claims.", e);
        }
        return object;
    }

    private static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(role -> !role.startsWith("ROLE_") ? "ROLE_" + role : role).collect(Collectors.toList());
    }
}