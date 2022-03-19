package user.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.domain.Level;
import user.domain.User;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest2 {

    @Autowired
    private UserDao userDao;
    private User user1;

    @BeforeEach
    void beforeEach() {
        // fixture 등록
        user1 = new User("1", "USER_1","1111", Level.BASIC, 1, 0);
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
    }

    @Test
    void update() {
        userDao.deleteAll();
        assertThat(userDao.getAll().size()).isEqualTo(0);

        userDao.add(user1);

        user1.setName("FIRST_USER");
        user1.setPassword("1111_1111");
        user1.setLogin(100);
        user1.setRecommend(50);
        user1.setLevel(Level.SILVER);

        User updatedUser = userDao.update(user1);
        checkSameUser(user1, updatedUser);
    }


}
