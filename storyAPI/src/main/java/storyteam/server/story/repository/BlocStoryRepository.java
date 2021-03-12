package storyteam.server.story.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import storyteam.server.story.model.BlocStory;

@Repository
public interface BlocStoryRepository extends JpaRepository<BlocStory, Integer> {

	Optional<BlocStory> findById(Integer id);

	/**
	 * recupère un bloc story si existant en bdd
	 *
	 * @param blocId
	 * @param storyId
	 * @return
	 */
	@Query(value = "SELECT bloc FROM BlocStory bloc WHERE bloc.id = :blocId AND bloc.story.id = :storyId")
	Optional<BlocStory> findBlocStoryByStoryIdAndBlocId(@Param("blocId") Integer blocId,
			@Param("storyId") Integer storyId);
}
