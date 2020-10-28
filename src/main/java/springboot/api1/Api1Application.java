package springboot.api1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springboot.api1.persistence.entity.UserApi;
import springboot.api1.persistence.repository.UserRespository;
import springboot.api1.service.UserService;

@SpringBootApplication
public class Api1Application implements CommandLineRunner {
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(Api1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> rol = new ArrayList<>();
		rol.add("ROLE_USER");
		rol.add("ROLE_ADMIN");
		UserApi userApi = new UserApi("siritfelix@gmail.com", "12345", "siritfelix", rol, true);
		userService.CreateUser(userApi);

	}

}
