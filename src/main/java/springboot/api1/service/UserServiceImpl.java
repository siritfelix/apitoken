package springboot.api1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;
import springboot.api1.persistence.dtos.UserApiDto;
import springboot.api1.persistence.entity.UserApi;
import springboot.api1.persistence.repository.UserRespository;
import springboot.api1.util.MapperUser;

@Log4j2
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    public BCryptPasswordEncoder encoder;
    @Autowired
    private UserRespository userRespository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApi user = userRespository.findByUsername(username);
        if (user == null) {
            log.info("Usuario no existe");
            return null;
        }
        List<GrantedAuthority> authorities = user.getRol().stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserApiDto findByUsername(String username) {
        UserApi user = userRespository.findByUsername(username);
        if (user != null)
            return new UserApiDto(user.getMail(), user.getUsername(), user.getRol(), user.getPassword());
        else
            return null;

    }

    @Override
    public List<UserApiDto> findAllUsers() {
        List<UserApiDto> userApiDtos = new ArrayList<>();
        userRespository.findAll().forEach(u -> userApiDtos.add(MapperUser.UserToDto(u)));
        return userApiDtos;
    }

    @Override
    public UserApiDto CreateUser(UserApi userApi) {

        try {
            if (userRespository.findByUsername(userApi.getUsername()) == null) {
                userApi.setPassword(encoder.encode(userApi.getPassword()));
                userRespository.save(userApi);
            } else
                return null;
            return MapperUser.UserToDto(userApi);
        } catch (Exception e) {
            return null;
        }

    }

}
