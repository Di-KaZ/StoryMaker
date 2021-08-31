package storyteam.server.story.dto;

import java.util.Set;

/**
 * A class used to send data about the story bloc as json without exta methods
 */
public class StoryBlocDTO {
	public Integer id;
	public String name;
	public String text;
	public String urlImage;
	public Set<Integer> choices;

	public StoryBlocDTO(Integer id, String name, String text, String urlImage, Set<Integer> choices) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.urlImage = urlImage;
		this.choices = choices;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Integer> getChoices() {
		return this.choices;
	}

	public void setChoices(Set<Integer> choices) {
		this.choices = choices;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
