package storyteam.server.story.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comment")
@JsonIgnoreProperties({ "story" })
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private String content;

	@Column(name = "creationDate")
	private LocalDate creationDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties({ "id", "password", "email", "stories" })
	private User user;

	@ManyToOne
	@JoinColumn(name = "storyId")
	@JsonIgnoreProperties({ "comments" })
	private Story story;

	@JoinColumn(name = "subCommentId")
	@OneToMany
	@JsonIgnoreProperties({ "story", "comment", "subComment" })
	private List<Comment> subComments = new ArrayList<>();

	public Comment() {
	}

	public Comment(Integer id, String content, LocalDate creationDate, User user, Story story) {
		this.id = id;
		this.content = content;
		this.creationDate = creationDate;
		this.user = user;
		this.story = story;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Story getStory() {
		return this.story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public List<Comment> getSubComment() {
		return this.subComments;
	}

	public void setSubComment(List<Comment> subComments) {
		this.subComments = subComments;
	}

}
