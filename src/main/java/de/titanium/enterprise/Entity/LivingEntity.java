package de.titanium.enterprise.Entity;

import de.titanium.enterprise.Entity.Statistic.GameStatistic;
import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Sprite.Animation.Animation;
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

    private final double dexterity;
    private double attackValue;

    private final List<Skill> skills = new ArrayList<>();
    private final AnimationQueue animationQueue;

    private int skillPoints;

    private boolean isUnlocked = false;

    private double scoreToUnlock;

    private final GameStatistic gameStatistic = new GameStatistic();

    /**
     * @param identifier       Die eindeutige ID des Entitys.
     * @param defaultAnimation Die standart Animation die abgespielt werden soll.
     * @param name             Der "Display-Name" des Entitys.
     * @param health           Die aktuelle Anzahl an Leben des Entitys.
     * @param maxHealth        Die maximale Anzahl an Leben des Entitys.
     * @param dexterity        Der Geschicklichkeitswert des Entitys.
     * @param attackValue      Der Basis Wert des Schadens.
     * @param skillPoints      Die Punkte die der Held zum Skillen hat.
     * @param isUnlocked       Ob der Held dem Spieler bereits zur verfuegung steht.
     * @param scoreToUnlock    Anzahl der Punkte die erreicht werden müssen, damit dieser Held freigeschaltet wird.
     */
    public LivingEntity(UUID identifier, Animation defaultAnimation, String name, double health, double maxHealth, double dexterity, double attackValue, int skillPoints, boolean isUnlocked, double scoreToUnlock) {
        super(identifier);

        this.animationQueue = new AnimationQueue(defaultAnimation);
        this.name = name;
        this.health = health;
        this.maxHealth = maxHealth;
        this.dexterity = dexterity;
        this.attackValue = attackValue;
        this.skillPoints = skillPoints;
        this.isUnlocked = isUnlocked;
        this.scoreToUnlock = scoreToUnlock;

    }

    /**
     * Gibt den Namen zurueck, der dem Benutzer angezeigt werden soll.
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gibt die AnimationQueue des Entitys zurueck.
     *
     * @return
     */
    public AnimationQueue getAnimationQueue() {
        return this.animationQueue;
    }

    /**
     * Gibt die aktullen GameStatistics zurueck.
     *
     * @return
     */
    public GameStatistic getGameStatistic() {
        return this.gameStatistic;
    }

    /**
     * Gibt die aktuelle Anzahl an Leben zurueck.
     *
     * @return
     */
    public double getHealth() {
        return this.health;
    }

    /**
     * Gibt die maximale Anzahl an Leben zurueck.
     *
     * @return
     */
    public double getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Gibt den aktuellen Geschicklichkeitswert zurueck.
     *
     * @return
     */
    public double getDexterity() {
        return this.dexterity;
    }

    /**
     * Gibt den Basis Wert des Schadens zurueck.
     *
     * @return
     */
    public double getAttackValue() {
        return this.attackValue;
    }

    /**
     * Gibt true zurueck, wenn das Entity noch mehr als 0 Leben hat.
     *
     * @return
     */
    public boolean isAlive() {
        return (this.getHealth() > 0);
    }

    /**
     * Gibt eine Liste mit allen Skills zurueck die dieses Living-Entity bereits freigeschaltet hat.
     *
     * @return
     */
    public List<Skill> getSkills() {
        return this.skills;
    }

    /**
     * Die Punkte die der Held hat, um seinen Helden zu skillen.
     *
     * @return
     */
    public int getSkillPoints() {
        return this.skillPoints;
    }

    /**
     * Diese Methode fuegt dem Entity den uebergebenen Skill hinzu.
     *
     * @param skill
     */
    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    /**
     * Diese Methode berechnet abhaengig von den Typen, die sich gegenueberstehen, die jeweiligen Schadenswerte.
     *
     * @param enemy       Ist das Entity das angegriffen wird oder verteidigt.
     * @param comboResult Das Resultat der Combo fuer dieses Entity.
     * @return
     */
    public abstract double calculateDamage(LivingEntity enemy, int comboResult);

    public abstract double calculateDefense(LivingEntity enemy, double defenseScore);

    /**
     * Setzt das maximale Leben des Entitys.
     *
     * @param maxHealth
     */
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Setzt den Angriffswert.
     *
     * @param attackValue
     */
    public void setAttackValue(double attackValue) {
        this.attackValue = attackValue;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Setzt die Sillpunkte.
     *
     * @param skillPoints
     */
    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    /**
     * Gibt true zurück, wenn der Spieler diesen Helden bereits freigeschaltet hat.
     *
     * @return
     */
    public boolean isUnlocked() {
        return this.isUnlocked;
    }

    /**
     * Gibt den Wert an Punkten zurück, die erreicht werden müssen, damit dieser Held freigeschaltet wird.
     *
     * @return
     */
    public double getScoreToUnlock() {
        return this.scoreToUnlock;
    }

    /**
     * Setzt den Wert, ob der Held freigeschaltet ist oder nicht.
     * @param unlocked
     */
    public void setUnlocked(boolean unlocked) {
        this.isUnlocked = unlocked;
    }

    @Override
    public String toString() {
        return String.format(
                "{identifier: %s, name: %s, health: %.2f, maxHealth: %.2f, dexterity: %.2f, attackValue: %.2f, skills: %s, animationQueue: %s, skillPoints: %d, gameStatistic: %s}",
                this.getIdentifier(),
                this.name,
                this.health,
                this.maxHealth,
                this.dexterity,
                this.attackValue,
                this.skills,
                this.animationQueue,
                this.skillPoints,
                this.gameStatistic
        );
    }

}

