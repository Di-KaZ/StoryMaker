package storyteam.server.story.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.dto.helloworld;

@RestController
public class MainController {
	@CrossOrigin
	@GetMapping("helloworld")
	public helloworld home() {
		return new helloworld("MARCHE STP");
	}
}
