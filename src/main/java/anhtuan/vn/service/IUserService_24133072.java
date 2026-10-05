package anhtuan.vn.service;

import anhtuan.vn.entity.User_24133072;

public interface IUserService_24133072 {

    User_24133072 findByUsername(String username);

    User_24133072 findByEmail(String email);

    User_24133072 login(String username, String password);

    boolean register(User_24133072 user);

    boolean activate(String username);

    void update(User_24133072 user);
}