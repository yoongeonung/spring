package user.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.dao.EmptyResultDataAccessException;
import user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class UserDao {

    //    private final SimpleConnectionMaker connectionMaker;
    @Setter
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
//        simpleConnectionMaker = new SimpleConnectionMaker();
//        connectionMaker = new DConnectionMaker();
        this.connectionMaker = connectionMaker; // 생성자 DI

        /*
        * Dependency Lookup -> 컨테이너로부터 직접 찾아오는것
        * 스프링에서는 getBean() 메소드를 이용해서 DL 가능

        DaoFactory factory = new DaoFactory();
        this.connectionMaker = factory.connectionMaker();
         */
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("insert into USER (id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("select * from USER where id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }

        rs.close();
        ps.close();
        c.close();

        if (user == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return user;
    }

    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("delete from USER");
        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public int getCount() throws SQLException, ClassNotFoundException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("select COUNT(*) from USER");
        ResultSet rs = ps.executeQuery();
        rs.next();

        int count = rs.getInt(1);
        rs.close();
        ps.close();
        c.close();

        return count;
    }

    // extract method
//    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        UserDao dao = new UserDao();
////        UserDao dao = new NUserDao();
//
//        User user = new User();
//        user.setId("1");
//        user.setName("Kakao");
//        user.setPassword("1234");
//
//        dao.add(user);
//        System.out.println(user.getId() + " 등록 성공");
//
//        User findUser = dao.get(user.getId());
//        System.out.println(findUser.getName());
//        System.out.println(findUser.getPassword());
//
//        System.out.println(findUser.getId() + " 조회 성공");
//    }
}
