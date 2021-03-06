package storyteam.server.story.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import storyteam.server.story.dto.StoryDTO;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.repository.UserRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class StoryService {
	@Autowired
	StoryRepository storyRepository;

	@Autowired
	UserRepository userRepository;

	//Création de l'objet Story puis mise en forme des données reprise du StoryDTO reçu du FrontOffice

	public Story getStoryFromDTO(StoryDTO storyDTO){

		Story story = new Story();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		story.setName(storyDTO.getName());
		story.setCreationDate(LocalDate.parse(storyDTO.getCreationDate(), dateTimeFormatter));
		story.setDescription(storyDTO.getDescription());
		story.setUser(userRepository.findById(storyDTO.getUserId()).get());

		return story;

	}
}
