package dao;

import domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void add(User user);
    User get(String id) throws SQLException;
    List<User> getAll();
    void deleteAll();
    int getCount();
}
