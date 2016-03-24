package de.titanium.enterprise.Skill;

import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Data.Datas.SkillEntry;
import de.titanium.enterprise.Entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yonas on 11.03.2016.
 */
public enum Skills implements Skill {

    NEU_00_OLYMPUS {

        private List<String> description = new ArrayList<>();

        @Override
        public String getName() {
            return "Olympus";
        }

        @Override
        public List<String> getDescription() {
            if(this.description.isEmpty()) {
                this.description.add("Willkommen zum");
                this.description.add("Olympus der Skills.");
            }
            return this.description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.NEUTRAL;
        }

        @Override
        public int getPrice() {
            return -1;
        }

        @Override
        public int getPosition() {
            return 500;
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
            return  true;
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return true;
        }

    },
    DEF_10_ZEUS {

        private List<String> description = new ArrayList<>();

        @Override
        public String getName() {
            return "Zeus";
        }

        @Override
        public List<String> getDescription() {
            if(this.description.isEmpty()) {
                this.description.add("Zeus ist gewillt dein");
                this.description.add("maximales Leben um 5");
                this.description.add("zu erhoehen.");
            }
            return this.description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.HEALTH_VALUE;
        }

        @Override
        public int getPrice() {
            return 5;
        }

        @Override
        public int getPosition() {
            return 250;
        }

        @Override
        public double getValue(LivingEntity a, LivingEntity b) {
            return 5;
        }

        @Override
        public void apply(LivingEntity entity) {
            entity.setMaxHealth(
                    entity.getMaxHealth() + this.getValue(entity, entity)
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

        private List<String> description = new ArrayList<>();

        @Override
        public String getName() {
            return "Jupiter";
        }

        @Override
        public List<String> getDescription() {
            if(this.description.isEmpty()) {
                this.description.add("Jupiter ist der Meinung,");
                this.description.add("dass du 1 mehr Schaden");
                this.description.add("machen sollst.");
            }
            return this.description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.ATTACK_VALUE;
        }

        @Override
        public int getPrice() {
            return 5;
        }

        @Override
        public int getPosition() {
            return 750;
        }

        @Override
        public double getValue(LivingEntity a, LivingEntity b) {
            return 1;
        }

        @Override
        public void apply(LivingEntity entity) {
            entity.setAttackValue(
                    entity.getAttackValue() + this.getValue(entity, entity)
            );
        }

        @Override
        public boolean hasSkill(LivingEntity entity) {
            return entity.getSkills().contains(this);
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return (Skills.NEU_00_OLYMPUS.hasSkill(entity));
        }
    };

    public static BinarySearchTree<SkillEntry> defaultTree() {

        BinarySearchTree<SkillEntry> skillEntryBinarySearchTree = new BinarySearchTree<>();
        skillEntryBinarySearchTree.insert(new SkillEntry(Skills.NEU_00_OLYMPUS));
        skillEntryBinarySearchTree.insert(new SkillEntry(Skills.DEF_10_ZEUS));
        skillEntryBinarySearchTree.insert(new SkillEntry(Skills.ATT_10_JUPITER));

        return  skillEntryBinarySearchTree;

    }

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
