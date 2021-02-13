package storyteam.server.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storyteam.server.story.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    //Method that find a user
    Optional<User> findByName(String name);

}