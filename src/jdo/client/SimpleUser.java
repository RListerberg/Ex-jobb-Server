package jdo.client;

import jdo.server.User;

/**
 * Created by LeoAsp on 2017-02-13.
 */
public class SimpleUser {
    String nickname;

    public SimpleUser(User user) {
        this.nickname=user.nickname;
    }
}
