package storyteam.server.story.controller;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.model.BlocStory;
import storyteam.server.story.model.CreatorStory;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.BlocStoryRepository;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.services.UserService;

@RestController
@RequestMapping("/creator")
public class CreatorStoryController {

	@Autowired
	StoryRepository storyRepo;

	@Autowired
	BlocStoryRepository blocRepo;

	@Autowired
	UserService userRepo;

	@PostMapping(value = "/save")
	public ResponseEntity<Story> postMethodName(@RequestHeader("Authorization") String auth,
			@RequestBody CreatorStory story) {
		var rStory = storyRepo.findById(story.getStory().getId());

		if (rStory.isEmpty()) {
			Story newStory = new Story();
			newStory.setDescription(story.getStory().getDescription());
			newStory.setUser(userRepo.findUserByToken(auth).get());
			newStory.setName(story.getStory().getName());
			newStory.setCreationDate(LocalDate.now());
			newStory.setFirstBloc(-1);
			newStory.setCover(story.getStory().getCover());

			final var tempNewStory = storyRepo.save(newStory);
			HashMap<String, BlocStory> blocMapWithTempId = new HashMap<>();
			story.getBlocs().forEach(bloc -> {
				var newBloc = new BlocStory(bloc.getName(), bloc.getText(), tempNewStory.getId(), -1, bloc.getCover());
				newBloc = blocRepo.save(newBloc);
				blocMapWithTempId.put(bloc.getId(), newBloc);
			});
			newStory.setFirstBloc(blocMapWithTempId.get(story.getStory().getFirstBlocId()).getId());
			storyRepo.save(newStory);

			story.getBlocs().forEach(bloc -> {
				if (bloc.getIn() != null) {
					var parentBlocId = blocMapWithTempId.get(bloc.getIn().getId()).getId();
					blocMapWithTempId.get(bloc.getId()).setPreviousBlocId(parentBlocId);
					blocRepo.save(blocMapWithTempId.get(bloc.getId()));
				}
			});
			return ResponseEntity.ok(newStory);
		}
		return ResponseEntity.ok(rStory.get());
	}
}
