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

    // Methods
    public String getDisplayname() { return displayname; }
    public Collection<User> getUsers() { return users; }
    public Collection<String> getChatLog() { return chatLog; }

    public void addUser(User user) { this.users.add(user); }
    public void removeUser(User user) { this.users.remove(user); }
}
