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

    private BufferedImage achievementImage;

    public AchievementGraphic(Achievement achievement) {

        Enterprise.getGame().addComponent(this);
        this.createAchievementImage(achievement);

    }

    /**
     * Diese Methode zeichnet die Achievement-Notification an der richtigen Stelle.
     * @param g
     */
    public void draw(Graphics2D g) {

        // Den Alpha-Wert setzen
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1, this.alpha)));

        g.drawImage(this.achievementImage.getSubimage(
                (int) (this.achievementImage.getWidth() / 2 - Math.min((this.achievementImage.getWidth() / 2 * this.alpha), (this.achievementImage.getWidth() / 2))),
                 0,
                (int) Math.max((this.achievementImage.getWidth(null) * Math.min(this.alpha, 1)), 1),
                this.achievementImage.getHeight()
        ), 50, 50, null);

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

    /**
     * Diese Methode erstellt das Image das dem Spieler angezeigt wird. Dabei wurde dies in diese Methode ausgelagert,
     * damit der Constructor "sauberer" bleibt.
     * @param achievement
     */
    private void createAchievementImage(Achievement achievement) {
        Image icon = achievement.getTexture().getImage().getScaledInstance(28, 28, 0);

        BufferedImage text = (BufferedImage) Enterprise.getGame().getTextBuilder().toImage(achievement.getName(), 10);

        this.achievementImage = new BufferedImage((text.getWidth() + 48), 44, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.achievementImage.createGraphics();

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        // Rahmen
        g.setColor(new Color(26, 49, 64));
        g.fillRoundRect(0, 0, this.achievementImage.getWidth(), this.achievementImage.getHeight(), 20, 20);

        // Das Icon zeichnen
        g.drawImage(icon, 10, 8, null);

        // Hier wird der Name des Achievements gezeichnet
        g.drawImage(text, 48, 10, null);

        // Den Speicher wieder befreien
        g.dispose();

    }

}
