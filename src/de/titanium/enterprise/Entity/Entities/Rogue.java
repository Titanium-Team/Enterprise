package de.titanium.enterprise.Entity.Entities;

import de.titanium.enterprise.Entity.EntityType.EntityTypes;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.KeyCombo.ComboResult;
import de.titanium.enterprise.Turn;

import java.util.UUID;

/**
 * Created by Yonas on 09.03.2016.
 */
public class Rogue extends LivingEntity {

    public Rogue(UUID identifier, String name, double health, double maxHealth, double skill, double attackValue) {
        super(identifier, EntityTypes.CLOSE, name, health, maxHealth, skill, attackValue);
    }

    @Override
    public double calculateDamage(LivingEntity enemy, ComboResult comboResult) {

        double value = -1 * Math.pow(comboResult.getKeys() - this.getSkill(), 2) * this.getAttackValue() * 1.2 + this.getAttackValue() * 1.8;

        return value;
    }
    @Override
    public void update(double deltaTime, int tick) {

    }

    @Override
    public void render() {

    }

    @Override
    public boolean isActive() {
        return true;
    }
}
