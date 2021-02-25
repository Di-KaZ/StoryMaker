package storyteam.server.story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import storyteam.server.story.dto.StoryDTO;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.repository.UserRepository;
import storyteam.server.story.services.StoryService;

import java.time.LocalDate;
import java.util.logging.Logger;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/stories")
public class StoryController {

    @Autowired
    StoryRepository storyRepository;

    //A ENLEVER APRES C'EST JUSTE POUR TEST
    @Autowired
    UserRepository userRepository;

    @Autowired
    StoryService storyService;

    //Il faudrait faire une fonction qui récupère une Story pour jouer
    //Ajouter un id pour la récupération de la story
    // "/"{id}"/show"
    @GetMapping("/show")
    public Story showStory(){

        Story story = storyRepository.findById(1).get();

        return story;

    }

    //Obtenir la liste de toutes les stories existantes sur le site
    @GetMapping("/allStories")
    public void listStories(){

    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Story createStory(
            @RequestBody StoryDTO storyDTO
/*            @RequestBody Integer userId*/
    ){
        Story story = storyService.getStoryFromDTO(storyDTO);

        return storyRepository.save(story);

    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Story updateStory(
            @RequestBody StoryDTO storyDTO
    ){
        Story story = storyRepository.findById(storyDTO.getId()).get();
        story.setName(storyDTO.getName());
        story.setCreationDate(LocalDate.now());
        story.setDescription(storyDTO.getDescription());
        return storyRepository.save(story);

    }

    @DeleteMapping("/delete/{storyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStory(
            @PathVariable("storyId") Integer storyId
    ){
        storyRepository.deleteById(storyId);
    }
}
