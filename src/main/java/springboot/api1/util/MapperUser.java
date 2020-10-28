package springboot.api1.util;

import springboot.api1.persistence.dtos.UserApiDto;
import springboot.api1.persistence.entity.UserApi;

public class MapperUser {
    public static UserApiDto UserToDto(UserApi userApi) {

        return new UserApiDto(userApi.getMail(), userApi.getUsername(), userApi.getRol(),userApi.getPassword());

    }
}
