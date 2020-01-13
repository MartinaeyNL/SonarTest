import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import streamerchat.models.ChatLobby;

public class ChatLobbyTest {

    @Test
    public void addNewChatLobby() {
        ChatLobby lobby = new ChatLobby(null);
        Assertions.assertNull(lobby.getDisplayName());
    }
}
