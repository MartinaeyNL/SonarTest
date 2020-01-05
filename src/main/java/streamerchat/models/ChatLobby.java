package streamerchat.models;

import java.util.ArrayList;
import java.util.Collection;

public class ChatLobby {

    // Variables
    private String displayname;
    private Collection<User> users;
    private Collection<String> chatLog;

    // Constructor
    public ChatLobby(String name) {
        this.displayname = name;
        this.users = new ArrayList<>();
        this.chatLog = new ArrayList<>();
    }

    // Get&Set Methods
    public String getDisplayname() { return displayname; }
    public Collection<User> getUsers() { return users; }
    public Collection<String> getChatLog() { return chatLog; }

    public void addUser(User user) throws Exception {
        if (isUserAlreadyInLobby(user)) {
            this.users.add(user);
            System.out.println("[LOG] User #" + user.getSessionId() + " joined ChatLobby " + this.displayname);
        }
    }

    public void removeUser(User user) { this.users.remove(user); }


    /*------------------------------------------------------------------------*/

    // Field and parameter checking
    private boolean isUserAlreadyInLobby(User user) throws Exception {
        if (this.users.size() > 0) {
            for (User loopUser : this.users) {
                if (loopUser.getSessionId().equals(user.getSessionId())) {
                    throw new Exception("You've already joined this lobby!");
                }
            }
        }
        return true;
    }
}
