package storyteam.server.story.model;

import java.time.LocalDate;

public class StoryInfo {
	private Integer id;
	private String name;
	private String description;
	private LocalDate creationDate;
	private String username;
	private String firstBlocId;
	private String cover;

	public String getFirstBlocId() {
		return firstBlocId;
	}

	public void setFirstBlocId(String firstBlocId) {
		this.firstBlocId = firstBlocId;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
}
