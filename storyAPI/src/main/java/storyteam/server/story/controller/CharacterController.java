package storyteam.server.story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import storyteam.server.story.model.Character;
import storyteam.server.story.repository.CharacterRepository;
import storyteam.server.story.repository.StoryRepository;

import java.awt.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    StoryRepository storyRepository;

    @GetMapping("/show")
    public Character getCharacter(){
        return new Character();
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Character createCharacter(
            @RequestBody Character character
    ){

        character.setStory(storyRepository.findById(2).get());
        return characterRepository.save(character);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Character updateCharacter(
            @RequestBody Character character
    ){
        character.setStory(storyRepository.findById(2).get());
        return characterRepository.save(character);
    }

    @DeleteMapping("/delete/{characterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(
            @PathVariable Integer characterId
    ){
        characterRepository.deleteById(characterId);
    }

    //Besoins hors crud => faire des methodes /attack
    //Tester la couche services
    //Faire les test des controller => quesce qui se passe quand je fait un get dan url (intégration)
    //Spring web avec MOCK MVC

}
