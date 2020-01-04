package streamerchat.models;

import java.util.ArrayList;
import java.util.Collection;

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
    public User addConnectedUser(String userId) {
        User user = new User(userId);
        this.connectedUsers.add(user);
        return user;
    }

    // Creation of the lobbies
    private void createNewLobbies(int amount) {
        for(int i = 0; i < amount; i++) {
            this.chatLobbies.add(new ChatLobby(("LobbyNr" + i)));
        }
    }

    /*-------------------------------------------------------*/

    // Web Socket methods
    public void addUserToLobby(ChatLobby lobby, User user) {
        lobby.users.add(user);
    }


    /*-----------------------------------------------------*/

    // Get&set Methods
    public Collection<ChatLobby> getAllLobbies() { return this.chatLobbies; }
}
