package de.titanium.enterprise.View.LoadingView;

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
        }, 800, 800);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("Loading" + this.value, (this.getWidth() / 2) - 100, this.getHeight() / 2);

    }

    @Override
    public void update(int tick) {}

    @Override
    public void render() {}

}
