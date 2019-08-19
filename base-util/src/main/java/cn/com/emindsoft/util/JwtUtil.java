package cn.com.emindsoft.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {

    private static final Long  TOKEN_EXPIRES = 1000 * 60 * 60 * 24 * 30L;

    /**
     * 创建token
     * @param username
     * @param password
     * @return
     */
    public static String createToken(String username, String password) {
        Algorithm algorithm = Algorithm.HMAC384(password);
        return JWT.create().withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES))
                .sign(algorithm);

    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("username").asString();

    }

    /**
     * 验证token
     * @param token
     */
    public static void verifyToken(String password, String token) {
        Algorithm algorithm = Algorithm.HMAC384(password);
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
    }

}
