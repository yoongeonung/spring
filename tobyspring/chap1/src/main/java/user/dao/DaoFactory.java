package user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import user.domain.User;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        // factory메소드는 UserDao를 어떻게 만들고 어떻게 준비시킬지를 결정한다.
//        return new UserDao(connectionMaker());
        // 수정자 주입을 통한 연관관계 설정
        // xml파일에 프로퍼티설정이 용이하다.
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
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
