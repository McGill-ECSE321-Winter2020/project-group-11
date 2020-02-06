package ca.mcgill.ecse321.projectgroup11;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class ProjectGroup11Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjectGroup11Application.class, args);
	}

	@RequestMapping("/")
	public String greeting() {
		return "Hello ECSE Group 11!";
	}
}
