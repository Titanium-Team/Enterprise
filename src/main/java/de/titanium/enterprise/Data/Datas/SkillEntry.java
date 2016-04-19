package de.titanium.enterprise.Data.Datas;

import de.titanium.enterprise.Skill.Skill;

public class SkillEntry implements Comparable<SkillEntry> {

    private final Skill skill;

    public SkillEntry(Skill skill) {
        this.skill = skill;
    }

    public Skill getSkill() {
        return this.skill;
    }

    @Override
    public int compareTo(SkillEntry o) {

        if(this.getSkill().getPosition() > o.getSkill().getPosition()) {
            return 1;
        }

        if(this.getSkill().getPosition() < o.getSkill().getPosition()) {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s", this.skill.getName());
    }

}
