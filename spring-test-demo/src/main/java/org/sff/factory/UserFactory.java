package org.sff.factory;

import org.sff.bean.User;

public class UserFactory {
    public static User getUser() {
        return new User();
    }
}
