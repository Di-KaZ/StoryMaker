package storyteam.server.story.dto;

import java.util.List;

/**
 * A class used to send data about the story bloc as json without exta methods
 */
public class StoryBlocDTO {
	public String name;
	public String urlImage;
	public List<String> choices;

	public StoryBlocDTO(String name, String urlImage, List<String> choices) {
		this.name = name;
		this.urlImage = urlImage;
		this.choices = choices;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlImage() {
		return this.urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<String> getChoices() {
		return this.choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}
}
