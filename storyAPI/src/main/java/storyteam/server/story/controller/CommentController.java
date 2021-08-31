package storyteam.server.story.controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.dto.CommentDto;
import storyteam.server.story.model.Comment;
import storyteam.server.story.model.Story;
import storyteam.server.story.model.User;
import storyteam.server.story.repository.CommentRepository;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.repository.UserRepository;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(CommentController.class);
    // Quand on appelle une story il faudrait pouvoir afficher tout les commentaires
    // d'une story

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    UserRepository userRepository;

    // Créer une route pour accéder à add pour pouvoir ajouter des commentaires en
    // Passer par un DTO et le convertir en BDD
    @PostMapping(value = "/create")
    public void registerComment(@RequestBody CommentDto commentDto) {
        Story story = storyRepository.findById(commentDto.getStory()).get();
        User user = userRepository.findByName(commentDto.getUsername()).get();
        // Date dateCreation = new Date(Calendar.getInstance().getTime().getTime());
        LocalDateTime dateCreation = java.time.LocalDateTime.now();
        System.out.println(dateCreation);

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setStory(story);
        comment.setUser(user);
        comment.setCreationDate(dateCreation);

        commentRepository.save(comment);
        // commentRepository.save(commentDto);
    }

}
