package user.dao;

import user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private ConnectionMaker connectionMaker;
    private DataSource dataSource;

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into users(id,name,password) values (?,?,?)");
        preparedStatement.setString(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public User get(String id) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();

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

    public void deleteAll() throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement ps = connection.prepareStatement("delete from users");
        ps.executeUpdate();

        ps.close();
        connection.close();
    }

    public int getCount() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement("select count(*) from users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1); // index가 1부터 시작

        rs.close();
        ps.close();
        connection.close();

        return count;
    }

}
