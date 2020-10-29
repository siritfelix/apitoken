package springboot.api1.service;

import java.util.List;

import com.auth0.jwt.interfaces.DecodedJWT;

import springboot.api1.persistence.dtos.UserApiDto;

public interface JwtService {

    public String createToken(UserApiDto user);

    public boolean isBearer(String authorization);

    public String user(String authorization);

    DecodedJWT verify(String authorization) ;

    public List<String> roles(String authorization);

}
