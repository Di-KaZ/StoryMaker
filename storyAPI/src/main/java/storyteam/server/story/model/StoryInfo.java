package storyteam.server.story.model;

import java.util.Date;

public class StoryInfo {
	private Integer id;
	private String name;
	private String description;
	private Date creationDate;
	private String username;
	private String firstBlocId;


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

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
