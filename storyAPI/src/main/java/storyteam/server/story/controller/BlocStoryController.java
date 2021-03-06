package storyteam.server.story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import storyteam.server.story.model.BlocStory;
import storyteam.server.story.repository.BlocStoryRepository;
import storyteam.server.story.repository.StoryRepository;

import javax.print.attribute.standard.Media;
import java.awt.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/blocstories")
public class BlocStoryController {

    @Autowired
    BlocStoryRepository blocStoryRepository;

    @Autowired
    StoryRepository storyRepository;

    @PostMapping(value = "/get/{idBlocStory}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BlocStory getBlocStory(
            @RequestBody Integer idBlocStory
    ){

        BlocStory blocStory = blocStoryRepository.findById(idBlocStory).get();

        return blocStory;

    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlocStory createBlocStory(
            @RequestBody BlocStory blocStory
    ){

        blocStory.setStory(storyRepository.findById(1).get());

        return blocStoryRepository.save(blocStory);

    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BlocStory updateBlocStory(
            @RequestBody BlocStory blocStory
    ){

        blocStory.setStory(storyRepository.findById(2).get());

        return blocStoryRepository.save(blocStory);

    }

    @DeleteMapping("/delete/{blocStoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlocStory(
            @PathVariable Integer blocStoryId
    ){
        blocStoryRepository.deleteById(blocStoryId);
    }

}
