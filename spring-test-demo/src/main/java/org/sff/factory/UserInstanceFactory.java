package org.sff.factory;

import org.sff.bean.User;

public class UserInstanceFactory {
    public User getUser() {
        return new User();
    }
}
