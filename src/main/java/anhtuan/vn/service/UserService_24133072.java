package anhtuan.vn.service;

import anhtuan.vn.dao.IUserDAO_24133072;
import anhtuan.vn.dao.UserDAO_24133072;
import anhtuan.vn.entity.User_24133072;

public class UserService_24133072
        implements IUserService_24133072 {

    private final IUserDAO_24133072 userDAO =
            new UserDAO_24133072();

    @Override
    public User_24133072 findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User_24133072 findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User_24133072 login(String username, String password) {

        User_24133072 user =
                userDAO.findByUsername(username);

        if (user == null) {
            return null;
        }

        if (user.getPassword() == null
                || !user.getPassword().equals(password)) {
            return null;
        }

        if (!Boolean.TRUE.equals(user.getActive())) {
            return null;
        }

        return user;
    }

    @Override
    public boolean register(User_24133072 user) {

        if (userDAO.findByUsername(user.getUsername()) != null) {
            return false;
        }

        if (userDAO.findByEmail(user.getEmail()) != null) {
            return false;
        }

        user.setAdmin(false);
        user.setActive(false);

        userDAO.insert(user);

        return true;
    }

    @Override
    public boolean activate(String username) {

        User_24133072 user =
                userDAO.findByUsername(username);

        if (user == null) {
            return false;
        }

        user.setActive(true);
        userDAO.update(user);

        return true;
    }

    @Override
    public void update(User_24133072 user) {
        userDAO.update(user);
    }
}