package con.jdbcconnectivity.BookstoreManagement.service.impl;

import con.jdbcconnectivity.BookstoreManagement.dao.impl.UserDAOImpl;
import con.jdbcconnectivity.BookstoreManagement.model.User;

public class UserServiceImpl {

    private UserDAOImpl dao = new UserDAOImpl();

    public boolean register(User user) {
        return dao.register(user);
    }

    public boolean login(String username, String password) {
        return dao.login(username, password);
    }
}
