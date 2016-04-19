package de.titanium.enterprise.Skill;

public enum SkillTypes {

    /**
     * ATTACK_VALUE Skills sind Skills, die einmalig den Attack-Value des Entitys erh�hen.
     */
    ATTACK_VALUE,

    /**
     * HEALTH_VALUE Skills sind Skills, die einmalig den Max-Health des Entitys erh�hen.
     */
    HEALTH_VALUE,

    /**
     * ATTACK_EFFICIENCY Skills sind Skills, die den Angriffswert des Entitys um einen bestimmten relativen Wert (0 - 1)
     * erh�hen, bei jeder Attacke.
     */
    ATTACK_EFFICIENCY,

    /**
     * DEFENSE_EFFICIENCY_ACTIVE Skills sind Skills, die "aktiv" den Angriffswert des Gegners um einen bestimmten relativen
     * Wert (0 - 1) schw�chen.
     */
    DEFENSE_EFFICIENCY_ACTIVE,

    /**
     * DEFENSE_EFFICIENCY_PASSIVE Skills sind Skills, die den Score den man im Defense-Game erreicht um einen bestimmten
     * relativen Wert (0 - 1) erh�hen.
     */
    DEFENSE_EFFICIENCY_PASSIVE,

    /**
     * NEUTRAL Diesen Typen gibt es nur, damit man dem ersten Skill, der keine besondere Funktion hat, auch einen
     * SkillType zuweisen konnte.
     */
    NEUTRAL

}
