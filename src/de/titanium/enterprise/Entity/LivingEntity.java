package de.titanium.enterprise.Entity;

import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Sprite.Animation.AnimationQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Yonas on 09.03.2016.
 */
public abstract class LivingEntity extends Entity {

    private final String name;

    private double health;
    private double maxHealth;

    private final double skill;
    private double attackValue;

    private final List<Skill> skills = new ArrayList<>();
    private final AnimationQueue animationQueue;

    /**
     *
     * @param identifier Die eindeutige ID des Entitys.
     * @param animationQueue Die AnimationQueue des Entitys.
     * @param name Der "Display-Name" des Entitys.
     * @param health Die aktuelle Anzahl an Leben des Entitys.
     * @param maxHealth Die maximale Anzahl an Leben des Entitys.
     * @param skill Der Geschicklichkeitswert des Entitys.
     * @param attackValue Der Basis Wert des Schadens.
     */
    public LivingEntity(UUID identifier, AnimationQueue animationQueue, String name, double health, double maxHealth, double skill, double attackValue) {
        super(identifier);

        this.animationQueue = animationQueue;
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.skill = skill;
        this.attackValue = attackValue;
    }

    /**
     * Gibt den Namen zurück, der dem Benutzer angezeigt werden soll.
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gibt die aktuelle Anzahl an Leben zurück.
     * @return
     */
    public double getHealth() {
        return health;
    }

    /**
     * Gibt die maximale Anzahl an Leben zurück.
     * @return
     */
    public double getMaxHealth() {
        return maxHealth;
    }

    /**
     * Gibt den aktuellen Geschicklichkeitswert zurück.
     * @return
     */
    public double getSkill() {
        return skill;
    }

    /**
     * Gibt den Basis Wert des Schadens zurück.
     * @return
     */
    public double getAttackValue() {
        return attackValue;
    }

    /**
     * Gibt true zurück, wenn das Entity noch mehr als 0 Leben hat.
     * @return
     */
    public boolean isAlive() {
        return (this.getHealth() > 0);
    }

    /**
     * Gibt eine Liste mit allen Skills zurück die dieses Living-Entity bereits freigeschaltet hat.
     * @return
     */
    public List<Skill> getSkills() {
        return this.skills;
    }

    /**
     * Diese Methode fügt dem Entity den übergebenen Skill hinzu.
     * @param skill
     */
    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    /**
     * Diese Methode berechnet abhängig von den Typen, die sich gegenüberstehen, die jeweiligen Schadenswerte.
     * @param enemy Ist das Entity das angegriffen wird oder verteidigt.
     * @param comboResult Das Resultat der Combo für dieses Entity.
     * @return
     */
    public abstract double calculateDamage(LivingEntity enemy, int comboResult);

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setAttackValue(double attackValue) {
        this.attackValue = attackValue;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public AnimationQueue getAnimationQueue() {
        return animationQueue;
    }

}
