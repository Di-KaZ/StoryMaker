package storyteam.server.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storyteam.server.story.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}