package org.sff.proxy;

public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("保存数据入库");
    }
}
