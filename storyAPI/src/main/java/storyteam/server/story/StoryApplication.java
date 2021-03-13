package storyteam.server.story;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
@ComponentScan(basePackages = "storyteam.server.story")
public class StoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(StoryApplication.class, args);
	}
}
