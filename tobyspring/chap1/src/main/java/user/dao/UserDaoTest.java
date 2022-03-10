package user.dao;

import user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // factory 클래스로 분리
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao dao = new UserDao(connectionMaker);
        // factory 클래스로 분리
        DaoFactory daoFactory = new DaoFactory();
        UserDao dao = daoFactory.userDao();
//        UserDao dao = new NUserDao();

        User user = new User();
        user.setId("1");
        user.setName("Kakao");
        user.setPassword("1234");

        dao.add(user);
        System.out.println(user.getId() + " 등록 성공");

        User findUser = dao.get(user.getId());
        System.out.println(findUser.getName());
        System.out.println(findUser.getPassword());

        System.out.println(findUser.getId() + " 조회 성공");
    }
}
