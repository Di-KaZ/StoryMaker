package storyteam.server.story.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "creationDate")
	private LocalDate creationDate;

	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonIgnoreProperties({ "id", "password", "email", "stories", "comments" })
	private User user;

	@Column(name = "firstBlocId")
	private Integer firstBlocId;

	@Column(name = "cover")
	private String cover;

	@OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
	private Set<BlocStory> blocStories;

	@OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
	private List<Comment> comments;

	public Story() {
	}

	public Set<BlocStory> getBlocStories() {
		return blocStories;
	}

	public void setBlocStories(Set<BlocStory> blocStories) {
		this.blocStories = blocStories;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Story(Integer id, String name, String description, LocalDate creationDate, User user, BlocStory firstBloc) {
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.user = user;
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

	public Integer getFirstBlocId() {
		return firstBlocId;
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

	public void setFirstBlocId(Integer firstBlocId) {
		this.firstBlocId = firstBlocId;
	}
}
