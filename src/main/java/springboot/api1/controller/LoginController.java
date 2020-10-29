package springboot.api1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.api1.persistence.dtos.UserApiDto;
import springboot.api1.service.JwtService;
import springboot.api1.service.UserService;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    private JwtService jwtService;

    
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@AuthenticationPrincipal User activeUser) {
        UserApiDto userApiDto = userService.findByUsername(activeUser.getUsername());

        try {
            return new ResponseEntity<String>("token: " + jwtService.createToken(userApiDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
