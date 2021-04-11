package storyteam.server.story;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = "storyteam.server.story")
public class StoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoryApplication.class, args);
	}

	@Bean(name = "passwordEncoder")
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// TODO get secret from the env
	@Bean(name = "JWT_SECRET")
	public String jwtSecret() {
		return "SECRET_KEY";
	}
}
