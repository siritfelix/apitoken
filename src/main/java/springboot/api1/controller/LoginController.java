package springboot.api1.controller;

import java.util.List;

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
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public ResponseEntity<?> login(@RequestParam String username) {

        try {
            return new ResponseEntity<UserApiDto>(userService.findByUsername(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
