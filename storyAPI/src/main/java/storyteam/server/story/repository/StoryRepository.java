package storyteam.server.story.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import storyteam.server.story.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

	Page<Story> findAll(Pageable pageable);

	/**
	 * Filter the result of page by the different parameters
	 *
	 * @param storyname
	 * @param orderby
	 * @return
	 */
	@Query(value = "select s from Story s where s.name like %:storyName% order by :orderby")
	Page<Story> search(@Param("storyName") Optional<String> storyname, @Param("orderby") Optional<String> orderby,
			Pageable page);
}
