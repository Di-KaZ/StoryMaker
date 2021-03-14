package storyteam.server.story.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.model.BlocStory;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.BlocStoryRepository;
import storyteam.server.story.services.StoryService;

@RestController
@RequestMapping(value = "/story")
public class StoryController {
	Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StoryController.class);

	@Autowired
	BlocStoryRepository BlocStoryRepository;

	@Autowired
	StoryService storyService;

	/**
	 * Récupère un bloc d'une certaine story
	 *
	 * @param storyId
	 * @param blocId
	 * @return
	 */
	@GetMapping("/play/{storyId}/bloc/{blocId}")
	public ResponseEntity<BlocStory> playStoryBloc(@PathVariable("storyId") Integer storyId,
			@PathVariable("blocId") Integer blocId) {
		Optional<BlocStory> blocStory = storyService.getBlocStory(storyId, blocId);
		if (blocStory.isPresent()) {
			return ResponseEntity.ok(blocStory.get());
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Requete pour commencé a jouer une story
	 *
	 * @param storyId
	 * @return
	 */
	@GetMapping("/play/{storyId}")
	ResponseEntity<Story> playStoryStart(@PathVariable("storyId") Integer storyId) {
		Optional<Story> story = storyService.getStory(storyId);
		if (story.isPresent()) {
			return ResponseEntity.ok(story.get());
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Save une story en bdd depuis son json envoyé dans le body TODO algo de
	 * creation
	 *
	 * @param storyDTO
	 * @return
	 */
	@PostMapping(value = "/save")
	public ResponseEntity<Story> createStory(@RequestBody Story story) {
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
	}

	/**
	 * Supprime une story via son id /!\ TODO verif si la story appartient a l'user
	 * connecté
	 *
	 * @param storyId
	 */
	@DeleteMapping("/delete/{id}")
	public void deleteStory(@PathVariable("id") Integer storyId) {
		storyService.delete(storyId);
	}

	/**
	 * Récupère une page de toutes les story dans ordonée par date
	 *
	 * @param page
	 * @return
	 */
	@GetMapping("trending")
	public ResponseEntity<List<Story>> getPageStory() {
		return ResponseEntity.ok(storyService.getTrendingStory().toList());
	}

	/**
	 * Effecture une recherche dans les stories avec les parametres
	 *
	 * @param page
	 * @param username
	 * @param storyname
	 * @param tags
	 * @param orderby
	 * @return
	 */
	@GetMapping("search/{page}")
	public ResponseEntity<Page<Story>> searchStories(@PathVariable("page") Integer page,
			@RequestParam("username") Optional<String> username, @RequestParam("storyname") Optional<String> storyname,
			@RequestParam("tags") Optional<String> tags, @RequestParam("orderby") Optional<String> orderby) {

		LOGGER.info("page={},username={},storyname={}", page, username, storyname);
		// normalize page to the first being 1 but ehre we start at 0
		if (--page < 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		Page<Story> storiesSearched = storyService.search(page, username, storyname, tags, orderby);
		if (storiesSearched.isEmpty()) {
			LOGGER.info("rien ici");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(storiesSearched);
	}
}