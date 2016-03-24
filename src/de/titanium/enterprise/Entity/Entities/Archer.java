package de.titanium.enterprise.Entity.Entities;

import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Sprite.Animation.Animations;

import java.util.UUID;

/**
 * Created by Yonas on 09.03.2016.
 */
public class Archer extends LivingEntity {

    public Archer(UUID identifier, String name, double health, double maxHealth, double dexterity, double attackValue, int skillPoints) {
        super(identifier, Animations.RANGER_IDLE, name, health, maxHealth, dexterity, attackValue, skillPoints);
    }

    @Override
    public double calculateDamage(LivingEntity enemy, int comboResult) {

        double value = Math.log(comboResult - this.getDexterity()) + this.getAttackValue();

        return value;
    }

    @Override
    public void update(int tick) {

    }

    @Override
    public void render() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
