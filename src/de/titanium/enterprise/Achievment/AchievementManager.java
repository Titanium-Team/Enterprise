package de.titanium.enterprise.Achievment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.List;

/**
 * Created by Yonas on 29.03.2016.
 */
public class AchievementManager {

    private final List<Achievement> unlocked = new ArrayList<>();
    private final Queue<AchievementGraphic> queue = new LinkedTransferQueue<>();

    public AchievementManager() {}

    /**
     * Diese Methode fuegt ein Achievement der Liste hinzu.
     *
     * Es wird allerdings nur hinzugefuegt, wenn dieses Achievement noch nicht freigeschaltet wurde und fügt sie
     * auch der Queue hinzu.
     * @param achievement
     */
    public void add(Achievement achievement) {
        this.add(achievement, true);
    }

    /**
     * Diese Methode fügt das Achievement der Liste hinzu.
     *
     * @param achievement
     * @param display Wenn dieser Wert true ist, dann wird das Achievement auch im Spiel als freigeschaltet dargestellt.
     */
    public void add(Achievement achievement, boolean display) {
        if(!(this.unlocked.contains(achievement))) {
            if(display) {
                this.queue.add(new AchievementGraphic(achievement));
            }
            this.unlocked.add(achievement);
        }
    }

    /**
     * Diese Methode "handelt" das Ein- und wieder Ausblenden der aktuellen Achievements die freigeschaltet wurden.
     *
     * Sollte es keine aktuell in der Queue geben wird auch nichts gezeichnet.
     * @param g
     */
    public void handle(Graphics2D g) {

        if(!(this.queue.isEmpty())) {
            this.queue.element().draw(g);

            if(this.queue.element().isDone()) {
                this.queue.remove();
            }
        }

    }

    /**
     * Diese Methode gibt die AchievementGraphic zurueck, die aktuell gezeichnet wird, falls keine gezeichnet wird,
     * wird null zurueckgegeben.
     * @return
     */
    public AchievementGraphic getCurrent() {
        return this.queue.peek();
    }

    @Override
    public String toString() {
        return String.format("{unlocked: %s, queue: %s}", this.unlocked, this.queue);
    }

    public List<Achievement> getUnlocked() {
        return this.unlocked;
    }
}
