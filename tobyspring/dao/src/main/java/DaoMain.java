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
    }
}
