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
    // fixture
    private User user1;
    private User user2;

    @BeforeEach
    void beforeEach() {
        // fixture 등록
        user1 = new User("1", "USER_1","1111", "abcd@gmail.com", Level.BASIC, 1, 0);
        user2 = new User("2", "USER_2","2222", "abcd@gmail.com", Level.SILVER, 19, 5);
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
        assertThat(user1.getEmail()).isEqualTo(user2.getEmail());
    }

    @Test
    void update() {
        userDao.deleteAll();
        assertThat(userDao.getAll().size()).isEqualTo(0);

        userDao.add(user1); // 수정 할 사용자
        userDao.add(user2); // 수정 하지 않을 사용자

        user1.setName("FIRST_USER");
        user1.setPassword("1111_1111");
        user1.setEmail("abcd@gmail.com");
        user1.setLogin(100);
        user1.setRecommend(50);
        user1.setLevel(Level.SILVER);

        userDao.update(user1);
        User updatedUser = userDao.get(user1.getId());
        checkSameUser(user1, updatedUser);

        User findUser2 = userDao.get(user2.getId());
        checkSameUser(user2, findUser2);
    }


}
