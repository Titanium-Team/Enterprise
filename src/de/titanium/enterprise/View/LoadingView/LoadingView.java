package de.titanium.enterprise.View.LoadingView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Loading.Loadable;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Yonas on 17.03.2016.
 */
public class LoadingView extends View {

    private String value = ".";
    private int count = 0;

    private boolean firstOne = true;

    private final Timer timer = new Timer();

    public LoadingView() {
        super(new LoadingMenu());

        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                count++;
                if(count == 10) {
                    if (value.length() > 2) {
                        value = ".";
                    } else {
                        value += ".";
                    }
                    count = 0;
                }

                repaint();

                // Der TimeTask wird beendet, sobald dieser nicht mehr benötigt wird.
                if(Enterprise.getGame().getLoadingManager().getCurrent() == null && !(firstOne)) {
                    if(!(this.cancel())) {
                        // @Watch: Das sollte eigentlich nie passieren, falls doch muss man schauen wie
                        // man das löst.
                        throw new RuntimeException("Loading Task didn't stop running.");
                    }
                }

                // @Cleanup: Das sollte eigentlich nicht nötig sein, allerdings ist getCurrent() schon beim ersten Aufruf
                // null und muss deshalb einmal "ignoriert" werden, allerdings ist aus einem mir unbekannt Grund auch
                // der GameState nicht auf LOADING steht. - Ich denke es liegt daran das dies ein neuer Thread ist und der
                // alte Thread einfach schon weiter geht, im Code, und der GameState sich dann bereits im GameLoop befindet.
                firstOne = false;
            }
        }, 0, 50);
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        // Draw Loading Text
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Loading" + this.value;
        FontMetrics fontMetrics = g.getFontMetrics();
        g.drawString(text, (this.getWidth() - fontMetrics.stringWidth("Loading...")) / 2,  ((this.getHeight() - fontMetrics.getHeight()) / 2) - fontMetrics.getAscent());

        // Draw Loadable Text
        Loadable current = Enterprise.getGame().getLoadingManager().getCurrent();
        g.setFont(new Font("Arial", Font.BOLD, 25));
        fontMetrics = g.getFontMetrics();

        if(!(current == null)) {
            g.drawString(current.getName(), (this.getWidth() - fontMetrics.stringWidth(current.getName())) / 2,  ((this.getHeight() - fontMetrics.getHeight()) / 2) - fontMetrics.getAscent());
        }

    }

    @Override
    public void update(int tick) {}

    @Override
    public void render() {}

}
