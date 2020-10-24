package springboot.api1.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springboot.api1.persistence.dtos.UserApiDto;
import springboot.api1.persistence.entity.UserApi;
import springboot.api1.persistence.repository.UserRespository;
@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApi user = userRespository.findById(email).get();
        List<GrantedAuthority> authorities = user.getRol().stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public UserApiDto findByUsername(String username) {
        UserApi user = userRespository.findByUsername(username);
        UserApiDto userApiDto = new UserApiDto(user.getMail(), user.getUsername(), user.getRol());
        return userApiDto;

    }

}
