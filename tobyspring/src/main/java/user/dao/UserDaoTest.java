package user.dao;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import user.domain.User;

public class UserDaoTest {

  private UserDao userDao;

  @Before
  public void setUp() {
    ApplicationContext applicationContext = new GenericXmlApplicationContext(
        "applicationContext.xml");
    this.userDao = applicationContext.getBean("userDao", UserDao.class);
  }

  @Test
  public void addAndGet() throws SQLException, ClassNotFoundException {
    User user1 = new User("1", "userA", "1234");
    User user2 = new User("2", "userB", "5678");

    userDao.deleteAll();
    assertThat(userDao.getCount(), is(0));

    userDao.add(user1);
    userDao.add(user2);
    assertThat(userDao.getCount(), is(2));

    User getUser1 = userDao.get(user1.getId());
    assertThat(getUser1.getName(), is(user1.getName()));
    assertThat(getUser1.getPassword(), is(user1.getPassword()));

    User getUser2 = userDao.get(user2.getId());
    assertThat(getUser2.getName(), is(user2.getName()));
    assertThat(getUser2.getPassword(), is(user2.getPassword()));
  }

  @Test
  public void count() throws SQLException, ClassNotFoundException {
    User user1 = new User("1", "userA", "1111");
    User user2 = new User("2", "userB", "2222");
    User user3 = new User("3", "userC", "3333");
    // OPERATE
    // CHECK
    userDao.deleteAll();
    assertThat(userDao.getCount(), is(0));

    userDao.add(user1);
    assertThat(userDao.getCount(), is(1));

    userDao.add(user2);
    assertThat(userDao.getCount(), is(2));

    userDao.add(user3);
    assertThat(userDao.getCount(), is(3));
  }

  @Test(expected = EmptyResultDataAccessException.class)
  public void getUserFailure() throws SQLException {
    userDao.deleteAll();
    assertThat(userDao.getCount(), is(0));

    userDao.get("unknown_id"); // 예외 발생
  }

}
