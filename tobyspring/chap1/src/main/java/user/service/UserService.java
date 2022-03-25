package user.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import user.dao.UserDao;
import user.domain.Level;
import user.domain.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

public class UserService {

    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECOMMEND_FOR_GOLD = 30;

    private UserDao userDao;
    private MailSender mailSender;
//    private DataSource dataSource;
    private PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
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

        // JDBC 트랜잭션 추상 오브젝트 생성 -> DI로 변경
//        PlatformTransactionManager transactionManager = new JtaTransactionManager();
//        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);

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
        sendUpgradeEmail(user);
    }

    private void sendUpgradeEmail(User user) {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "mail.ksug.org");
//        Session s = Session.getInstance(props, null);
//
//        MimeMessage message = new MimeMessage(s);
//        try {
//            message.setFrom(new InternetAddress("useradmin@ksug.org"));
//            message.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(user.getEmail())));
//            message.setSubject("Upgrade 안내");
//            message.setText("사용자님의 등급이 " + user.getLevel().name() + "로 업그레이드 되셨습니다.");
//
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

        // 스프링의 JavaMailSender로 대체
        // try/catch 불요
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("mail.server.com");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("useradmin@ksug.org");
        mailMessage.setSubject("Upgrade 안내");
        mailMessage.setText("사용자님의 등급이 " + user.getLevel().name() + "로 업그레이드 되셨습니다.");

        mailSender.send(mailMessage);


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