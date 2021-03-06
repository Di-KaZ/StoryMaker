package storyteam.server.story.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import storyteam.server.story.dto.StoryDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "story")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Integer  id;

    @Column(name = "story_name")
    private String name;

    @Column(name = "story_description")
    private String description;

    @Column(name = "story_date")
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    //Revoir les JsonIgnoreProperties
    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"story"})
    private Set<BlocStory> BlocStories = new HashSet<>();

    public Story() {
    }

    public Story(Integer id, String name, String description, LocalDate creationDate, User user, Set<BlocStory> blocStories) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.user = user;
        BlocStories = blocStories;
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
        return BlocStories;
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
        BlocStories = blocStories;
    }
}