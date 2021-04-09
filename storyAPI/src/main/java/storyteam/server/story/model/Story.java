package storyteam.server.story.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "story")
@JsonIgnoreProperties({ "blocStories" })
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "story_id")
	private Integer id;

	@Column(name = "story_name")
	private String name;

	@Column(name = "story_description")
	private String description;

	@Column(name = "story_date")
	private LocalDate creationDate;

	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonIgnoreProperties({ "id", "password", "email", "stories", "comments" })
	private User user;

	// Revoir les JsonIgnoreProperties
	@OneToMany(mappedBy = "story")
	private Set<BlocStory> blocStories = new HashSet<>();

	@OneToMany(mappedBy = "story")
	private Set<Comment> comments = new HashSet<>();

	public Story() {
	}

	public Story(Integer id, String name, String description, LocalDate creationDate, User user, Integer firstIdBloc,
			Set<BlocStory> blocStories, Set<Comment> comments) {
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.user = user;
		this.blocStories = blocStories;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public User getUser() {
		return user;
	}

	public Set<BlocStory> getBlocStories() {
		return blocStories;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBlocStories(Set<BlocStory> blocStories) {
		this.blocStories = blocStories;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
