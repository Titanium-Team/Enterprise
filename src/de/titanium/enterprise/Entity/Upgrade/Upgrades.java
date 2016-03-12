package de.titanium.enterprise.Entity.Upgrade;

import de.titanium.enterprise.Entity.LivingEntity;

/**
 * Created by Yonas on 09.03.2016.
 */
public enum Upgrades implements Upgrade<LivingEntity> {

    HEALTH_UPGRADE_ONE {
        @Override
        public boolean upgradeUnlocked(LivingEntity entity) {
            return false;
        }

        @Override
        public void performUpgrade(LivingEntity entity) {
            entity.setMaxHealth(entity.getHealth() * 0.05);
        }
    },
    DAMAGE_UPGRADE_ONE {
        @Override
        public boolean upgradeUnlocked(LivingEntity entity) {
            return false;
        }

        @Override
        public void performUpgrade(LivingEntity entity) {
            entity.setAttackValue(entity.getAttackValue() * 0.05);
        }
    }

}
