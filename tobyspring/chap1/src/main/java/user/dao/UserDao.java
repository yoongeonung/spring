package user.dao;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
public class UserDao {

    //    private final SimpleConnectionMaker connectionMaker;
    @Setter
    private ConnectionMaker connectionMaker;
    private DataSource dataSource;
    @Setter
    private JdbcContext jdbcContext;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

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

    public void add(final User user) throws SQLException {
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
//        Connection c = connectionMaker.makeConnection();

        // 로컬클래스로 변경
//        class AddStatement implements StatementStrategy {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into USER (id, name, password) values (?,?,?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//                return ps;
//            }
//        }

        // 익명 클래스로 변경
//        StatementStrategy strategy = new AddStatement();
//        jdbcContextWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into USER (id, name, password) values (?,?,?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//                return ps;
//            }
//        });

//        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                PreparedStatement ps = c.prepareStatement("insert into USER (id, name, password) values (?,?,?)");
//                ps.setString(1, user.getId());
//                ps.setString(2, user.getName());
//                ps.setString(3, user.getPassword());
//                return ps;
//            }
//        });

        // jdbcTemplate 사용
        jdbcTemplate.update("insert into USER (id, name, password) values (?,?,?)", user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id) throws SQLException {
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
//        Connection c = connectionMaker.makeConnection();

        // jdbcTemplate 적용
        return jdbcTemplate.queryForObject("select * from USER where id = ?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }, id);


//        Connection c = dataSource.getConnection();
//
//        PreparedStatement ps = c.prepareStatement("select * from USER where id = ?");
//        ps.setString(1, id);
//        ResultSet rs = ps.executeQuery();
//
//        User user = null;
//        if (rs.next()) {
//            user = new User();
//            user.setId(rs.getString("id"));
//            user.setName(rs.getString("name"));
//            user.setPassword(rs.getString("password"));
//        }
//
//        rs.close();
//        ps.close();
//        c.close();
//
//        if (user == null) {
//            throw new EmptyResultDataAccessException(1);
//        }
//        return user;
    }

    // 클라이언트
    public void deleteAll() throws SQLException {
//        Connection c = connectionMaker.makeConnection();
//        jdbcContextWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                return c.prepareStatement("delete from USER");
//            }
//        });
        // 직접만든 템플릿 / 콜백 패턴
//        jdbcContext.executeSQL("delete from USER");

//         jdbcTemplate 사용 1
//        this.jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                return con.prepareStatement("delete from USER");
//            }
//        });

        // jdbcTemplate 사용 2
        jdbcTemplate.update("delete from USER");
    }

    // 템플릿으로 이전
//    private void executeSQL(final String sql) throws SQLException {
//        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
//            @Override
//            public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//                return c.prepareStatement("delete from USER");
//            }
//        });
//    }

    // 컨텍스트를 분리, 클라이언트가 전략을 선택하게끔 변경
    // 별도 클래스로 분리
//    private void jdbcContextWithStatementStrategy(StatementStrategy strategy) throws SQLException {
//        Connection c = null;
//        PreparedStatement ps = null;
//
//        try {
//            c = dataSource.getConnection();
////            ps = makeStatement(c);
//            ps = strategy.makePreparedStatement(c);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if (ps != null) {
//                try {
//                    ps.close(); // 여기서도 exception이 발생 가능하므로 잡아줘야 한다.
//                } catch (SQLException e) {
//                }
//            }
//            if (c != null) {
//                try {
//                    c.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//    }

    // 변하는 부분을 변하지 않는부분으로부터 추출;
    // 템플릿 메소드 패턴을 적용 추상메소드로 변환
//    public abstract PreparedStatement makeStatement(Connection c) throws SQLException;

    public Integer getCount() throws SQLException {
//        Connection c = connectionMaker.makeConnection();

        // jdbcTemplate 1, queryForInt() 는 deprecated 됨.
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement("select COUNT(*) from USER");
            }
        }, new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                return rs.getInt(1); // 1부터 시작
            }
        });

//        Connection c = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            c = dataSource.getConnection();
//            ps = c.prepareStatement("select COUNT(*) from USER");
//            rs = ps.executeQuery();
//            rs.next();
//
//            return rs.getInt(1);
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                }
//            }
//
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) {
//                }
//            }
//
//            if (c != null) {
//                try {
//                    c.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
    }

    public List<User> getAll() {
        return jdbcTemplate.query("select * from USER order by id", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
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