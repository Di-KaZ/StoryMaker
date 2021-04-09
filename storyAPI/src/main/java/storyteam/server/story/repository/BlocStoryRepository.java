package storyteam.server.story.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import storyteam.server.story.model.BlocStory;

@Repository
public interface BlocStoryRepository extends JpaRepository<BlocStory, Integer> {

	Optional<BlocStory> findById(Integer id);

	@Query(value = "SELECT bs FROM BlocStory bs WHERE bs.previousIdBloc = :id")
	Optional<List<BlocStory>> findAllChoices(@Param("id") Integer id);
}
