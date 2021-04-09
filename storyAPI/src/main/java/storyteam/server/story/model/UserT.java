package storyteam.server.story.model;

public class UserT {
    private String username;
    private String userPassword;

    public UserT() {
    }

    public UserT(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
