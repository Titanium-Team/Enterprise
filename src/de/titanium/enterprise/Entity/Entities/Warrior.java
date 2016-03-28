package de.titanium.enterprise.Entity.Entities;

import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Skill.SkillTypes;
import de.titanium.enterprise.Skill.Skills;
import de.titanium.enterprise.Sprite.Animation.Animations;

import java.util.UUID;

/**
 * Created by Yonas on 09.03.2016.
 */
public class Warrior extends LivingEntity {

    public Warrior(UUID identifier, String name, double health, double maxHealth, double dexterity, double attackValue, int skillPoints) {
        super(identifier, Animations.RANGER_IDLE, name, health, maxHealth, dexterity, attackValue, skillPoints);
    }

    @Override
    public double calculateDamage(LivingEntity enemy, int comboResult) {

        double value = (comboResult / 10D) * this.getAttackValue();

        //Attack Efficiency
        double attackEfficiency = 1;
        for(Skill skill : Skills.all(SkillTypes.ATTACK_EFFICIENCY, this)) {
            attackEfficiency += skill.getValue(this, enemy);
        }

        //Enemy Defense Efficiency
        for(Skill skill : Skills.all(SkillTypes.DEFENSE_EFFICIENCY, enemy)) {
            attackEfficiency -= skill.getValue(enemy, this);
        }

        value *= attackEfficiency;

        // Hier wird sichergestellt das es keinen ungültigen Wert gibt!
        if(Double.isNaN(value)) {
            value = 0;
        }

        value = Math.max(value, 0);

        return value;
    }

    @Override
    public double calculateDefense(LivingEntity enemy, int defenseScore) {
        return (defenseScore / 100);
    }

    @Override
    public void update(int tick) {}

    @Override
    public void render() {}

    @Override
    public boolean isActive() {
        return false;
    }

}
