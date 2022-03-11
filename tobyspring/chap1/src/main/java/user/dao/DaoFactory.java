package user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        // factory메소드는 UserDao를 어떻게 만들고 어떻게 준비시킬지를 결정한다.
        return new UserDao(connectionMaker());
    }

//    public AccountDao accountDao() {
//        // factory메소드는 UserDao를 어떻게 만들고 어떻게 준비시킬지를 결정한다.
//        return new AccountDao(connectionMaker());
//    }
//    public MessageDao messageDao() {
//        // factory메소드는 UserDao를 어떻게 만들고 어떻게 준비시킬지를 결정한다.
//        return new MessageDao(connectionMaker());
//    }

    @Bean
    public DConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
