package user.dao;

import user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao = daoFactory.userDao();

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
    }
}
