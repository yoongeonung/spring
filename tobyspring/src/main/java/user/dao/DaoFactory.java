package user.dao;

public class DaoFactory {

    public UserDao userDao() {
        DConnectionMaker connectionMaker = new DConnectionMaker();
        return new UserDao(connectionMaker);
    }
}
