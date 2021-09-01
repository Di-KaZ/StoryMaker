package storyteam.server.story.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.model.BlocStory;
import storyteam.server.story.model.Story;
import storyteam.server.story.model.User;
import storyteam.server.story.services.BlocStoryService;
import storyteam.server.story.services.StoryService;
import storyteam.server.story.services.UserService;

@RestController
@RequestMapping(value = "/story")
public class StoryController {
	Logger LOGGER = LoggerFactory.getLogger(StoryController.class);

	@Autowired
	StoryService storyService;

	@Autowired
	UserService userService;

	@Autowired
	BlocStoryService blocStoryService;

	@GetMapping(value = "/play/{id}/getFirstBloc")
	public ResponseEntity<BlocStory> getFirstBlocStory(@PathVariable("id") Integer storyId) {
		Optional<Story> story = storyService.findById(storyId);

		if (story.isPresent()) {
			Optional<BlocStory> blocStory = blocStoryService.findById(story.get().getFirstBlocId());
			if (blocStory.isPresent()) {
				return ResponseEntity.ok(blocStory.get());
			}
		}
		return ResponseEntity.badRequest().body(null);
	}

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
	 * Récupère un bloc d'une certaine story
	 *
	 * @param storyId
	 * @param blocId
	 * @return
	 */
	@GetMapping("/info/{storyId}")
	public ResponseEntity<Story> getStory(@PathVariable("storyId") Integer storyId) {
		Optional<Story> story = storyService.getStory(storyId);
		if (story.isPresent()) {
			return ResponseEntity.ok(story.get());
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
		Optional<Story> story = storyService.findById(storyId);

		// Je vais effectuer ici mes test pour récupérer mes futurs commentaires
		// Attention il faudra prendre garde a ne pas faire de boucle Recup story->
		// recup comment -> recup user -> recup story -> ...
		// Voir quand on récup un commentaire un moyen pour ne récupérer que les données
		// utilisateurs qui nous intéresse

		if (story.isPresent()) {
			return ResponseEntity.ok(story.get());
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Supprime une story via son id
	 *
	 * @param storyId
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteStory(@RequestHeader("Authorization") String auth,
			@PathVariable("id") Integer storyId) {
		Optional<User> user = userService.findUserByToken(auth);
		if (user.isPresent()) {
			Optional<Story> story = storyService.findById(storyId);
			if (story.isPresent() && story.get().getUser().equals(user.get())) {
				storyService.delete(storyId);
				return ResponseEntity.ok(null);
			}
		}
		return ResponseEntity.noContent().build();
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
	 * Search story via tag selected by the user
	 *
	 * @param page
	 * @param username
	 * @param storyname
	 * @param tags
	 * @param orderby
	 * @return
	 */
	@GetMapping("search/{page}")
	public ResponseEntity<List<Story>> searchStories(@PathVariable("page") Integer page,
			@RequestParam("username") Optional<String> username, @RequestParam("storyname") Optional<String> storyname,
			@RequestParam("tags") Optional<String> tags, @RequestParam("orderby") Optional<String> orderby) {

		// normalize page to the first being 1 but here we start at 0
		if (--page < 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		List<Story> storiesSearched = storyService.search(page, username, storyname, tags, orderby).toList();
		if (storiesSearched.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(storiesSearched);
	}
}