package de.titanium.enterprise.Entity.Entities;

import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Sprite.Animation.Animations;

import java.util.UUID;

/**
 * Created by Yonas on 09.03.2016.
 */
public class Rogue extends LivingEntity {

    public Rogue(UUID identifier, String name, double health, double maxHealth, double skill, double attackValue) {
        super(identifier, Animations.RANGER_IDLE, name, health, maxHealth, skill, attackValue);
    }

    @Override
    public double calculateDamage(LivingEntity enemy, int comboResult) {

        double value = -1 * Math.pow(comboResult - this.getSkill(), 2) * this.getAttackValue() * 1.2 + this.getAttackValue() * 1.8;

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
        return true;
    }
}
