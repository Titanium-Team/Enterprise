package de.titanium.enterprise.Skill;

import de.titanium.enterprise.Entity.Entity;
import de.titanium.enterprise.Entity.LivingEntity;

/**
 * Created by Yonas on 11.03.2016.
 */
public interface Skill {

    /**
     * Gibt den Namen des Skills zurück.
     * @return
     */
    String getName();

    /**
     * Gibt die Beschreibung des Skills an.
     * @return
     */
    String getDescription();

    /**
     * Gibt die Art des Skills zurück.
     * @return
     */
    SkillTypes getSkillType();

    /**
     * Gibt den Wert zurück den dieser Skill Einfluss nehmen soll.
     * @param entity
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
