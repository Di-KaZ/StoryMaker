package storyteam.server.story.dto;

import java.util.List;

/**
 * A class used to send data about the story as json without exta methods
 */
public class StoryDTO {
	String name;
	String coverUrl;
	List<StoryBlocDTO> blocs;

	public StoryDTO(String name, String coverUrl, List<StoryBlocDTO> blocs) {
		this.name = name;
		this.coverUrl = coverUrl;
		this.blocs = blocs;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverUrl() {
		return this.coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public List<StoryBlocDTO> getBlocs() {
		return this.blocs;
	}

	public void setBlocs(List<StoryBlocDTO> blocs) {
		this.blocs = blocs;
	}
}
