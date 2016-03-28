package de.titanium.enterprise.Entity.Entities;

import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Sprite.Animation.Animations;

import java.util.UUID;

/**
 * Created by Yonas on 09.03.2016.
 */
public class Rogue extends LivingEntity {

    public Rogue(UUID identifier, String name, double health, double maxHealth, double dexterity, double attackValue, int skillPoints) {
        super(identifier, Animations.RANGER_IDLE, name, health, maxHealth, dexterity, attackValue, skillPoints);
    }

    @Override
    public double calculateDamage(LivingEntity enemy, int comboResult) {

        double value = -1 * Math.pow(comboResult - this.getDexterity(), 2) * this.getAttackValue() * 1.2 + this.getAttackValue() * 1.8;

        // @TODO: Die freigeschalteten Damage-Skills müssen noch in die Berechnung einfliesen.
        // @Improve: Eventuell sollte hier bereits auf ungültige Ergebnisse (NaN oder x < 0) geprüft werden,
        // denn das sollte nicht immer im Code passieren. Stichwort: Code-Duplication.

        return value;
    }

    @Override
    public double calculateDefense(LivingEntity enemy, int defenseScore) {
        return (defenseScore / 100);
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
