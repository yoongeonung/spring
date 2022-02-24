package dao;

import domain.User;

import java.sql.*;

public class UserDao {

    //    private static SimpleConnectionMaker simpleConnectionMaker;
    // 인터페이스를 의존하도록 변경, 추상에 의존
    private static ConnectionMaker connectionMaker;


    public UserDao() {
//        simpleConnectionMaker = new SimpleConnectionMaker();
        // 하지만 여기서 new해서 구체클래스에 의존하고있다.
        connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        // 중복 제거, 메소드로 추출
//        Connection c = getConnection();
        // 추상클래스를 제거, 클래스로 분리후 참조
//        Connection c = simpleConnectionMaker.makeNewConnection();
        Connection c = connectionMaker.makeConnection(); // 인터페이스(추상)에 의존
        PreparedStatement ps = c.prepareStatement( "insert into user(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException{
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("select * from user where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    // 관심사의 분리
//    private Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/tobyspring", "root", "00000000");
//        return c;
//    }
    // 관심사의 분리 + 추상화
//    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
