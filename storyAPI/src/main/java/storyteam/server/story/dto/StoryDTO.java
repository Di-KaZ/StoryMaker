package storyteam.server.story.dto;

import java.util.List;

/**
 * A class used to send data about the story as json without exta methods
 */
public class StoryDTO {
	Integer id;
	String name;
	String description;
	String creationDate;
	Integer userId;
	String coverUrl;
	List<StoryBlocDTO> blocs;

	public StoryDTO(String name, String description, String creationDate, Integer userId, String coverUrl, List<StoryBlocDTO> blocs) {
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.userId = userId;
		this.coverUrl = coverUrl;
		this.blocs = blocs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public List<StoryBlocDTO> getBlocs() {
		return blocs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public void setBlocs(List<StoryBlocDTO> blocs) {
		this.blocs = blocs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
