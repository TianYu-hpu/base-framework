package cn.com.zenmaster.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class JwtUtilTest {

    @Test
    public void generateToken() {
        Algorithm algorithm = Algorithm.HMAC384("zenmaster@123");
        String token = JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + 1000000)).withClaim("username", "password").sign(algorithm);
        log.info("token:" + token);
    }

    @Test
    public void validateToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJleHAiOjE1NjYyMDEyNjgsInVzZXJuYW1lIjoicGFzc3dvcmQifQ.ydsFfgvLtZrI3b-UuYVzfAO2G1TqBELn5b43gpr8Lin4wfvIZJzguIDZUWXqIgXR";
        Algorithm algorithm = Algorithm.HMAC384("zenmaster@123");
        JWTVerifier verifier = JWT.require(algorithm).build();
        verifier.verify(token);
        log.info("token:" + token);
    }

    @Test
    public void getUsername() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJleHAiOjE1NjYyMDEyNjgsInVzZXJuYW1lIjoicGFzc3dvcmQifQ.ydsFfgvLtZrI3b-UuYVzfAO2G1TqBELn5b43gpr8Lin4wfvIZJzguIDZUWXqIgXR";
        Algorithm algorithm = Algorithm.HMAC384("zenmaster@123");
        DecodedJWT decodedJWT = JWT.decode(token);
        String username = decodedJWT.getClaim("username").asString();
        log.info("username:{}", username);
    }

}
