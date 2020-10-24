package springboot.api1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/user")
    public ResponseEntity<?> login(@RequestParam String user, @RequestParam String password) {
        ResponseEntity<?> ret = null;
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
        return ret;
    }

}
