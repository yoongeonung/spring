package user.service;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    private UserDao userDao;
    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }



    public void upgradeLevels() throws Exception {
        // template-callback pattern의 도입
        // 이 경우에는 템플릿/콜백 패턴이 어울리지 않음
        // 템플릿 / 콜백 패턴의 경우 객채내에 이미 굳어져있는 전체적인 흐름중에
        // 일부 기능을 바꿔서 구현해야할 경우에 적합함.
        // 이 경우에는 전체 흐름이 아니라 읽정 기능안에서의 전략을 유연하게 바꿔줘야 함으로 DI를 통한 전략패턴이 적절하다.
//        UserLevelUpgradeCallback callback = new UserLevelUpgradeCallback() {
//            @Override
//            public boolean canUpgradeLevel(User user) {
//                Level currentLevel = user.getLevel();
//                switch (currentLevel) {
//                    case BASIC:
//                        return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
//                    case SILVER:
//                        return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
//                    case GOLD:
//                        return false;
//                    default:
//                        throw new IllegalArgumentException("Unknown Level : " + currentLevel);
//                }
//            }
//            @Override
//            public void upgradeLevel(User user) {
//                user.upgradeLevel();
//                userDao.update(user);
//            }
//        };
//        upgradeLevelTemplate(callback);

        // 트랜잭션 동기화 매니저 초기화
//        TransactionSynchronizationManager.initSynchronization();
        //
//        Connection c = DataSourceUtils.getConnection(dataSource);
//        c.setAutoCommit(false);

        // JDBC 트랜잭션 추상 오브젝트 생성
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        // 트랜잭션 시작
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            // 트랜잭션 안에서 진행되는 작업
            List<User> users = userDao.getAll();
            for (User user : users) {
                if (canUpgradeLevel(user)) {
                    upgradeLevel(user);
                }
            }
            // 정상적으로 작업을 마치면 커밋
            transactionManager.commit(status);
        } catch (Exception e) {
            // 예외 발생시 롤백
            transactionManager.rollback(status);
            throw e;
        }


        // 1. 원래의 복잡한 로직 코드
//        for (User user : users) {
//            Boolean changed = null;
//            if (user.getLevel() == Level.BASIC && user.getLogin() >= 50) {
//                user.setLevel(Level.SILVER);
//                changed = true;
//            } else if (user.getLevel() == Level.SILVER && user.getRecommend() >= 30) {
//                user.setLevel(Level.GOLD);
//                changed = true;
//            } else if (user.getLevel() == Level.GOLD) {
//                changed = false;
//            } else {
//                changed = false;
//            }
//            if (changed) {
//                userDao.update(user);
//            }
//        }
    }

    // template-callback pattern의 도입
    public void upgradeLevelTemplate(UserLevelUpgradeCallback callback) {
        List<User> users = userDao.getAll();
        for (User user : users) {
            if (callback.canUpgradeLevel(user)) {
                callback.upgradeLevel(user);
            }
        }
    }

        // template-callback 패턴으로 대체
    protected void upgradeLevel(User user) {
        // Level에 관한 로직을 User에서 알아서 처리하도록 요청
//        Level currentLevel = user.getLevel();
//        if (currentLevel == Level.BASIC) {
//            user.setLevel(Level.SILVER);
//        } else if (currentLevel == Level.SILVER) {
//            user.setLevel(Level.GOLD);
//        }
        user.upgradeLevel();
        userDao.update(user);
    }

    // template-callback 패턴으로 대체
    private boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC:
                return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
            case SILVER:
                return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unknown Level : " + currentLevel);
        }
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }


}