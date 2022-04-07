package user.dao;

import user.domain.User;

import java.sql.*;

public abstract class UserDao {

    private final SimpleConnectionMaker simpleConnectionMaker;

    public UserDao(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = simpleConnectionMaker.makeConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(id,name,password) values (?,?,?)");
        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection connection = simpleConnectionMaker.makeConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
        preparedStatement.setString(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();

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
