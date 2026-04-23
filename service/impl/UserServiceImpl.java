package service.impl;

import dao.impl.UserDAOImpl;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAOImpl dao = new UserDAOImpl();

    @Override
    public boolean register(User user) {
        return dao.register(user);
    }

    @Override
    public boolean login(String username, String password) {
        return dao.login(username, password);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public List<User> viewUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void deleteUser(int userId) {
        dao.deleteUser(userId);
    }
}
