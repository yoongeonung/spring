package user.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {TestDaoFactory.class})
@ContextConfiguration(locations = "/applicationContext.xml")
@DirtiesContext
class UserDaoTest {

    private UserDao dao;
    private User wooah;
    private User naver;
    private User line;

    @Autowired
    private ApplicationContext ac;

    @BeforeEach
    void beforeEach() {
//        final ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
//        this.ac = new AnnotationConfigApplicationContext(DaoFactory.class); // test용

        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/tobyspring", "root", "00000000", true);

        System.out.println(" Application Context = " + this.ac);
        System.out.println(" this = " + this);

        dao = ac.getBean("userDao", UserDao.class);
        dao.setDataSource(dataSource);

        this.wooah = new User("1", "우형", "1234");
        this.naver = new User("2", "네이버", "1234");
        this.line = new User("3", "라인", "1234");
    }


    @Test
    void addAndGet() throws SQLException {
        User kakao = new User("1", "카카오", "1234");
        User line = new User("2", "라인", "1234");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(kakao);
        dao.add(line);
        assertThat(dao.getCount()).isEqualTo(2);

        User kakaoUser = dao.get(kakao.getId());
        // assertj 사용
        assertThat(kakao.getName()).isEqualTo(kakaoUser.getName());
        assertThat(kakao.getPassword()).isEqualTo(kakaoUser.getPassword());

        User lineUser = dao.get(line.getId());
        assertThat(line.getName()).isEqualTo(lineUser.getName());
        assertThat(line.getPassword()).isEqualTo(lineUser.getPassword());

    }

    @Test
    void count() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(wooah);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(naver);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(line);
        assertThat(dao.getCount()).isEqualTo(3);
    }

    @Test
    void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("unknown_id"));
    }

    @Test
    void getAll() throws SQLException {
        dao.deleteAll();

        List<User> users0 = dao.getAll();
        assertThat(users0.size()).isEqualTo(0); // 아무것도 없을때 예외대신 빈 리스트가 돌아온다.

        User user1 = new User();
        user1.setId("ccc");
        user1.setName("kakao");
        user1.setPassword("pangyo");
        dao.add(user1);

        List<User> users1 = dao.getAll();
        assertThat(users1.size()).isEqualTo(1);
        checkSameUser(user1, users1.get(0));

        User user2 = new User();
        user2.setId("bbb");
        user2.setName("line");
        user2.setPassword("bundang");
        dao.add(user2);

        List<User> users2 = dao.getAll();
        assertThat(users2.size()).isEqualTo(2);
        checkSameUser(user2, users2.get(0));

        User user3 = new User();
        user3.setId("aaa");
        user3.setName("naver");
        user3.setPassword("bundang");
        dao.add(user3);

        List<User> users3 = dao.getAll();
        assertThat(users3.size()).isEqualTo(3);
        checkSameUser(user3, users3.get(0));


        List<User> users = dao.getAll();
        assertThat(users.size()).isEqualTo(3);
        checkSameUser(users.get(0), user3);
        checkSameUser(users.get(1), user2);
        checkSameUser(users.get(2), user1);

    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
    }

}