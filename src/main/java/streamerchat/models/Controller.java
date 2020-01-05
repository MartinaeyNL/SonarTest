package streamerchat.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class Controller {

    // Variables
    private Collection<ChatLobby> chatLobbies;
    private Collection<User> connectedUsers;  // List of users

    // Constructor
    public Controller() {
        this.chatLobbies = new ArrayList<>();
        this.connectedUsers = new ArrayList<>();
        this.createNewLobbies(5);
    }

    /*----------------------------------------------------------------------*/

    // Main methods of the class
    public void addConnectedUser(String sessionId) {
        User user = new User(sessionId);
        this.connectedUsers.add(user);
    }

    public User getConnectedUser(String sessionId) {
        Optional<User> oUser = this.connectedUsers.stream().filter(item -> item.getSessionId().equals(sessionId)).findFirst();
        return oUser.orElse(null);
    }

    public void removeConnectedUser(String sessionId) {
        User user = this.getConnectedUser(sessionId);
        if(user != null) {
            this.connectedUsers.remove(user);
        }
        else {
            System.out.println("AAAAHHHHHHHH USER NOT REMOVED AAAAAAAA");
        }
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
        Optional<ChatLobby> oLobby = this.chatLobbies.stream().filter(item -> item.displayname.equals(name)).findFirst();
        return oLobby.orElse(null);
    }

    // Web Socket methods
    public void addUserToLobby(String lobbyName, User user) {
        ChatLobby lobby = this.getChatLobby(lobbyName);
        lobby.users.add(user);
        System.out.println("User is [" + user + "]");
    }

    public void removeUserFromLobby(String lobbyName, User user) {
        ChatLobby lobby = this.getChatLobby(lobbyName);
        lobby.users.remove(user);
        System.out.println("User is [" + user + "]");
    }

    /*-----------------------------------------------------*/

    // Get&set Methods
    public Collection<ChatLobby> getAllLobbies() { return this.chatLobbies; }
}
