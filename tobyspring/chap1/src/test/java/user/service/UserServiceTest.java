package user.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import java.util.Arrays;
import java.util.List;

import static user.service.UserService.*;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(locations = "/applicationContext.xml")
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao dao;
    private List<User> users; // fixtrue

    private void checkLevel(User user, Boolean upgradable) {
        User savedUser = dao.get(user.getId());
        if (upgradable) {
            Assertions.assertThat(user.getLevel().nextLevel()).isEqualTo(savedUser.getLevel());
        } else {
            Assertions.assertThat(user.getLevel()).isEqualTo(savedUser.getLevel());
        }
    }

    @BeforeEach
    void beforeEach() {
        users = Arrays.asList(
                new User("1", "USER1", "1111", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0),
                new User("2", "USER2", "2222", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
                new User("3", "USER3", "3333", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD-1),
                new User("4", "USER4", "4444", Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
                new User("5", "USER5", "5555", Level.GOLD, 100, 100)
        );
    }

    @Test
    void upgradeLevels() {
        dao.deleteAll();
        Assertions.assertThat(dao.getAll().size()).isEqualTo(0);
        for (User user : users) {
            dao.add(user);
        }
        Assertions.assertThat(dao.getAll().size()).isEqualTo(5);

        userService.upgradeLevels();

        checkLevel(users.get(0), false);
        checkLevel(users.get(1), true);
        checkLevel(users.get(2), false);
        checkLevel(users.get(3), true);
        checkLevel(users.get(4), false);
    }

    @Test
    void add() {
        dao.deleteAll();

        // Level 초기설정없음
        User user1 = users.get(0);
        user1.setLevel(null);

        User user2 = users.get(4);    // Level 초기설정있음 (GOLD)

        userService.add(user1);
        userService.add(user2);

        User addedUser1 = dao.get(user1.getId());
        User addedUser2 = dao.get(user2.getId());

        Assertions.assertThat(addedUser1.getLevel()).isEqualTo(Level.BASIC);
        Assertions.assertThat(addedUser2.getLevel()).isEqualTo(user2.getLevel());


    }

}