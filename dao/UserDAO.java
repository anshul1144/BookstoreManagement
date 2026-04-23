package dao;

import java.util.List;

import model.User;

public interface UserDAO {
    boolean register(User user);
    boolean login(String username, String password);
    void addUser(User user);
    List<User> getAllUsers();
    void deleteUser(int userId);
}
