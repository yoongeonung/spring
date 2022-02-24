import dao.UserDao;
import domain.User;

import java.sql.SQLException;

public class DaoMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("11");
        user.setName("TestUser");
        user.setPassword("1111");

        userDao.add(user);
        System.out.println(user.getId() + " 등록 성공");

        User user2 = userDao.get("11");
        System.out.println("user2.getId() = " + user2.getId());
        System.out.println("user2.getName() = " + user2.getName());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
