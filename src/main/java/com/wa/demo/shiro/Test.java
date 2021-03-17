package com.wa.demo.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

/**
 * Test
 * 2021-03-09 17:54
 *
 * @author wuao
 */
public class Test {

    public static void main(String[] args) throws Exception {
        String username = "wuao";
        String password = "123456";

        Algorithm algorithm = Algorithm.HMAC256(password);
        String token1 = JWT.create()
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 2))
                .sign(algorithm);

        Thread.sleep(1000);

        JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();

        verifier.verify(token1+"1");

        //System.out.println(JWT.decode(token1).getClaims());

        /*String token2 = JWT.create()
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(algorithm);*/

        //DecodedJWT decodedJWT = JWT.decode(token);
        //System.out.println(token1);
        //System.out.println(token2);
    }
}
