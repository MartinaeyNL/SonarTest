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

    /*------------------------------------------------*/

    public ChatLobby getChatLobby(String name) {
        for(ChatLobby lobby : this.getAllLobbies()) {
            if(lobby.displayname.equals(name)) {
                return lobby;
            }
        }
        return null;
    }

    // Main methods of the class
    public void addConnectedUser(String userId) {
        User user = new User(userId);
        this.connectedUsers.add(user);
    }

    public User getConnectedUser(String userId) {
        //Optional<Object> user = Arrays.stream(this.connectedUsers.toArray()).filter(item -> item.getClass() != null).findFirst();
        //return user;

        Optional<User> oUser = this.connectedUsers.stream().filter(item -> item.getSessionId().equals(userId)).findFirst();
        return oUser.orElse(null);
    }

    // Creation of the lobbies
    private void createNewLobbies(int amount) {
        for(int i = 0; i < amount; i++) {
            this.chatLobbies.add(new ChatLobby(("LobbyNr" + i)));
        }
    }

    /*-------------------------------------------------------*/

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
