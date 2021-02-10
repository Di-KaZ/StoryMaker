package storyteam.server.story.model;

import javax.persistence.*;

@Entity
@Table(name = "story_character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Integer id;

    @Column(name = "character_name")
    private String name;

    @Column(name = "character_stat")
    private String stat;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    public Character() {
    }

    public Character(Integer id, String name, String stat, Story story) {
        this.id = id;
        this.name = name;
        this.stat = stat;
        this.story = story;
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

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
