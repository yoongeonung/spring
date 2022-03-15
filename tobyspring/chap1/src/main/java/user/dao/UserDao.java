package user.dao;

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
    @Setter
    private DataSource dataSource;

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

    public void add(User user) throws SQLException {
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
//        Connection c = connectionMaker.makeConnection();
        StatementStrategy strategy = new AddStatement(user);
        jdbcContextWithStatementStrategy(strategy);
    }

    public User get(String id) throws SQLException {
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
//        Connection c = connectionMaker.makeConnection();
        Connection c = dataSource.getConnection();

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

    // 클라이언트
    public void deleteAll() throws SQLException {
//        Connection c = connectionMaker.makeConnection();
        jdbcContextWithStatementStrategy(new DeleteAllStatement());
    }

    // 컨텍스트를 분리, 클라이언트가 전략을 선택하게끔 변경
    private void jdbcContextWithStatementStrategy(StatementStrategy strategy) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
//            ps = makeStatement(c);
            ps = strategy.makePreparedStatement(c);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close(); // 여기서도 exception이 발생 가능하므로 잡아줘야 한다.
                } catch (SQLException e) {
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    // 변하는 부분을 변하지 않는부분으로부터 추출;
    // 템플릿 메소드 패턴을 적용 추상메소드로 변환
//    public abstract PreparedStatement makeStatement(Connection c) throws SQLException;

    public int getCount() throws SQLException {
//        Connection c = connectionMaker.makeConnection();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = dataSource.getConnection();
            ps = c.prepareStatement("select COUNT(*) from USER");
            rs = ps.executeQuery();
            rs.next();

            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }

            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
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
