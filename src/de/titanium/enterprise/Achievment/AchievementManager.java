package de.titanium.enterprise.Achievment;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by Yonas on 29.03.2016.
 */
public class AchievementManager {

    private final Queue<AchievementGraphic> queue = new LinkedTransferQueue<>();

    public AchievementManager() {}

    /**
     * Diese Methode fügt ein Achievement der Queue hinzu, damit dies dann angezeigt werden kann.
     * @param achievement
     */
    public void add(Achievement achievement) {
        this.queue.add(new AchievementGraphic(achievement));
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
