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
    private final Timer timer = new Timer();

    public LoadingView() {
        super(new LoadingMenu());
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(value.length() > 2) {
                    value = ".";
                } else {
                    value += ".";
                }

                repaint();
            }
        }, 0, 400);
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //Draw Loading Text
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "Loading" + this.value;
        FontMetrics fontMetrics = g.getFontMetrics();
        g.drawString(text, (this.getWidth() - fontMetrics.stringWidth("Loading...")) / 2,  ((this.getHeight() - fontMetrics.getHeight()) / 2) - fontMetrics.getAscent());

        //Draw Loadable Text
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
