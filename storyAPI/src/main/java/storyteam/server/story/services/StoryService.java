package storyteam.server.story.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import storyteam.server.story.model.BlocStory;
import storyteam.server.story.model.Story;
import storyteam.server.story.repository.BlocStoryRepository;
import storyteam.server.story.repository.StoryRepository;
import storyteam.server.story.repository.UserRepository;

@Service
public class StoryService {
	private static final Integer PAGE_STORY_SIZE = 15;

	@Autowired
	StoryRepository storyRepository;

	@Autowired
	BlocStoryRepository blocStoryRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * Effectue une recherche selon les critètre
	 *
	 * @param pageNum
	 * @param username
	 * @param storyname
	 * @param tags
	 * @param orderby
	 * @return
	 */
	public Page<Story> search(Integer pageNum, Optional<String> username, Optional<String> storyname,
			Optional<String> tags, Optional<String> orderby) {
		Pageable page = PageRequest.of(pageNum, PAGE_STORY_SIZE);
		// return storyRepository.search(page);
		return storyRepository.findAll(page);
	}

	/**
	 * Récupere la story correspondant a l'id
	 *
	 * @param storyId
	 * @return
	 */
	public Optional<Story> findById(Integer storyId) {
		return storyRepository.findById(storyId);
	}

	/**
	 * Récupére le bloc story apparenant a la story
	 *
	 * @param storyId
	 * @param blocId
	 * @return
	 */
	public Optional<BlocStory> getBlocStory(Integer storyId, Integer blocId) {
		return blocStoryRepository.findBlocStoryByStoryIdAndBlocId(blocId, storyId);
	}

	/**
	 * Renvoit une page story avec le numero de page les pages font une taille de
	 * PAGE_STORY_SIZE
	 *
	 * @param numeroPage
	 * @return
	 */
	public Page<Story> getTrendingStory() {
		Pageable page = PageRequest.of(0, 4);
		return storyRepository.findTrending(page);
	}

	/**
	 * Save une nouvelle story
	 *
	 * @param story
	 * @return
	 */
	public Story save(Story story) {
		return storyRepository.save(story);
	}

	/**
	 * Supprime une story via son id
	 *
	 * @param storyid
	 */
	public void delete(Integer storyid) {
		storyRepository.deleteById(storyid);
	}
}
