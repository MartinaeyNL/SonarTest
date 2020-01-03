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

    public boolean doesChatLobbyExist(String name) {
        for(ChatLobby lobby : this.getAllLobbies()) {
            if(lobby.displayname.equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Main methods of the class
    public void addConnectedUser(String userId) {
        this.connectedUsers.add(new User(userId));
    }

    // Creation of the lobbies
    private void createNewLobbies(int amount) {
        for(int i = 0; i < amount; i++) {
            this.chatLobbies.add(new ChatLobby(("LobbyNr" + i)));
        }
    }

    /*-------------------------------------------------------*/

    // Web Socket methods
    public void addUserToLobby() {

    }


    /*-----------------------------------------------------*/

    // Get&set Methods
    public Collection<ChatLobby> getAllLobbies() { return this.chatLobbies; }
}
