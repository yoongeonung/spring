package user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@NoArgsConstructor
@Getter @Setter
public class User {
    private String id;
    private String name;
    private String password;

    private Level level;
    private int login;

    public User(String id, String name, String password, Level level, int login, int recommend) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
        this.login = login;
        this.recommend = recommend;
    }

    private int recommend;
    private LocalDate lastUpdate;   // 기타 기능 추가

    // User의 내부 정보가 변경되는 경우 User외의 다른 객체가 아닌 User가 담당하는게 더 좋다.

    // User가 로직을 담당함으로 인해, 기타 기능의 추가들도 간단하게 할 수 있다.
    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if (nextLevel == null) {
            throw new IllegalStateException(level + "은 업그레이드가 불가능합니다.");
        }
        lastUpdate = LocalDate.now();
        level = nextLevel;
    }
}
