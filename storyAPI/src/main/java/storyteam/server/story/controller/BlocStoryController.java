package storyteam.server.story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storyteam.server.story.model.BlocStory;
import storyteam.server.story.repository.BlocStoryRepository;

@RestController
@RequestMapping("/blocstories")
public class BlocStoryController {

    @Autowired
    BlocStoryRepository blocStoryRepository;

    @GetMapping("/show")
    public BlocStory showBlocStory(){

        BlocStory blocStory = blocStoryRepository.findById(1).get();

        return blocStory;

    }

    @GetMapping("/create")
    public BlocStory createBlocStory(){

        return new BlocStory();

    }

    @GetMapping("/update")
    public BlocStory updateBlocStory(){

        return new BlocStory();

    }

    @GetMapping("/delete")
    public void deleteBlocStory(){

    }

}
