package storyteam.server.story.inter;

import java.util.List;

import storyteam.server.story.dto.StoryBlocDTO;

public interface IStoryBlocManager {
	/**
	 * Rturn tha name of the bloc, must be unique in same story
	 *
	 * @return
	 */
	public String getName();

	/**
	 * Return the image used to visually represent this bloc
	 *
	 * @return
	 */
	public String getImage();

	/**
	 * get the content of the block (the story part)
	 *
	 * @return
	 */
	public String getContent();

	/**
	 * Return a list of the choice bloc name
	 *
	 * @return
	 */
	public List<String> getChoiceName();

	/**
	 * transofrm the story bloc into it's dto
	 *
	 * @return
	 */
	public StoryBlocDTO toDTO();
}
