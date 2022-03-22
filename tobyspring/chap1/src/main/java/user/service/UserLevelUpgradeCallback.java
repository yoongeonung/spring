package user.service;

import user.domain.User;

public interface UserLevelUpgradeCallback {
    boolean canUpgradeLevel(User user);

    void upgradeLevel(User user);
}
