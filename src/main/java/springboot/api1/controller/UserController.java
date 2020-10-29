package springboot.api1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springboot.api1.persistence.dtos.UserApiDto;
import springboot.api1.persistence.entity.UserApi;
import springboot.api1.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    @GetMapping(value = "/find_user")
    public ResponseEntity<?> login(@RequestParam String username) {

        try {
            UserApiDto userApiDto = userService.findByUsername(username);
            if (userApiDto != null)
                return new ResponseEntity<UserApiDto>(userApiDto, HttpStatus.OK);
            else
                return new ResponseEntity<UserApiDto>(userApiDto, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    } 
    @Secured(value = {"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/findall_users")
    public ResponseEntity<?> getUsers() {
        try {
            return new ResponseEntity<List<UserApiDto>>(userService.findAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/create")
    public ResponseEntity<?> PutUsers(@RequestBody @Valid UserApi userApi) {
        try {
            if (userService.CreateUser(userApi) != null)
                return new ResponseEntity<UserApiDto>(userService.findByUsername(userApi.getUsername()), HttpStatus.OK);
            else
                return new ResponseEntity<String>("Existe", HttpStatus.ALREADY_REPORTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
