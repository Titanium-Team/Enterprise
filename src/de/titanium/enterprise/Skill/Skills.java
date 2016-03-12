package de.titanium.enterprise.Skill;

import de.titanium.enterprise.Entity.Entity;
import de.titanium.enterprise.Entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yonas on 11.03.2016.
 */
public enum Skills implements Skill {

    NEU_00_OLYMPUS {
        @Override
        public String getName() {
            return "Olympus";
        }

        @Override
        public String getDescription() {
            return "<comming soon>";
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.NEUTRAL;
        }

        @Override
        public double getValue(LivingEntity a, LivingEntity b) {
            return 0;
        }

        @Override
        public void apply(LivingEntity entity) {
            entity.addSkill(this);
        }

        @Override
        public boolean hasSkill(LivingEntity entity) {
            return true;
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return true;
        }

    },
    DEF_10_ZEUS {
        @Override
        public String getName() {
            return "Zeus";
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.HEALTH_VALUE;
        }

        @Override
        public double getValue(LivingEntity a, LivingEntity b) {
            return 5;
        }

        @Override
        public void apply(LivingEntity entity) {
            entity.addSkill(this);
            entity.setMaxHealth(
                    entity.getMaxHealth() + entity.getMaxHealth() * this.getValue(entity, entity)
            );
            entity.setHealth(entity.getMaxHealth());
        }

        @Override
        public boolean hasSkill(LivingEntity entity) {
            return entity.getSkills().contains(this);
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return (Skills.NEU_00_OLYMPUS.hasSkill(entity));
        }

    },
    ATT_10_JUPITER {
        @Override
        public String getName() {
            return "Jupiter";
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.ATTACK_VALUE;
        }

        @Override
        public double getValue(LivingEntity a, LivingEntity b) {
            return 1;
        }

        @Override
        public void apply(LivingEntity entity) {
            entity.addSkill(this);
            entity.setAttackValue(
                    entity.getAttackValue() + this.getValue(entity, entity)
            );
        }

        @Override
        public boolean hasSkill(LivingEntity entity) {
            return entity.getSkills().contains(entity);
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return (Skills.NEU_00_OLYMPUS.hasSkill(entity));
        }
    };


    public static List<Skill> all(SkillTypes skillType, LivingEntity entity) {

        List<Skill> skills = new ArrayList<>();

        for(Skill skill : Skills.values()) {
            if(skill.getSkillType() == skillType && skill.hasSkill(entity)) {
                skills.add(skill);
            }
        }

        return skills;
    }

}
