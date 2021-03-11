package storyteam.server.story.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.dto.StoryDTO;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.repository.UserRepository;
import storyteam.server.story.services.StoryService;

@CrossOrigin
@RestController
@RequestMapping(value = "/story")
public class StoryController {
	Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StoryController.class);

	@Autowired
	StoryRepository storyRepository;

	// A ENLEVER APRES C'EST JUSTE POUR TEST
	@Autowired
	UserRepository userRepository;

	@Autowired
	StoryService storyService;

	// Il faudrait faire une fonction qui récupère une Story pour jouer
	// Ajouter un id pour la récupération de la story
	// "/"{id}"/show"
	@GetMapping("/play/{id}")
	public ResponseEntity<Story> playStory(@PathVariable("id") Integer id) {
		LOGGER.info("id est {}", id);
		Optional<Story> story = storyRepository.findById(id);
		if (story.isPresent()) {
			return ResponseEntity.ok(story.get());
		}
		return ResponseEntity.ok(story.get());

	}

	// @GetMapping("/play/{id}")
	// public Story showStory(@PathVariable("id") Integer id) {
	// Story story = storyRepository.findById(id);
	// if (story != null) {
	// return story;
	// }
	// throw new Exception();

	// // }

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Story> createStory(@RequestBody StoryDTO storyDTO
	/* @RequestBody Integer userId */
	) {
		Story story = storyService.getStoryFromDTO(storyDTO);
		return ResponseEntity.ok(storyRepository.save(story));
	}

	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Story> updateStory(@RequestBody StoryDTO storyDTO) {
		Optional<Story> story = storyRepository.findById(storyDTO.getId());
		if (story.isPresent()) {

			story.get().setName(storyDTO.getName());
			story.get().setCreationDate(LocalDate.now());
			story.get().setDescription(storyDTO.getDescription());
			return ResponseEntity.ok(storyRepository.save(story.get()));
		}
		return ResponseEntity.badRequest().body(null);

	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStory(@PathVariable("id") Integer storyId) {
		storyRepository.deleteById(storyId);
	}

	@GetMapping("getPage/{page}")
	public List<Story> getPageStory(@PathVariable("page") Integer page) {
		System.out.println("weoigfhpiweough");
		return storyService.getPageStory(page).stream().collect(Collectors.toList());
	}

}