package storyteam.server.story.inter;

import storyteam.server.story.dto.StoryDTO;

/**
 * Interface showing the implementation of all methods used to navigate in a
 * story. It will use the entity as parent ?
 */
public interface IStoryManager {

	/**
	 * Returns the name of the story
	 *
	 * @return
	 */
	public String getName();

	/**
	 * Return a story block of this story with the given name
	 *
	 * @param name
	 * @return IStroyBlocManager
	 */
	public IStoryBlocManager getBloc(String name);

	/**
	 * Return the url of image used as cover
	 *
	 * @return
	 */
	public String getCover();

	/**
	 * Add a block to the sotry
	 *
	 * @param bloc
	 * @return true if no conflict false if a bloc already exist with this name
	 */
	public boolean addBloc(IStoryBlocManager bloc);

	/**
	 * Retun the first block that should be played at the start of the story
	 *
	 * @return
	 */
	public IStoryBlocManager getFirstBloc();

		/**
	 * transofrm the story  into it's dto
	 *
	 * @return
	 */
	public StoryDTO toDTO();
}
