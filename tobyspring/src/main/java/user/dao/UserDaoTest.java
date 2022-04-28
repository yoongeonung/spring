package user.dao;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.SQLException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.domain.User;

public class UserDaoTest {

  @Test
  public void addAndGet() throws SQLException, ClassNotFoundException {
    ApplicationContext applicationContext = new GenericXmlApplicationContext(
        "applicationContext.xml");
    UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
    userDao.deleteAll();

    int count = userDao.getCount();
    assertThat(count, is(0));


    User user = new User("1", "userA", "1234");
    userDao.add(user);
    assertThat(userDao.getCount(), is(1));

    User findUser = userDao.get(user.getId());

    assertThat(findUser.getName(), is(user.getName()));
    assertThat(findUser.getPassword(), is(user.getPassword()));
    assertThat(findUser.getId(), is(user.getId()));
  }

}
