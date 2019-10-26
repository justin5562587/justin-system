package com.justin.system.entity.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.justin.system.models.User;

import java.util.Date;

public class JwtUtil {

    private static final String SUBJECT = "justinSystemSubject";

    private static final String SECRET = "justinSystemSecretKey";

    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    public static String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withSubject(SUBJECT)
                    .withClaim("username", user.getUsername())
                    .withClaim("email", user.getEmail())
                    .withClaim("id", user.getId())
                    .withIssuer("auth0")
                    .withExpiresAt(new Date(EXPIRE))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            return exception.toString();
        }
    }

    public static boolean validateToken(String token, User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withClaim("username", user.getUsername())
                    .withClaim("email", user.getEmail())
                    .withClaim("id", user.getId())
                    .withIssuer("auth0")
                    .build();
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}
