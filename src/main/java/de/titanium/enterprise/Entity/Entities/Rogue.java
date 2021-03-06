package de.titanium.enterprise.Entity.Entities;

import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Skill.SkillTypes;
import de.titanium.enterprise.Skill.Skills;
import de.titanium.enterprise.Sprite.Animation.Animations;

import java.util.UUID;

public class Rogue extends LivingEntity {

    public Rogue(UUID identifier, String name, double health, double maxHealth, double dexterity, double attackValue, int skillPoints, boolean isUnlocked, double scoreToUnlock) {
        super(identifier, Animations.RANGER_IDLE, name, health, maxHealth, dexterity, attackValue, skillPoints, isUnlocked, scoreToUnlock);
    }

    @Override
    public double calculateDamage(LivingEntity enemy, int comboResult) {

        double value = -1 * Math.pow(comboResult - this.getDexterity(), 2) * this.getAttackValue() * 1.2 + this.getAttackValue();

        //Attack Efficiency
        double attackEfficiency = 1;
        for(Skill skill : Skills.all(SkillTypes.ATTACK_EFFICIENCY, this)) {
            attackEfficiency += skill.getValue(this, enemy);
        }
        for(Skill skill : Skills.all(SkillTypes.DEFENSE_EFFICIENCY_ACTIVE, enemy)) {
            attackEfficiency -= skill.getValue(enemy, this);
        }

        value *= attackEfficiency;

        // Hier wird sichergestellt das es keinen ungueltigen Wert gibt!
        if(Double.isNaN(value)) {
            value = 0;
        }

        value = Math.max(value, 0);

        return value;
    }

    @Override
    public double calculateDefense(LivingEntity enemy, double defenseScore) {

        double value = (defenseScore / 300);

        int efficiency = 1;
        for(Skill skill : Skills.all(SkillTypes.DEFENSE_EFFICIENCY_PASSIVE, this)) {
            efficiency += skill.getValue(this, enemy);
        }

        value *= efficiency;

        if(Double.isNaN(value)) {
            value = 0;
        }

        value = Math.max(value, 0);

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
