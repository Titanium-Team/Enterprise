package de.titanium.enterprise.Achievment;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Yonas on 29.03.2016.
 */
public class AchievementGraphic implements GameComponent {

    private float alpha = 0.0F;
    private boolean done = false;

    private Image icon;

    private BufferedImage text;
    private int textWidth;


    public AchievementGraphic(Achievement achievement) {

        Enterprise.getGame().addComponent(this);

        this.icon = achievement.getTexture().getImage().getScaledInstance(28, 28, 0);

        this.text = (BufferedImage) Enterprise.getGame().getTextBuilder().toImage(achievement.getName(), 10);
        this.textWidth = this.text.getWidth();


    }

    /**
     * Diese Methode zeichnet die Achievement-Notification an der richtigen Stelle.
     * @param g
     */
    public void draw(Graphics2D g) {

        // Den Alpha-Wert setzen
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1, this.alpha)));

        // Das Icon zeichnen
        g.drawImage(this.icon, 50, 50, null);

        // Hier wird der Name des Achievements gezeichnet
        g.drawImage(this.text.getSubimage(0, 0, (int) Math.max(1, (this.textWidth * Math.min(this.alpha, 1))), text.getHeight()), 85, 50, null);

        // Hier wird der Alpha-Wert wieder auf 1 gesetzt, damit alle anderen "Zeichnungen" wieder normal dargestellt
        // werden.
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

    }

    /**
     * Diese Methode prüft, ob die Einblendung fertig ist. Das bedeutet die Texture wurde eingeblendet und dann wieder
     * ausgeblendet.
     * @return
     */
    public boolean isDone() {
        return (this.alpha == 0 && this.done);
    }

    @Override
    public void update(int tick) {

        // In dieser Bereich wird der Alpha-Wert immer weiter erhöht und sobald er 2.5 erreicht hat wird er wieder verringert,
        // so wird sichergestellt dass das Icon für das Achievement erst eingeblendet und dann wieder ausgeblendet wird,
        // ohne das es "unnatürlich" oder "ruckartig" aussieht.

        if(!(this.done)) {
            this.alpha += 0.01;
        }

        if(this.alpha >= 2.5 || this.done) {
            this.done = true;
            this.alpha -= 0.01;

            this.alpha = Math.max(this.alpha, 0);
        }

    }

    @Override
    public void render() {}

    @Override
    public boolean isActive() {
        return Enterprise.getGame().getAchievementManager().getCurrent() == this;
    }
}
