package streamerchat.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Controller {

    // Variables
    private Collection<ChatLobby> chatLobbies;
    private Collection<Session> connectedSessions;  // List of users

    // Constructor
    public Controller() {
        this.chatLobbies = new ArrayList<>();
        this.connectedSessions = new ArrayList<>();
        this.createNewLobbies(5);
    }

    /*----------------------------------------------------------------------*/

    // Main methods of the class
    public void addConnectedUser(String sessionId) {
        Session session = new Session(sessionId);
        this.connectedSessions.add(session);
    }

    public Session getConnectedUser(String sessionId) {
        Optional<Session> oUser = this.connectedSessions.stream().filter(item -> item.getSessionId().equals(sessionId)).findFirst();
        return oUser.orElse(null);
    }

    public Collection<Session> getAllUsers() {
        return this.connectedSessions;
    }

    public void disconnectUser(String sessionId) {
        Session session = this.getConnectedUser(sessionId);
        if(session == null) {
            System.out.println("OKAY THIS IS WEIRD THE USER DOESN'T EXIST!!! TAAADUUUUUUU TAAAADUUUUUUUUUUUUUUUUUUUU");
            return;
        }
        for(ChatLobby lobby : this.chatLobbies) {
            this.removeUserFromLobby(lobby.getDisplayname(), session);
        }
        this.connectedSessions.remove(session);
    }

    /*----------------------------------------------------------------------*/

    // Creation of the lobbies
    private void createNewLobbies(int amount) {
        for(int i = 0; i < amount; i++) {
            this.chatLobbies.add(new ChatLobby(("LobbyNr" + i)));
        }
    }

    // Return the lobby with that name
    public ChatLobby getChatLobby(String name) {
        Optional<ChatLobby> oLobby = this.chatLobbies.stream().filter(item -> item.getDisplayname().equals(name)).findFirst();
        return oLobby.orElse(null);
    }

    // Web Socket methods
    public void addUserToLobby(String lobbyName, Session session) throws Exception {
        ChatLobby lobby = this.getChatLobby(lobbyName);
        if(lobby == null) {
            throw new Exception("The lobby you wanted to join doesn't exist!");
        }
        lobby.addUser(session);
    }

    public void removeUserFromLobby(String lobbyName, Session session) {
        ChatLobby lobby = this.getChatLobby(lobbyName);
        lobby.removeUser(session);
        System.out.println("User is [" + session + "]");
    }

    /*-----------------------------------------------------*/

    // Get&set Methods
    public Collection<ChatLobby> getAllLobbies() { return this.chatLobbies; }
}
