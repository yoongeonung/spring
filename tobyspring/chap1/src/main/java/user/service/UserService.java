package user.service;

import user.domain.User;

public interface UserService {
    void upgradeLevels();

    void add(User user);
}
