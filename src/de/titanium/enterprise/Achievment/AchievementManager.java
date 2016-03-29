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
     * Diese Methode fügt ein Achievement der Queue hinzu, damit dies dann angezeigt werden kann.
     *
     * Es wird allerdings nur hinzugefügt, wenn dieses Achievement noch nicht freigeschaltet wurde
     * @param achievement
     */
    public void add(Achievement achievement) {
        if(!(this.unlocked.contains(achievement))) {
            this.queue.add(new AchievementGraphic(achievement));
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
     * Diese Methode gibt die AchievementGraphic zurück, die aktuell gezeichnet wird, falls keine gezeichnet wird,
     * wird null zurückgegeben.
     * @return
     */
    public AchievementGraphic getCurrent() {
        return this.queue.peek();
    }
}
