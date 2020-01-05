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

    private void removeConnectedUser(User user) {
    }

    public void disconnectUser(String sessionId) {
        User user = this.getConnectedUser(sessionId);
        if(user == null) {
            System.out.println("OKAY THIS IS WEIRD THE USER DOESN'T EXIST!!! TAAADUUUUUUU TAAAADUUUUUUUUUUUUUUUUUUUU");
            return;
        }
        for(ChatLobby lobby : this.chatLobbies) {
            this.removeUserFromLobby(lobby.getDisplayname(), user);
        }
        this.connectedUsers.remove(user);
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
    public void addUserToLobby(String lobbyName, User user) throws Exception {
        ChatLobby lobby = this.getChatLobby(lobbyName);
        if(lobby == null) {
            throw new Exception("The lobby you wanted to join doesn't exist!");
        }
        lobby.addUser(user);
    }

    public void removeUserFromLobby(String lobbyName, User user) {
        ChatLobby lobby = this.getChatLobby(lobbyName);
        lobby.removeUser(user);
        System.out.println("User is [" + user + "]");
    }

    /*-----------------------------------------------------*/

    // Get&set Methods
    public Collection<ChatLobby> getAllLobbies() { return this.chatLobbies; }
}
