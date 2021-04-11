package storyteam.server.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import storyteam.server.story.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
