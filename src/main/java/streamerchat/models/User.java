package streamerchat.models;

public class User {

    // Variables
    private String sessionId;

    // Constructor
    public User(String sessionId) {
        this.sessionId = sessionId;
    }

    /*--------------------------------------------*/

    // Get&set methods
    public String getSessionId() { return this.sessionId; }


}
