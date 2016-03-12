package de.titanium.enterprise.Entity.Entities;

import de.titanium.enterprise.Entity.EntityType.EntityTypes;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.KeyCombo.ComboResult;
import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Skill.SkillTypes;
import de.titanium.enterprise.Skill.Skills;

import java.util.UUID;

/**
 * Created by Yonas on 09.03.2016.
 */
public class Warrior extends LivingEntity {

    public Warrior(UUID identifier, String name, double health, double maxHealth, double skill, double attackValue) {
        super(identifier, EntityTypes.CLOSE, name, health, maxHealth, skill, attackValue);
    }

    @Override
    public double calculateDamage(LivingEntity enemy, ComboResult comboResult) {

        double value = (comboResult.getKeys() / 10) * this.getAttackValue();

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

        return value;
    }

    @Override
    public void update(double deltaTime, int tick) {}

    @Override
    public void render() {}

    @Override
    public boolean isActive() {
        return false;
    }

}
