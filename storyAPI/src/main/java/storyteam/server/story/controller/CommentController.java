package storyteam.server.story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.model.Comment;
import storyteam.server.story.repository.CommentRepository;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    // Quand on appelle une story il faudrait pouvoir afficher tout les commentaires
    // d'une story

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/show")
    public Comment getComment() {
        return new Comment();
    }

}
