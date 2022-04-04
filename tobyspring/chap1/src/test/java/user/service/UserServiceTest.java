package user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;
import static user.service.UserServiceImpl.*;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(locations = "/applicationContext.xml")
class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserDao dao;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private MailSender mailSender;

    private List<User> users; // fixtrue

    private void checkLevel(User user, Boolean upgraded) {
        User savedUser = dao.get(user.getId());
        if (upgraded) {
            assertThat(savedUser.getLevel()).isEqualTo(user.getLevel().nextLevel());
        } else {
            assertThat(savedUser.getLevel()).isEqualTo(user.getLevel());
        }
    }

    @BeforeEach
    void beforeEach() {
        users = Arrays.asList(
                new User("1", "USER1", "1111", "abcd@gmail.com",Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0),
                new User("2", "USER2", "2222", "abcd@gmail.com",Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
                new User("3", "USER3", "3333", "abcd@gmail.com",Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD-1),
                new User("4", "USER4", "4444", "abcd@gmail.com",Level.SILVER, 60, MIN_RECOMMEND_FOR_GOLD),
                new User("5", "USER5", "5555", "abcd@gmail.com",Level.GOLD, 100, 100)
        );
    }

    @Test
    @DirtiesContext
    void upgradeLevels() throws Exception {
        dao.deleteAll();
        assertThat(dao.getAll().size()).isEqualTo(0);
        for (User user : users) {
            dao.add(user);
        }
        assertThat(dao.getAll().size()).isEqualTo(5);

        MockMailSender mockMailSender = new MockMailSender();
        userService.setMailSender(mockMailSender); // context의 di설정을 변경 -> @DirtiesContext 표기해줘야함

        userService.upgradeLevels();

        checkLevel(users.get(0), false);
        checkLevel(users.get(1), true);
        checkLevel(users.get(2), false);
        checkLevel(users.get(3), true);
        checkLevel(users.get(4), false);

        List<String> request = mockMailSender.getRequest();
        assertThat(request.size()).isEqualTo(2);
        assertThat(request.get(0)).isEqualTo(users.get(1).getEmail());
        assertThat(request.get(1)).isEqualTo(users.get(3).getEmail());
    }

    @Test
    void add() {
        dao.deleteAll();

        // Level 초기설정없음
        User user1 = users.get(0);
        user1.setLevel(null);

        User user2 = users.get(4);    // Level 초기설정있음 (GOLD)

        userService.add(user1);
        userService.add(user2);

        User addedUser1 = dao.get(user1.getId());
        User addedUser2 = dao.get(user2.getId());

        assertThat(addedUser1.getLevel()).isEqualTo(Level.BASIC);
        assertThat(addedUser2.getLevel()).isEqualTo(user2.getLevel());


    }

    @Test
    void upgradeAllOrNothing() {
        TestUserService testUserService = new TestUserService(users.get(3).getId());
        testUserService.setUserDao(dao);
        testUserService.setMailSender(mailSender);

        UserServiceTx txUerService = new UserServiceTx();
        txUerService.setTransactionManager(transactionManager);
        txUerService.setUserService(testUserService);

        dao.deleteAll();
        for (User user : users) {
            dao.add(user);
        }

        try {
            txUerService.upgradeLevels();
            fail("TestUserServiceException expected");
        } catch (Exception e) {
        }
        checkLevel(users.get(1), false);
    }

    private static class TestUserService extends UserServiceImpl {
        private String id;

        public TestUserService(String id) {
            this.id = id;
        }

        @Override
        protected void upgradeLevel(User user) {
            if (user.getId().equals(id)) {
                throw new TestUserServiceException();
            }
            super.upgradeLevel(user);
        }
    }

    static class TestUserServiceException extends RuntimeException {
    }

    static class MockMailSender implements MailSender {
        private List<String> request = new ArrayList<>();

        public List<String> getRequest() {
            return request;
        }

        @Override
        public void send(SimpleMailMessage simpleMessage) throws MailException {
            request.add(simpleMessage.getTo()[0]);
        }

        @Override
        public void send(SimpleMailMessage... simpleMessages) throws MailException {

        }
    }

}

