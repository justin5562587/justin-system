package com.justin.system.entity.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.models.User;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String SUBJECT = "justinSystemSubject";

    private static final String SECRET = "justinSystemSecretKey";

    private static final long EXPIRE = 1000 * 60 * 60 * 2; // 暂时一小时

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public static String generateToken(User user) {
        try {
            return JWT.create()
                    .withSubject(SUBJECT)
                    .withClaim(SystemConstant.USER_NAME, user.getUsername())
                    .withClaim(SystemConstant.USER_EMAIL, user.getEmail())
                    .withClaim(SystemConstant.USER_ID, user.getId())
                    .withIssuer("auth0")
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            return exception.toString();
        }
    }

    public static Map<String, Claim> validateToken(String token, User user) {
        try {
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withClaim(SystemConstant.USER_NAME, user.getUsername())
                    .withClaim(SystemConstant.USER_EMAIL, user.getEmail())
                    .withClaim(SystemConstant.USER_ID, user.getId())
                    .withIssuer("auth0")
                    .build();
            jwtVerifier.verify(token);
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaims();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException(exception.toString());
        }
    }

    // 获取token中无须secret也可以解密的信息
    public static Claim getClaim(String token, String claimName) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim(claimName);
        } catch (JWTDecodeException exception) {
            throw new RuntimeException(exception.getCause());
        }
    }
}
