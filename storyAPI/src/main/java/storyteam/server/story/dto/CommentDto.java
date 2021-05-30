package storyteam.server.story.dto;

import java.time.LocalDate;

public class CommentDto {

    private Integer id;

    private String content;

    private LocalDate creationDate;

    private String username;

    private Integer story;

    public CommentDto(Integer id, String content, LocalDate creationDate, String username, Integer story) {
        this.id = id;
        this.content = content;
        this.creationDate = creationDate;
        this.username = username;
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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStory() {
        return this.story;
    }

    public void setStory(Integer story) {
        this.story = story;
    }

}