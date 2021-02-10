package storyteam.server.story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/stories")
public class StoryController {

    @Autowired
    StoryRepository storyRepository;

    //A ENLEVER APRES C'EST JUSTE POUR TEST
    @Autowired
    UserRepository userRepository;

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

    @GetMapping("/create")
    public Story createStory(){

        Story story = new Story();
        story.setName("La fois ou j'ai explosé un troll au tarot");
        story.setUser(userRepository.findById(3).get());

        return storyRepository.save(story);

    }

    @GetMapping("/update")
    public Story updateStory(){

        Story storyUpdate = storyRepository.findById(3).get();

        storyUpdate.setName("updated name");

        return storyRepository.save(storyUpdate);

    }

    @GetMapping("/delete")
    public void deleteStory(){

        storyRepository.deleteById(3);

    }
}
