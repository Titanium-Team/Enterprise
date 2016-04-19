package de.titanium.enterprise.Skill;

public enum SkillTypes {

    /**
     * ATTACK_VALUE Skills sind Skills, die einmalig den Attack-Value des Entitys erhoehen.
     */
    ATTACK_VALUE,

    /**
     * HEALTH_VALUE Skills sind Skills, die einmalig den Max-Health des Entitys erhoehen.
     */
    HEALTH_VALUE,

    /**
     * ATTACK_EFFICIENCY Skills sind Skills, die den Angriffswert des Entitys um einen bestimmten relativen Wert (0 - 1)
     * erhoehen, bei jeder Attacke.
     */
    ATTACK_EFFICIENCY,

    /**
     * DEFENSE_EFFICIENCY_ACTIVE Skills sind Skills, die "aktiv" den Angriffswert des Gegners um einen bestimmten relativen
     * Wert (0 - 1) schwaechen.
     */
    DEFENSE_EFFICIENCY_ACTIVE,

    /**
     * DEFENSE_EFFICIENCY_PASSIVE Skills sind Skills, die den Score den man im Defense-GameUtils erreicht um einen bestimmten
     * relativen Wert (0 - 1) erhoehen.
     */
    DEFENSE_EFFICIENCY_PASSIVE,

    /**
     * NEUTRAL Diesen Typen gibt es nur, damit man dem ersten Skill, der keine besondere Funktion hat, auch einen
     * SkillType zuweisen konnte.
     */
    NEUTRAL

}
