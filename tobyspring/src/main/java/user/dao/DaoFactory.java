package user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    /*
    <bean class=UserDao id="userDao">
        <property name="connectionMaker" ref="connectionMaker" / >
    </bean>
     */

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }

    /*
    <bean id="connectionMaker" class=DConnectionMaker>
    </bean>
     */
    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
