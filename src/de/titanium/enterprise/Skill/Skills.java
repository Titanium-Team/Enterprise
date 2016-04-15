package de.titanium.enterprise.Skill;

import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Data.Datas.SkillEntry;
import de.titanium.enterprise.Entity.Entities.Archer;
import de.titanium.enterprise.Entity.Entities.Rogue;
import de.titanium.enterprise.Entity.Entities.Warrior;
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
        public List<String> getDescription(LivingEntity entity) {

            List<String> description = new ArrayList<>();

            description.add("Willkommen zum");
            description.add("Olympus der Skills.");

            return description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.NEUTRAL;
        }

        @Override
        public int getPrice(LivingEntity entity) {
            return -1;
        }

        @Override
        public int getPosition() {
            return 500;
        }

        @Override
        public double getValue(LivingEntity entity, LivingEntity enemy) {
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

        @Override
        public String getName() {
            return "Zeus";
        }

        @Override
        public List<String> getDescription(LivingEntity entity) {

            List<String> description = new ArrayList<>();

            description.add("Zeus ist gewillt deine");
            description.add("Vitalitaet zu staerken.");
            description.add("");

            if(entity instanceof Warrior) {
                description.add("Maximales Leben wird");
                description.add("um 8 erhoeht.");
            }
            if(entity instanceof Archer) {
                description.add("Maximales Leben wird");
                description.add("um 5 erhoeht.");
            }
            if(entity instanceof Rogue) {
                description.add("Maximales Leben wird");
                description.add("um 3 erhoeht.");
            }

            return description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.HEALTH_VALUE;
        }

        @Override
        public int getPrice(LivingEntity entity) {
            return 4;
        }

        @Override
        public int getPosition() {
            return 250;
        }

        @Override
        public double getValue(LivingEntity entity, LivingEntity enemy) {
            if(entity instanceof Warrior){
                return 8;
            } else if (entity instanceof Archer) {
                return 5;
            } else if (entity instanceof Rogue) {
                return 3;
            }
            throw new IllegalArgumentException(entity.getName() + " wurde noch nicht implementiert");
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

        @Override
        public String getName() {
            return "Jupiter";
        }

        @Override
        public List<String> getDescription(LivingEntity entity) {

            List<String> description = new ArrayList<>();

            description.add("Jupiter gewaehrt dir");
            description.add("die Staerke des");
            description.add("Mondes.");
            description.add("");

            if(entity instanceof Warrior) {
                description.add("Schaden wird");
                description.add("um 1 erhoeht.");
            }
            if(entity instanceof Archer) {
                description.add("Schaden wird");
                description.add("um 2 erhoeht.");
            }
            if(entity instanceof Rogue) {
                description.add("Schaden wird");
                description.add("um 4 erhoeht.");
            }

            return description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.ATTACK_VALUE;
        }

        @Override
        public int getPrice(LivingEntity entity) {
            return 6;
        }

        @Override
        public int getPosition() {
            return 750;
        }

        @Override
        public double getValue(LivingEntity entity, LivingEntity enemy) {
            if(entity instanceof Warrior){
                return 1;
            } else if (entity instanceof Archer) {
                return 2;
            } else if (entity instanceof Rogue) {
                return 4;
            }
            throw new IllegalArgumentException(entity.getName() + " wurde noch nicht implementiert");
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
    },
    DEF_20_ATHENE {

        @Override
        public String getName() {
            return "Athene";
        }

        @Override
        public List<String> getDescription(LivingEntity entity) {

            List<String> description = new ArrayList<>();

            description.add("Athene staerkt dein");
            description.add("Schild.");
            description.add("");

            description.add("Effektivitaet der");
            description.add("Abwehr wird um");
            description.add("30 Prozent");
            description.add("verbessert.");

            return description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.DEFENSE_EFFICIENCY_PASSIVE;
        }

        @Override
        public int getPrice(LivingEntity entity) {
            return 10;
        }

        @Override
        public int getPosition() {
            return 125;
        }

        @Override
        public double getValue(LivingEntity entity, LivingEntity enemy) {
            return 0.3;
        }

        @Override
        public void apply(LivingEntity entity) {

        }

        @Override
        public boolean hasSkill(LivingEntity entity) {
            return entity.getSkills().contains(this);
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return Skills.DEF_10_ZEUS.hasSkill(entity);
        }
    },
    DEF_21_MERCURIUS {

        @Override
        public String getName() {
            return "Mercurius";
        }

        @Override
        public List<String> getDescription(LivingEntity entity) {

            List<String> description = new ArrayList<>();

            description.add("Auch sein Geschaeft");
            description.add("ist die Gaunerei.");
            description.add("So leistet er dir");
            description.add("Schutz gegen");
            description.add("seinesgleichen.");
            description.add("");

            description.add("20 Prozent weniger");
            description.add("Schaden von Rogues.");

            return description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.DEFENSE_EFFICIENCY_ACTIVE;
        }

        @Override
        public int getPrice(LivingEntity entity) {
            return 6;
        }

        @Override
        public int getPosition() {
            return 375;
        }

        @Override
        public double getValue(LivingEntity entity, LivingEntity enemy) {
            if(enemy instanceof Rogue){
                return 0.2;
            }
            return 0;
        }

        @Override
        public void apply(LivingEntity entity) {

        }

        @Override
        public boolean hasSkill(LivingEntity entity) {
            return entity.getSkills().contains(this);
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return Skills.DEF_10_ZEUS.hasSkill(entity);
        }
    },
    ATT_20_VULCANUS {


        @Override
        public String getName() {
            return "Vulcanus";
        }

        @Override
        public List<String> getDescription(LivingEntity entity) {

            List<String> description = new ArrayList<>();

            description.add("Vulcanus lehrt dir");
            description.add("die Kunst des");
            description.add("Schmiedens.");
            description.add("");

            description.add("25 Prozent mehr");
            description.add("Schaden gegen Warrior.");

            return description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.ATTACK_EFFICIENCY;
        }

        @Override
        public int getPrice(LivingEntity entity) {
            return 8;
        }

        @Override
        public int getPosition() {
            return 625;
        }

        @Override
        public double getValue(LivingEntity entity, LivingEntity enemy) {
            if(enemy instanceof Warrior){
                return 0.25;
            }
            return 0;
        }

        @Override
        public void apply(LivingEntity entity) {

        }

        @Override
        public boolean hasSkill(LivingEntity entity) {
            return entity.getSkills().contains(this);
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return Skills.ATT_10_JUPITER.hasSkill(entity);
        }
    },
    ATT_21_ARTEMIS {
        @Override
        public String getName() {
            return "Artemis";
        }

        @Override
        public List<String> getDescription(LivingEntity entity) {

            List<String> description = new ArrayList<>();

            description.add("Artemis trainiert");
            description.add("dich in Genauigkeit.");
            description.add("");

            if(entity instanceof Warrior) {
                description.add("Schaden wird");
                description.add("um 5 Prozent");
                description.add("erhoeht.");
            }
            if(entity instanceof Archer) {
                description.add("Schaden wird");
                description.add("um 25 Prozent");
                description.add("erhoeht.");
            }
            if(entity instanceof Rogue) {
                description.add("Schaden wird");
                description.add("um 5 Prozent");
                description.add("erhoeht.");
            }

            description.add("");
            description.add("Besonders effektiv");
            description.add("bei Archern.");

            return description;
        }

        @Override
        public SkillTypes getSkillType() {
            return SkillTypes.ATTACK_EFFICIENCY;
        }

        @Override
        public int getPrice(LivingEntity entity) {
            if(entity instanceof Warrior){
                return 1;
            } else if (entity instanceof Archer) {
                return 7;
            } else if (entity instanceof Rogue) {
                return 3;
            }
            throw new IllegalArgumentException(entity.getName() + " wurde noch nicht implementiert");
        }

        @Override
        public int getPosition() {
            return 875;
        }

        @Override
        public double getValue(LivingEntity entity, LivingEntity enemy) {

            if(entity instanceof Warrior){
                return 0.05;
            } else if (entity instanceof Archer) {
                return 0.25;
            } else if (entity instanceof Rogue) {
                return 0.05;
            }
            throw new IllegalArgumentException(entity.getName() + " wurde noch nicht implementiert");
        }

        @Override
        public void apply(LivingEntity entity) {

        }

        @Override
        public boolean hasSkill(LivingEntity entity) {
            return entity.getSkills().contains(this);
        }

        @Override
        public boolean isUnlockable(LivingEntity entity) {
            return Skills.ATT_10_JUPITER.hasSkill(entity);
        }
    };

    public static BinarySearchTree<SkillEntry> defaultTree() {

        BinarySearchTree<SkillEntry> skillEntryBinarySearchTree = new BinarySearchTree<>();
        for(Skill skill : Skills.values()){
            skillEntryBinarySearchTree.insert(new SkillEntry(skill));
        }

        return  skillEntryBinarySearchTree;

    }

    public static Skill byName(String value) {

        for(Skill skill : Skills.values()) {
            if(skill.getName().equals(value)) {
                return skill;
            }
        }

        return null;

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
