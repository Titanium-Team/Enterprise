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
     * @param entity
     */
    List<String> getDescription(LivingEntity entity);

    /**
     * Gibt die Art des Skills zurueck.
     * @return
     */
    SkillTypes getSkillType();

    /**
     * Gibt die Anzahl an Skill-Punkten zurueck, die der Spieler ausgeben muss, um diesen Skill freizuschalten.
     * @return
     * @param entity
     */
    int getPrice(LivingEntity entity);

    /**
     * Der zurueckgegebene Wert determiniert die Position im Skill-Tree;
     * @return
     */
    int getPosition();

    /**
     * Gibt den Wert zurueck den dieser Skill Einfluss nehmen soll.
     * @param entity
     * @param enemy
     * @return
     */
    double getValue(LivingEntity entity, LivingEntity enemy);

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
