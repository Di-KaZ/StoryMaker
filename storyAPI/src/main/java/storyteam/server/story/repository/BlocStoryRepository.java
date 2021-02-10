package storyteam.server.story.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storyteam.server.story.model.BlocStory;

@Repository
public interface BlocStoryRepository extends JpaRepository<BlocStory, Integer> {
}
