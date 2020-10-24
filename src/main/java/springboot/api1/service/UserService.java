package springboot.api1.service;

import springboot.api1.persistence.dtos.UserApiDto;

public interface UserService {
    public UserApiDto findByUsername(String username);
}
