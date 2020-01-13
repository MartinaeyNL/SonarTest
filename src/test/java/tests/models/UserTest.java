package tests.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import streamerchat.models.User;

public class UserTest {

    @Test
    public void newNullUser() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(null, null));
    }

    @Test
    public void newValidUser() {
        String displayname = "Martini";
        String twitchUserId = "ARandomIdThatDoesntMeanAnythingAtAll";
        User user = new User(displayname, twitchUserId);
        Assertions.assertEquals(user.getDisplayName(), displayname);
        Assertions.assertEquals(user.getTwitchUserId(), twitchUserId);
    }
}
