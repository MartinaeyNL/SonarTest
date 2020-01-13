package streamerchat.models;

public class User {

    // Variables
    private String displayName;
    private String twitchUserId;

    // Constructor
    public User(String displayName, String twitchUserId) {
        this.displayName = displayName;
        this.twitchUserId = twitchUserId;
    }

    // Get&Setters
    public String getDisplayName() { return this.displayName; }
    public String getTwitchUserId() { return this.twitchUserId; }
}
