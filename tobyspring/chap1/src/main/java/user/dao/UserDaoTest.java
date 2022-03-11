package user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import user.domain.User;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        UserDao dao = new NUserDao();
        // factory 클래스로 분리
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao dao = new UserDao(connectionMaker);
        // factory 클래스로 분리
        DaoFactory daoFactory = new DaoFactory();
        UserDao dao1 = daoFactory.userDao(); // 서로 다른 오브젝트
        UserDao dao2 = daoFactory.userDao(); // 서로 다른 오브젝트
        System.out.println("dao1 = " + dao1);
        System.out.println("dao2 = " + dao2);

        // 스프링 적용
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = ac.getBean("userDao", UserDao.class); // 동일 오브젝트 (싱글톤)
        UserDao dao3 = ac.getBean("userDao", UserDao.class); // 동일 오브젝트 (싱글톤)
        UserDao dao4 = ac.getBean("userDao", UserDao.class); // 동일 오브젝트 (싱글톤)
        System.out.println("dao3 = " + dao3);
        System.out.println("dao4 = " + dao4);

        User user = new User();
        user.setId("1");
        user.setName("Kakao");
        user.setPassword("1234");

        dao.add(user);
        System.out.println(user.getId() + " 등록 성공");

        User findUser = dao.get(user.getId());
        System.out.println(findUser.getName());
        System.out.println(findUser.getPassword());

        System.out.println(findUser.getId() + " 조회 성공");
    }
}
