package storyteam.server.story.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "blocStory")
@JsonIgnoreProperties({ "story" })
public class BlocStory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "content")
	private String content;

	@Column(name = "storyId")
	private Integer storyId;

	@Column(name = "previousBlocId")
	private Integer previousBlocId;

	public BlocStory() {
	}

	public BlocStory(String name, String content, Integer storyId, Integer previousIdBloc) {
		this.name = name;
		this.storyId = storyId;
		this.content = content;
		this.previousBlocId = previousIdBloc;
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

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setText(String content) {
		this.content = content;
	}

	public Integer getStoryId() {
		return storyId;
	}

	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}

	public Integer getPreviousBlocId() {
		return previousBlocId;
	}

	public void setPreviousBlocId(Integer previousBlocId) {
		this.previousBlocId = previousBlocId;
	}
}
