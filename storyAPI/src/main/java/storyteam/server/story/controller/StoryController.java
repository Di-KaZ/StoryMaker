package storyteam.server.story.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.dto.StoryDTO;
import storyteam.server.story.object.StoryManagerTester;

@RestController
@CrossOrigin("*")
public class StoryController {
	private static Logger LOGGER = LoggerFactory.getLogger(StoryController.class);

	public StoryController() {
		LOGGER.info("iunitializes story controller");
	}

	@GetMapping(path = "/story", produces = MediaType.APPLICATION_JSON_VALUE)
	public StoryDTO getStoryWithId(@RequestParam("id") Long id) {

		return new StoryManagerTester().toDTO();
	}

	@DeleteMapping("/story")
	public String deleteStoryWithId(@RequestParam("id") Long id) {
		LOGGER.info("delete story was called withh id {}", id);
		return "Success";
	}
}
