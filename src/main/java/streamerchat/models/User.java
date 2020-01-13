package streamerchat.models;

public class User {

    // Variables
    private String displayname;
    private String twitchUserId;

    // Constructor
    public User(String displayname, String twitchUserId) {
        this.displayname = displayname;
        this.twitchUserId = twitchUserId;
    }

    // Get&Setters
    public String getDisplayname() { return this.displayname; }
    public String getTwitchUserId() { return this.twitchUserId; }
}
