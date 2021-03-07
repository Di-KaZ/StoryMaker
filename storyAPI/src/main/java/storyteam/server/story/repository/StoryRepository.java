package storyteam.server.story.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import storyteam.server.story.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
	/**
	 * Get a story via it's id
	 */
	Optional<Story> findById(Integer id);
}
