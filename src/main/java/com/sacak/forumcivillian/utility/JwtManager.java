package com.sacak.forumcivillian.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sacak.forumcivillian.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class JwtManager {
    private String secretKey = "secretKey";
    private String issuer = "ForumCivillian";
    private final Long exDate = 1000L*60*60; //60mins for token expiration

    private static final String VERIFICATION_EMAIL_KEY = "VERIFICATION_EMAIL";

    Algorithm algorithm = Algorithm.HMAC512(secretKey);

    public String createUserToken(Long authId) {
        Date createdDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + exDate);

        String token = JWT.create()
                .withAudience()
                .withIssuer(issuer)
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                .withClaim("authId", authId)
                .withClaim("key", "IKProje")
                .withClaim("role","USER")
                .sign(algorithm);
        return token;
    }

    public String createAdminToken(Long adminId, String email) {
        Date createdDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + exDate);

        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                .withClaim("adminId",adminId)
                .withClaim("email",email)
                .withClaim("key","IKProje")
                .withClaim("role","ADMIN")
                .sign(algorithm);
    }

    public String createVerificationToken(User user) { //for Mail Verification
        return JWT.create()
                .withClaim(VERIFICATION_EMAIL_KEY,user.getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + (exDate)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public Optional<Long> validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(Objects.isNull(decodedJWT))
                return Optional.empty();

            String role = decodedJWT.getClaim("role").asString();
            if(role.equals("ADMIN")){
                return Optional.of(decodedJWT.getClaim("adminId").asLong());
            }
            else{
                return Optional.of(decodedJWT.getClaim("authId").asLong());
            }

        }
        catch (Exception e) {
            return Optional.empty();
        }
    }
}
