package storyteam.server.story.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.model.BlocStory;
import storyteam.server.story.repository.BlocStoryRepository;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.services.BlocStoryService;

@RestController
@RequestMapping("/blocstory")
public class BlocStoryController {

	@Autowired
	BlocStoryService blocStoryService;

	@Autowired
	BlocStoryRepository blocStoryRepository;

	@Autowired
	StoryRepository storyRepository;

	@GetMapping(value = "/get/choices/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BlocStory>> getChoices(@PathVariable("id") Integer idBlocStory) {
		Optional<List<BlocStory>> choices = blocStoryRepository.findAllChoices(idBlocStory);

		if (choices.isPresent()) {
			return ResponseEntity.ok(choices.get());
		}

		return ResponseEntity.badRequest().body(null);
	}
}
