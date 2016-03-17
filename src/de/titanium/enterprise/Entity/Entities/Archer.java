package de.titanium.enterprise.Entity.Entities;

import de.titanium.enterprise.Entity.EntityType.EntityTypes;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.KeyCombo.ComboResult;

import java.util.UUID;

/**
 * Created by Yonas on 09.03.2016.
 */
public class Archer extends LivingEntity {

    public Archer(UUID identifier, String name, double health, double maxHealth, double skill, double attackValue) {
        super(identifier, EntityTypes.RANGED, name, health, maxHealth, skill, attackValue);
    }

    @Override
    public double calculateDamage(LivingEntity enemy, ComboResult comboResult) {

        double value = Math.log(comboResult.getKeys() - this.getSkill()) + this.getAttackValue();

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
