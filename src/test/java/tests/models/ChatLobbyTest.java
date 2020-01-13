package tests.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import streamerchat.models.ChatLobby;
import streamerchat.models.Session;

public class ChatLobbyTest {

    @Test
    public void addNullChatLobby() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new ChatLobby(null));
    }

    @Test
    public void removeUserFromLobby() {
        Assertions.assertDoesNotThrow(() -> {
            String displayName = "Test";
            ChatLobby lobby = new ChatLobby(displayName);
            Session session = new Session("randomSessionTextWhichIsUnique");
            lobby.addUser(session);
            Assertions.assertThrows(IllegalArgumentException.class, () -> lobby.addUser(session)); // Try second time for error
            lobby.removeUser(session);
            if(!lobby.getDisplayName().equals(displayName)) {
                Assertions.fail();
            }
        });
    }
}
