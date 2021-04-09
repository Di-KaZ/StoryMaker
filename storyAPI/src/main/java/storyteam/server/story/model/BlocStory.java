package storyteam.server.story.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bloc_story")
@JsonIgnoreProperties({"story"})
public class BlocStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bloc_story_id")
    private Integer id;

    @Column(name = "bloc_story_name")
    private String name;

    @Column(name = "bloc_story_text")
    private String text;

    //Revoir les JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "story_Id")
    @JsonIgnoreProperties({"user", "blocStories", "story"})
    private Story story;

    @Column(name="previousIdBloc")
    private Integer previousIdBloc;

    public BlocStory() {
    }

    public BlocStory(Integer id, String name, String text, Story story, Integer previousIdBloc) {
        this.id = id;
        this.name = name;
        this.story = story;
        this.text = text;
        this.previousIdBloc = previousIdBloc;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Integer getPreviousIdBloc(){
        return previousIdBloc;
    }

    public void setPreviousIdBloc(Integer previousIdBloc) {
        this.previousIdBloc = previousIdBloc;
    }
}
