package user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

@Configuration
public class TestDaoFactory {
    @Bean
    public UserDao userDao() {
        // factory메소드는 UserDao를 어떻게 만들고 어떻게 준비시킬지를 결정한다.
        return new UserDao(connectionMaker());
    }

    @Bean
    public DConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    @Bean
    public DataSource dataSource() {
        return new SingleConnectionDataSource("jdbc:mysql://localhost/tobyspring", "root", "00000000", true);
    }
}
