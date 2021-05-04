package storyteam.server.story.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import storyteam.server.story.model.Comment;
import storyteam.server.story.repository.CommentRepository;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    Logger logger = org.slf4j.LoggerFactory.getLogger(CommentController.class);
    // Quand on appelle une story il faudrait pouvoir afficher tout les commentaires
    // d'une story

    @Autowired
    CommentRepository commentRepository;

    @GetMapping("/show")
    public ResponseEntity<Comment> getComment() {
        Optional<Comment> comment = commentRepository.findById(1);
        return ResponseEntity.ok(comment.get());
    }

}
