package user.service;

import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import java.util.List;

public class UserService {

    private UserDao userDao;
    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void upgradeLevels() {
        // template-callback pattern의 도입
        UserLevelUpgradeCallback callback = new UserLevelUpgradeCallback() {
            @Override
            public boolean canUpgradeLevel(User user) {
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
            @Override
            public void upgradeLevel(User user) {
                user.upgradeLevel();
                userDao.update(user);
            }
        };
        upgradeLevelTemplate(callback);

//        List<User> users = userDao.getAll();
        // 2. 리팩토링을 위한 추상적인 로직코드
        //      - 작업의 기본 흐름을 작성, 이해하기쉽게
//        for (User user : users) {
//            if (canUpgradeLevel(user)) {
//                upgradeLevel(user);
//            }
//        }
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
//    private void upgradeLevel(User user) {
//        // Level에 관한 로직을 User에서 알아서 처리하도록 요청
////        Level currentLevel = user.getLevel();
////        if (currentLevel == Level.BASIC) {
////            user.setLevel(Level.SILVER);
////        } else if (currentLevel == Level.SILVER) {
////            user.setLevel(Level.GOLD);
////        }
//        user.upgradeLevel();
//        userDao.update(user);
//    }

    // template-callback 패턴으로 대체
//    private boolean canUpgradeLevel(User user) {
//        Level currentLevel = user.getLevel();
//        switch (currentLevel) {
//            case BASIC:
//                return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
//            case SILVER:
//                return (user.getRecommend() >= MIN_RECOMMEND_FOR_GOLD);
//            case GOLD:
//                return false;
//            default:
//                throw new IllegalArgumentException("Unknown Level : " + currentLevel);
//        }
//    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }


}