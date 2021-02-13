package storyteam.server.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storyteam.server.story.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
