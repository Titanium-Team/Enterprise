package de.titanium.enterprise.Entity;

import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Data.BinaryTreeMath;
import de.titanium.enterprise.Data.Datas.SkillEntry;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.Entities.Archer;
import de.titanium.enterprise.Entity.Entities.Rogue;
import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Skill.Skills;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by Yonas on 30.03.2016.
 */
public class EntityGenerator {

    private final SecureRandom random = new SecureRandom();

    public EntityGenerator() {}

    public LivingEntity generate(int level) {

        LivingEntity entity = null;

        // An dieser Stelle wird bestimmt welchen Typ von Entity das Entity wird.
        int entityType = this.random.nextInt(2);

        // Der aktuelle SkillTree
        BinarySearchTree<SkillEntry> skills = Skills.defaultTree();

        // Wenn der Wert 0 ist, dann wird es ein Archer
        if(entityType == 0) { // Archer

            double health = (this.random.nextInt(40) + 5 * level);

            entity = new Archer(
                    UUID.randomUUID(),
                    "Archer",
                    health,
                    health,
                    this.random.nextInt(20 - level),
                    this.random.nextInt(15 * level),
                    this.random.nextInt(5 * level)
            );

            // Nun werden zufällig die Skillpunkte verteilt
            this.skill(entity, skills, null, skills, new int[] { 0, 2 }, new int[] { 3, 10 } );

        } else if(entityType == 1) { // Rogue

            double health = (this.random.nextInt(10) + level * 5);

            entity = new Rogue(
                    UUID.randomUUID(),
                    "Rogue",
                    health,
                    health,
                    this.random.nextInt(level * 3) + 10,
                    this.random.nextInt(level) + 5,
                    this.random.nextInt(level * 3)
            );

            this.skill(entity, skills, null, skills, new int[] { 0, 7 }, new int[] { 8, 10 } );

        } else if(entityType == 2) {

            double health = (this.random.nextInt(70) + level * 10);

            entity = new Rogue(
                    UUID.randomUUID(),
                    "Warrior",
                    health,
                    health,
                    this.random.nextInt(0),
                    this.random.nextInt(level) + 5,
                    this.random.nextInt(level * 3)
            );

            this.skill(entity, skills, null, skills, new int[] { 0, 7 }, new int[] { 8, 10 } );

        }

        Enterprise.getGame().getLogger().info(entity.toString());

        return entity;

    }

    /**
     * Diese Methode skilled ein LivingEntity automatisch.
     * @param entity Das Entity das geskilled werden soll.
     * @param current Die aktuelle Node im Baum.
     * @param parent Der parent der aktuellen Node, falls keiner vorhanden, dann null:
     * @param defaultTree Der komplette Baum der aktuell durchgegangen wird.
     * @param def Ist ein int[] array. Der Array hat zwei Einträge, der erste mit dem Index 0 ist die untere Grenze und
     *            der Eintrag mit dem Index 1 die obere Grenze. In der Methode wird eine Zahl zwischen 1 und 10 generiert,
     *            falls diese Zahl in dem angegebenen Bereich liegt, dann wird diese Pfad als nächstes genommen. In diesem
     *            Fall beeinflusst das die Wahrscheinlichkeit dafür das eher Defense geskilled wird.
     * @param att Ist ein int[] array. Der Array hat zwei Einträge, der erste mit dem Index 0 ist die untere Grenze und
     *            der Eintrag mit dem Index 1 die obere Grenze. In der Methode wird eine Zahl zwischen 1 und 10 generiert,
     *            falls diese Zahl in dem angegebenen Bereich liegt, dann wird diese Pfad als nächstes genommen. In diesem
     *            Fall beeinflusst das die Wahrscheinlichkeit dafür das eher Attack geskilled wird.
     */
    private void skill(LivingEntity entity, BinarySearchTree<SkillEntry> current, BinarySearchTree<SkillEntry> parent, BinarySearchTree<SkillEntry> defaultTree, int[] def , int[] att) {

        Skill skill = current.getContent().getSkill();

        // Wenn all diese Bedingungen erfüllt sind, dann könnte er diesen Skill freischalten.
        if(!(skill.hasSkill(entity)) && skill.isUnlockable(entity) && skill.getPrice() <= entity.getSkillPoints()) {
            skill.apply(entity);
            entity.setSkillPoints(
                    entity.getSkillPoints() - skill.getPrice()
            );
            entity.getSkills().add(skill);
        }

        int decision = this.random.nextInt(10);

        if(!(current.getLeftTree().isEmpty()) && (decision >= def[0] && decision <= def[1])) {

            // Falls links noch etwas im Baum ist und der Zufall es zulässt, dann wird noch geprüft ob man den nächsten
            // überhaupt freischalten kann und falls alles zutrifft, dann geht man in den linken Teilbaum.

            skill = current.getLeftTree().getContent().getSkill();
            if(!(skill.hasSkill(entity)) && skill.isUnlockable(entity) && skill.getPrice() <= entity.getSkillPoints()) {
                this.skill(entity, current.getLeftTree(), current, defaultTree, def, att);
            }

        } else if(!(current.getRightTree().isEmpty()) && (decision >= att[0] && decision <= att[1])) {

            // Falls rechts noch etwas im Baum ist und der Zufall es zulässt, dann wird noch geprüft ob man den nächsten
            // überhaupt freischalten kann und falls alles zutrifft, dann geht man in den rechten Teilbaum.

            skill = current.getRightTree().getContent().getSkill();
            if(!(skill.hasSkill(entity)) && skill.isUnlockable(entity) && skill.getPrice() <= entity.getSkillPoints()) {
                this.skill(entity, current.getRightTree(), current, defaultTree, def, att);
            }
        } else if(!(parent == null) && this.random.nextInt(10) == 1) {

            // Wenn weder nach rechts nocb nach links gegangen wurde und wir nicht im Root des Baumes sind, dann geht man
            // nochmal vom parent der aktuellen Node nach links, wenn man aktuell in der rechten Node des parents ist und
            // nach rechts, wenn man aktuell in der linken Node des parents ist.

            if(current == parent.getLeftTree()) {
                this.skill(entity, parent.getRightTree(), BinaryTreeMath.findParent(parent.getContent(), defaultTree), defaultTree, def, att);
            } else if(current == parent.getRightTree()) {
                this.skill(entity, parent.getLeftTree(), BinaryTreeMath.findParent(parent.getContent(), defaultTree), defaultTree, def, att);
            }

        }

    }

}
