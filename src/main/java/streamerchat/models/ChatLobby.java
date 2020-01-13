package streamerchat.models;

import java.util.ArrayList;
import java.util.Collection;

public class ChatLobby {

    // Variables
    private String displayname;
    private Collection<Session> sessions;
    private Collection<String> chatLog;

    // Constructor
    public ChatLobby(String name) {
        this.displayname = name;
        this.sessions = new ArrayList<>();
        this.chatLog = new ArrayList<>();
    }

    // Get&Set Methods
    public String getDisplayname() { return displayname; }
    public Collection<Session> getSessions() { return sessions; }
    public Collection<String> getChatLog() { return chatLog; }

    public void addUser(Session session) {
        if (!this.isUserAlreadyInLobby(session)) {
            this.sessions.add(session);
            System.out.println("[LOG] User #" + session.getSessionId() + " joined ChatLobby " + this.displayname);
        }
        else {
            throw new IllegalArgumentException("You've already joined this lobby!");
        }
    }

    public void removeUser(Session session) { this.sessions.remove(session); }


    /*------------------------------------------------------------------------*/

    // Field and parameter checking
    private boolean isUserAlreadyInLobby(Session session) {
        if (!this.sessions.isEmpty()) {
            for (Session loopSession : this.sessions) {
                if (loopSession.getSessionId().equals(session.getSessionId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
