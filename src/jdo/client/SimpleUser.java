package jdo.client;

import jdo.PlayerData;
import jdo.server.User;

/**
 * Created by LeoAsp on 2017-02-13.
 */
public class SimpleUser {
    String nickname;
    int portNr;
    PlayerData playerData;

    public SimpleUser(User user) {
        this.nickname = user.nickname;
        this.portNr = user.socket.getPort();
        this.playerData = user.playerData;
    }
}
