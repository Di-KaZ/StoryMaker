package storyteam.server.story.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storyteam.server.story.model.Character;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @GetMapping("/show")
    public Character getCharacter(){
        return new Character();
    }

    @GetMapping("/create")
    public Character createCharacter(){
        return new Character();
    }

    @GetMapping("/update")
    public Character updateCharacter(){
        return new Character();
    }

    @GetMapping("/delete")
    public void deleteCharacter(){

    }

    //Besoins hors crud => faire des methodes /attack
    //Tester la couche services
    //Faire les test des controller => quesce qui se passe quand je fait un get dan url (intégration)
    //Spring web avec MOCK MVC

}
