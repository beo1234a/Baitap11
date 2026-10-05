package anhtuan.vn.dao;

import anhtuan.vn.entity.User_24133072;

public interface IUserDAO_24133072 {

    User_24133072 findByUsername(String username);

    User_24133072 findByEmail(String email);

    void insert(User_24133072 user);

    void update(User_24133072 user);
}