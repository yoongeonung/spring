package user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.domain.User;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = ac.getBean("userDao", UserDao.class);

        dao.add(new User("1","kakao", "1231"));
        dao.add(new User("2","cakao", "1232"));
        dao.add(new User("3","sakao", "1233"));
        dao.add(new User("4","dakao", "1234"));
        dao.add(new User("5","zakao", "1235"));

        CountingConnectionMaker ccm = ac.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("count = " + ccm.getCount());

    }
}
