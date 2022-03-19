package user.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.domain.Level;
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

    private UserDaoJdbc dao;
    /*
    fixture
     */
    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private ApplicationContext ac;
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void beforeEach() {
//        final ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
//        this.ac = new AnnotationConfigApplicationContext(DaoFactory.class); // test용

        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/tobyspring", "root", "00000000", true);

        System.out.println(" Application Context = " + this.ac);
        System.out.println(" this = " + this);

        dao = ac.getBean("userDao", UserDaoJdbc.class);
        dao.setDataSource(dataSource);

        this.user1 = new User("1", "우형", "1234", Level.BASIC, 1, 0);
        this.user2 = new User("2", "네이버", "1234", Level.SILVER, 55, 10);
        this.user3 = new User("3", "라인", "1234", Level.GOLD, 100, 40);

        dao.deleteAll();

        dao.add(user1);
        dao.add(user2);
        dao.add(user3);
    }


    @Test
    void addAndGet() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User kakaoUser = dao.get(user1.getId());
        // assertj 사용
        assertThat(user1.getName()).isEqualTo(kakaoUser.getName());
        assertThat(user1.getPassword()).isEqualTo(kakaoUser.getPassword());

        User lineUser = dao.get(user2.getId());
        assertThat(user2.getName()).isEqualTo(lineUser.getName());
        assertThat(user2.getPassword()).isEqualTo(lineUser.getPassword());

    }

    @Test
    void count() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
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

        dao.add(user1);

        List<User> users1 = dao.getAll();
        assertThat(users1.size()).isEqualTo(1);
        checkSameUser(user1, users1.get(0));

        dao.add(user2);

        List<User> users2 = dao.getAll();
        assertThat(users2.size()).isEqualTo(2);
        checkSameUser(user2, users2.get(1));

        dao.add(user3);

        List<User> users3 = dao.getAll();
        assertThat(users3.size()).isEqualTo(3);
        checkSameUser(user3, users3.get(2));


        List<User> users = dao.getAll();
        assertThat(users.size()).isEqualTo(3);
        checkSameUser(users.get(0), user1);
        checkSameUser(users.get(1), user2);
        checkSameUser(users.get(2), user3);

    }

    private void checkSameUser(User user1, User user2) {
        System.out.println(user1.getId() + user2.getId());
        System.out.println(user1.getLevel().intValue() + user2.getLevel().intValue());
        System.out.println(user1.getName() + user2.getName());
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
    }

    @Test
    void duplicateKet() {
        dao.deleteAll();

        dao.add(user1);
        assertThat(dao.getAll().size()).isEqualTo(1);

//        assertThrows(DataAccessException.class, () -> dao.add(user2));
    }

    @Test
    void sqlExceptionTranslate() {
        dao.deleteAll();

        dao.add(user1);
        assertThat(dao.getAll().size()).isEqualTo(1);

        try {
            dao.add(user2);
        } catch (DuplicateKeyException ex) {
            SQLException sqlEx = (SQLException) ex.getRootCause();
            SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(dataSource);
            assertThat(set.translate(null, null, sqlEx)).isInstanceOf(DuplicateKeyException.class);

        }
    }
}