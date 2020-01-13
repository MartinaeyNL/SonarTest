package streamerchat.models;

import java.util.ArrayList;
import java.util.Collection;

public class ChatLobby {

    // Variables
    private String displayName;
    private Collection<Session> sessions;
    private Collection<String> chatLog;

    // Constructor
    public ChatLobby(String name) {
        this.displayName = name;
        this.sessions = new ArrayList<>();
        this.chatLog = new ArrayList<>();
    }

    // Get&Set Methods
    public String getDisplayName() { return displayName; }
    public Collection<Session> getSessions() { return sessions; }
    public Collection<String> getChatLog() { return chatLog; }

    public void addUser(Session session) {
        if (!this.isUserAlreadyInLobby(session)) {
            this.sessions.add(session);
            System.out.println("[LOG] User #" + session.getSessionId() + " joined ChatLobby " + this.displayName);
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
