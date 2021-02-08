package storyteam.server.story.controller;

import org.springframework.web.bind.annotation.RestController;
import storyteam.server.story.model.User;

@RestController
public class MainController {

    public String home(){
        return "Hello world";
    }
}
