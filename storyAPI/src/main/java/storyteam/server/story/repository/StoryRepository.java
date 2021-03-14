package storyteam.server.story.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import storyteam.server.story.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {

	Page<Story> findAll(Pageable pageable);

	@Query(value = "select s from Story s")
	Page<Story> findTrending(Pageable page);

	/**
	 * Filter the result of page by the different parameters
	 *
	 * @param storyname
	 * @param orderby
	 * @return
	 */
	@Query(value = "select s from Story s")
	Page<Story> search(Pageable page);
}
