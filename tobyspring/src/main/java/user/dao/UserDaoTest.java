package user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
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

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao1 = daoFactory.userDao();
        UserDao userDao2 = daoFactory.userDao();

        System.out.println("userDao1 = " + userDao1);
        System.out.println("userDao2 = " + userDao2);

        UserDao userDao3 = applicationContext.getBean("userDao", UserDao.class);
        UserDao userDao4 = applicationContext.getBean("userDao", UserDao.class);

        System.out.println("userDao3 = " + userDao3);
        System.out.println("userDao4 = " + userDao4);
    }
}
