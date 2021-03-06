package de.titanium.enterprise.Achievment;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameUtils.GameComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AchievementGraphic implements GameComponent {

    private float alpha = 0.0F;
    private boolean done = false;

    private BufferedImage achievementImage;

    public AchievementGraphic(Achievement achievement) {

        Enterprise.getGame().addComponent(this);
        this.createAchievementImage(achievement);

    }

    /**
     * This method handles the rendering of the achievements.
     *
     * Add this method to all views to make sure that the Achievement will be displayed in all views.
     * @param g
     */
    public void draw(Graphics2D g) {

        // Den Alpha-Wert setzen
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Math.min(1, this.alpha)));

        // Der Alpha-Wert der hier ueberall beruecksichtigt wird, bei der Breite, Hoehe und den Koordinaten sollte immer
        // zwischen 0 und 1 liegen.
        double optimizedAlpha = Math.min(this.alpha, 1);

        // Ist ein Wert der die X-Koordinate einmal fuer das Sub-Image angibt, sowie auch Position im globalen System
        // mit zu determinieren
        double minusX = (this.achievementImage.getWidth() / 2) - (this.achievementImage.getWidth() / 2 * optimizedAlpha);

        // @Bug: Durch die Rundungsfehler (durch das casten zum int) "teleportiert" sich die Grafik immer leicht nach
        // oben und unten.
        g.drawImage(this.achievementImage.getSubimage(
                        (int) minusX,
                        (int) (this.achievementImage.getHeight() / 2 - (this.achievementImage.getHeight() / 2 * optimizedAlpha)),
                        (int) Math.max((this.achievementImage.getWidth() * optimizedAlpha), 1),
                        (int) Math.max((this.achievementImage.getHeight() * optimizedAlpha), 1)
        ),
                (int) Math.round(50 + minusX),
                (int) Math.round(90 - (45 * optimizedAlpha)),
                null
        );

        // Hier wird der Alpha-Wert wieder auf 1 gesetzt, damit alle anderen "Zeichnungen" wieder normal dargestellt
        // werden.
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

    }

    /**
     * This method checks if the AchievementGraphic is done. - This is the case when the texture appeared and disappeared.
     * @return
     */
    public boolean isDone() {
        return (this.alpha == 0 && this.done);
    }

    @Override
    public void update(int tick) {

        // In dieser Bereich wird der Alpha-Wert immer weiter erhueht und sobald er 2.5 erreicht hat wird er wieder verringert,
        // so wird sichergestellt dass das Icon fuer das Achievement erst eingeblendet und dann wieder ausgeblendet wird,
        // ohne das es "unnatuerlich" oder "ruckartig" aussieht.

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
     * This method creates the achievement image.
     * @param achievement
     */
    private void createAchievementImage(Achievement achievement) {
        Image icon = achievement.getTexture().getImage().getScaledInstance(42, 43, 0);

        BufferedImage text = (BufferedImage) Enterprise.getGame().getTextBuilder().toImage(achievement.getName(), 10);

        this.achievementImage = new BufferedImage((text.getWidth() + 48), 44, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.achievementImage.createGraphics();

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        // Rahmen
        g.setColor(new Color(26, 49, 64));
        g.fillRoundRect(0, 0, this.achievementImage.getWidth(), this.achievementImage.getHeight(), 20, 20);

        // Das Icon zeichnen
        g.drawImage(icon, 5, 0, null);

        // Hier wird der Name des Achievements gezeichnet
        g.drawImage(text, 48, 10, null);

        // Den Speicher wieder befreien
        g.dispose();

    }

    @Override
    public String toString() {
        return String.format("{alpha: %.2f, done: %b, achievementImage: %s}", this.alpha, this.done, this.achievementImage);
    }

}
