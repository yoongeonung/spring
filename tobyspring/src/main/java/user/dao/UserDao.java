package user.dao;

import user.domain.User;

import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(id,name,password) values (?,?,?)");
        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();

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
}
