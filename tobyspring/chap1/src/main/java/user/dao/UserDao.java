package user.dao;

import user.domain.User;

import java.util.List;

public interface UserDao {
    void add(final User user);

    User get(String id);

    void deleteAll();

    Integer getCount();

    List<User> getAll();
}
