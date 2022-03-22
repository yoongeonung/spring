package user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user; // 컨테이너에서 관리되는 객체가 아니다.

    @BeforeEach
    void beforeEach() {
        this.user = new User();
    }

    @Test
    void upgradeLevel() {
        Level[] levels = Level.values();
        for (Level level : levels) {
            if (level.nextLevel() == null) {
                continue;
            }
            user.setLevel(level);
            user.upgradeLevel();
            assertThat(user.getLevel()).isEqualTo(level.nextLevel());
        }
    }

    @Test
    void cannotUpgradeLevel() {
        Level[] levels = Level.values();
        for (Level level : levels) {
            if (level != null) {
                continue;
            }
            user.setLevel(level);
            Assertions.assertThrows(IllegalStateException.class, () -> user.upgradeLevel());
            Assertions.assertThrows(IllegalStateException.class, () -> user.upgradeLevel(), level + "은 업그레이드가 불가능합니다.");
        }
    }
}