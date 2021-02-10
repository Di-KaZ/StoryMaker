package storyteam.server.story.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    //Revoir les JsonIgnoreProperties
    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"story"})
    private Set<BlocStory> BlocStories = new HashSet<>();

    public Story() {
    }

    public Story(Integer id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<BlocStory> getBlocStories() {
        return BlocStories;
    }

    public void setBlocStories(Set<BlocStory> blocStories) {
        BlocStories = blocStories;
    }
}
