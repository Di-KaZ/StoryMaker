package storyteam.server.story.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import storyteam.server.story.dto.StoryDTO;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.repository.UserRepository;

@Service
public class StoryService {
	private static final Integer PAGE_STORY_SIZE = 15;

	@Autowired
	StoryRepository storyRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * Création d'une entité Story a partir de son DTO
	 *
	 * @param storyDTO
	 * @return Story
	 */
	public Story getStoryFromDTO(StoryDTO storyDTO) {
		Story story = new Story();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		story.setName(storyDTO.getName());
		story.setCreationDate(LocalDate.parse(storyDTO.getCreationDate(), dateTimeFormatter));
		story.setDescription(storyDTO.getDescription());
		story.setUser(userRepository.findById(storyDTO.getUserId()).get());
		return story;
	}

	/**
	 * Renvoit une page story avec le numero de page les pages font une taille de
	 * PAGE_STORY_SIZE
	 *
	 * @param numeroPage
	 * @return
	 */
	public Page<Story> getPageStory(Integer numeroPage) {
		Pageable page = PageRequest.of(numeroPage, PAGE_STORY_SIZE);
		return storyRepository.findAll(page);
	}
}
