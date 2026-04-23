package service;

import java.util.List;

import model.User;

public interface UserService {
    boolean register(User user);
    boolean login(String username, String password);
    void addUser(User user);
    List<User> viewUsers();
    void deleteUser(int userId);
}
