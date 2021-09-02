package storyteam.server.story.controller;

import java.time.LocalDate;
import java.util.HashMap;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	EntityManager entityManager;

	@GetMapping(value = "/load")
	public ResponseEntity<CreatorStory> loadUserStory(@RequestHeader("Authorization") String auth,
			@RequestParam("id") Integer storyId) {
		var user = userRepo.findUserByToken(auth);
		if (user.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		var bddStory = storyRepo.findById(storyId);
		if (bddStory.isPresent()) {
			return ResponseEntity.ok(new CreatorStory(bddStory.get()));
		}
		return ResponseEntity.badRequest().build();
	}

	@Transactional()
	@PostMapping(value = "/save")
	public ResponseEntity<CreatorStory> saveUserStory(@RequestHeader("Authorization") String auth,
			@RequestBody CreatorStory story) {
		var bddStory = storyRepo.findById(story.getStory().getId());

		if (bddStory.isEmpty()) {
			Story newStory = new Story();
			newStory.setDescription(story.getStory().getDescription());
			newStory.setUser(userRepo.findUserByToken(auth).get());
			newStory.setName(story.getStory().getName());
			newStory.setCreationDate(LocalDate.now());
			newStory.setFirstBlocId(-1);
			newStory.setCover(story.getStory().getCover());

			newStory = storyRepo.save(newStory);
			HashMap<String, BlocStory> blocMapWithTempId = new HashMap<>();
			for (var bloc : story.getBlocs()) {
				var newBloc = new BlocStory(bloc.getName(), bloc.getText(), newStory.getId(), -1, bloc.getCover());
				newBloc = blocRepo.save(newBloc);
				blocMapWithTempId.put(bloc.getId(), newBloc);
			}
			newStory.setFirstBlocId(blocMapWithTempId.get(story.getStory().getFirstBlocId()).getId());
			newStory = storyRepo.save(newStory);

			story.getBlocs().forEach(bloc -> {
				if (bloc.getIn() != null) {
					var parentBlocId = blocMapWithTempId.get(bloc.getIn().getId()).getId();
					blocMapWithTempId.get(bloc.getId()).setPreviousBlocId(parentBlocId);
				}
			});
			blocRepo.saveAll(blocMapWithTempId.values());
			entityManager.persist(newStory);
			entityManager.flush();
			entityManager.refresh(newStory);
			return ResponseEntity.ok(new CreatorStory(newStory));
		}
		Story updatingStory = bddStory.get();
		updatingStory.setDescription(story.getStory().getDescription());
		updatingStory.setName(story.getStory().getName());
		updatingStory.setCreationDate(LocalDate.now());
		updatingStory.setCover(story.getStory().getCover());
		updatingStory.setFirstBlocId(Integer.valueOf(story.getStory().getFirstBlocId()));

		updatingStory = storyRepo.save(updatingStory);
		HashMap<String, BlocStory> blocMapWithTempId = new HashMap<>();
		for (var bloc : story.getBlocs()) {
			try {
				var existingBloc = blocRepo.findById(Integer.valueOf(bloc.getId()));
				existingBloc.ifPresent(existing -> {
					existing.setName(bloc.getName());
					existing.setText(bloc.getText());
					existing.setCover(bloc.getCover());
					blocMapWithTempId.put(bloc.getId(), existing);
				});
			} catch (NumberFormatException e) {
				var newBloc = new BlocStory(bloc.getName(), bloc.getText(), updatingStory.getId(), -1, bloc.getCover());
				newBloc = blocRepo.save(newBloc);
				blocMapWithTempId.put(bloc.getId(), newBloc);

			}
		}
		updatingStory.setFirstBlocId(blocMapWithTempId.get(story.getStory().getFirstBlocId()).getId());
		updatingStory = storyRepo.save(updatingStory);
		story.getBlocs().forEach(bloc -> {
			if (bloc.getIn() != null && bloc.getIn().getId() != "-1"
					&& blocMapWithTempId.get(bloc.getIn().getId()) != null) {
				var parentBlocId = blocMapWithTempId.get(bloc.getIn().getId()).getId();
				blocMapWithTempId.get(bloc.getId()).setPreviousBlocId(parentBlocId);
				blocRepo.save(blocMapWithTempId.get(bloc.getId()));
			}
		});
		entityManager.persist(updatingStory);
		entityManager.flush();
		entityManager.refresh(updatingStory);
		return ResponseEntity.ok(new CreatorStory(updatingStory));
	}
}
