package springboot.api1.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.stereotype.Service;

import springboot.api1.persistence.dtos.UserApiDto;

@Service
public class JwtServiceImpl implements JwtService {

    public static final String BEARER = "Bearer ";
    private static final String USER = "USER";
    private static final String ROLES = "ROLES";
    private static final String ISSUER = "isa2jes";
    private static final int EXPIRES_IN_MILLISECOND = 60000;
    private static final String SECRET = "firmaSecreta";

    @Override
    public String createToken(UserApiDto user) {
        String username = user.getUsername();
        List<String> ROLES = user.getRol();
        return JWT.create().withIssuer(ISSUER).withIssuedAt(new Date()).withNotBefore(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES_IN_MILLISECOND)).withClaim(USER, username)
                .withArrayClaim("ROLES", ROLES.toArray(new String[0])).sign(Algorithm.HMAC256(SECRET));
    }

    @Override
    public boolean isBearer(String authorization) {
        return authorization != null && authorization.startsWith(BEARER) && authorization.split("\\.").length == 3;

    }

    @Override
    public String user(String authorization) {
        return this.verify(authorization).getClaim(USER).asString();
    }

    @Override
    public DecodedJWT verify(String authorization) {
        if (!this.isBearer(authorization)) {
            return null;
        }
        try {
            return JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build()
                    .verify(authorization.substring(BEARER.length()));
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public List<String> roles(String authorization) {
        if (this.verify(authorization) != null)
            return Arrays.asList(this.verify(authorization).getClaim(ROLES).asArray(String.class));
        else
            return null;

    }

}
