package storyteam.server;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import storyteam.server.story.model.Story;
import storyteam.server.story.repository.StoryRepository;

public class InitializeData implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	StoryRepository storyRepository;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
		initStory();
	}

	public void initStory() {
		System.out.println("How the fuck");
		Story story1 = new Story(1, "Ma première histoire", "la première hsitoire", LocalDate.of(2021, 03, 03), null,
				null, null);
		Story story2 = new Story(2, "SUPA RPG", "une description", LocalDate.of(2021, 03, 01), null, null, null);
		Story story3 = new Story(3, "Troll Battle", "Un jeu de combat de trolls", LocalDate.of(2021, 03, 03), null,
				null, null);

		storyRepository.save(story1);
		storyRepository.save(story2);
		storyRepository.save(story3);
	}
}