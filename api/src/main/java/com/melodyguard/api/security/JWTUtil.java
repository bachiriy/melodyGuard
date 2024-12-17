package com.melodyguard.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.melodyguard.api.controller.UserController;
import com.melodyguard.api.entity.Role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JWTUtil {

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String email, Role role) throws IllegalArgumentException, JWTCreationException {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 7); // token is valid for 7 days
        Date expirationDate = cal.getTime();

        logger.info("\n\n" + 
            date  + " | " +  expirationDate
        + "\n\n");

        return JWT.create()
                .withSubject("User Details")
                .withClaim("role", role.toString())
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withExpiresAt(expirationDate)
                .withIssuer("melodyguard")
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("melodyguard")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}