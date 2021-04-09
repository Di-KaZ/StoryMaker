package storyteam.server.story.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.model.BlocStory;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.BlocStoryRepository;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.services.BlocStoryService;

@RestController
@RequestMapping("/blocstories")
public class BlocStoryController {

	@Autowired
	BlocStoryService blocStoryService;

	@Autowired
	BlocStoryRepository blocStoryRepository;

	@Autowired
	StoryRepository storyRepository;

	// (M)
	@GetMapping(value = "/get/{idBlocStory}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BlocStory> getBlocStory(@RequestBody Integer idBlocStory) {

		Optional<BlocStory> blocStory = blocStoryRepository.findById(idBlocStory);
		if (blocStory.isPresent()) {
			return ResponseEntity.ok(blocStory.get());
		}
		return ResponseEntity.badRequest().body(null);

	}

	@GetMapping(value="/getFirstBloc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BlocStory> getFirstBlocStory(@PathVariable("id") Integer idStory){
		Optional<Story> story = storyRepository.findById(idStory);

		if(story.isPresent()){
			BlocStory blocStory = story.get().getFirstBloc();
			return ResponseEntity.ok(blocStory);
		}

		return ResponseEntity.badRequest().body(null);
	}

	@GetMapping(value="/get/choices/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BlocStory>> getChoices(@PathVariable("id") Integer idBlocStory){
		Optional<List<BlocStory>> choices = blocStoryRepository.findAllChoices(idBlocStory);

		if(choices.isPresent()){
			return ResponseEntity.ok(choices.get());
		}

		return ResponseEntity.badRequest().body(null);
	}

	// // (M)
	// @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<BlocStory> createBlocStory(@RequestBody BlocStory
	// blocStory) {

	// y> blocStory = storyRepository.setStory(storyRepository.findById(1));
	// if (blocStory.isPresent()) {

	// return ResponseEntity.ok(blocStoryRepository.save(blocStory.get()));
	// }

	// return ResponseEntity.badRequest().body(null);

	// }

	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public BlocStory updateBlocStory(@RequestBody BlocStory blocStory) {

		blocStory.setStory(storyRepository.findById(2).get());

		return blocStoryRepository.save(blocStory);

	}

	@DeleteMapping("/delete/{blocStoryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBlocStory(@PathVariable Integer blocStoryId) {
		blocStoryRepository.deleteById(blocStoryId);
	}

}
