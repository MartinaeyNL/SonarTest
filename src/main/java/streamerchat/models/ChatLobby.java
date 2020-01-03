package streamerchat.models;

import java.util.ArrayList;
import java.util.Collection;

public class ChatLobby {

    // Variables
    public String displayname;
    public Collection<User> users;
    public Collection<String> chatLog;

    // Constructor
    public ChatLobby(String name) {
        this.displayname = name;
        this.users = new ArrayList<>();
        this.chatLog = new ArrayList<>();
    }
}
