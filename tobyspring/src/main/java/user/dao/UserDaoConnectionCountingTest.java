package user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.domain.User;

import java.sql.SQLException;

public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("1");
        user.setName("test");
        user.setPassword("1234");

        userDao.add(user);

        System.out.println("登録完了");

        User getUser = userDao.get(user.getId());

        System.out.println("getUser.getId() = " + getUser.getId());
        System.out.println("getUser.getName() = " + getUser.getName());
        System.out.println("getUser.getPassword() = " + getUser.getPassword());

        CountingConnectionMaker connectionMaker = applicationContext.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter = " + connectionMaker.getCounter());

    }
}
