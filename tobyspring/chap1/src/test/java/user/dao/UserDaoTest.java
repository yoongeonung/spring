package user.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import user.domain.User;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserDaoTest {

    private UserDao dao;
    private User wooah;
    private User naver;
    private User line;

    @BeforeEach
    void beforeEach() {
        final ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.dao = ac.getBean("userDao", UserDao.class);

        this.wooah = new User("1", "우형", "1234");
        this.naver = new User("2", "네이버", "1234");
        this.line = new User("3", "라인", "1234");
    }


    @Test
    void addAndGet() throws SQLException, ClassNotFoundException {
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
    void count() throws SQLException, ClassNotFoundException {


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
    void getUserFailure() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        assertThrows(EmptyResultDataAccessException.class, () -> dao.get("unknown_id"));
    }

}