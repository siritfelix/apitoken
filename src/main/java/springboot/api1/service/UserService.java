package springboot.api1.service;

import java.util.List;

import springboot.api1.persistence.dtos.UserApiDto;
import springboot.api1.persistence.entity.UserApi;

public interface UserService {

    public UserApiDto findByUsername(String username);

    public List<UserApiDto> findAllUsers();

    public UserApiDto CreateUser(UserApi userApi);
}
