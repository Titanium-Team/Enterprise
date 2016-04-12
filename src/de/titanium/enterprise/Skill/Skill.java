package de.titanium.enterprise.Skill;

import de.titanium.enterprise.Entity.LivingEntity;

import java.util.List;

/**
 * Created by Yonas on 11.03.2016.
 */
public interface Skill {

    /**
     * Gibt den Namen des Skills zurueck.
     * @return
     */
    String getName();

    /**
     * Gibt die Beschreibung des Skills an.
     * @return
     */
    List<String> getDescription();

    /**
     * Gibt die Art des Skills zurueck.
     * @return
     */
    SkillTypes getSkillType();

    /**
     * Gibt die Anzahl an Skill-Punkten zurueck, die der Spieler ausgeben muss, um diesen Skill freizuschalten.
     * @return
     */
    int getPrice();

    /**
     * Der zurueckgegebene Wert determiniert die Position im Skill-Tree;
     * @return
     */
    int getPosition();

    /**
     * Gibt den Wert zurueck den dieser Skill Einfluss nehmen soll.
     * @param a
     * @param b
     * @return
     */
    double getValue(LivingEntity a, LivingEntity b);

    /**
     * Gibt dem LivingEntity diesen Skill.
     * @param entity
     */
    void apply(LivingEntity entity);

    /**
     *
     * @param entity
     * @return
     */
    boolean hasSkill(LivingEntity entity);

    /**
     *
     * @param entity
     * @return
     */
    boolean isUnlockable(LivingEntity entity);


}
