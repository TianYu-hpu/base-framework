package cn.com.zenmaster.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author tianyu
 */
@Slf4j
public class JwtUtil {

    /**
     * token有效期
     */
    private static final Long TOKEN_EXPIRES = 1000 * 60 * 60 * 24 * 30L;

    /**
     * 创建token
     * @param userId
     * @param secret
     * @return
     */
    public static String createToken(String userId, String secret) {
        Algorithm algorithm = Algorithm.HMAC384(secret);
        return JWT.create().withClaim("id", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES))
                .sign(algorithm);

    }

    /**
     * 从token中获取用户id
     * @param token
     * @return
     */
    public static String getUserIdFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("id").asString();

    }

    /**
     * 验证token
     * @param secret
     * @param token
     */
    public static void verifyToken(String secret, String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC384(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.info("token 验证失败");
        }
    }

}
