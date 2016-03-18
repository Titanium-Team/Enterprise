package de.titanium.enterprise.View.LoadingView;

import de.titanium.enterprise.View.View;

import java.awt.*;

/**
 * Created by Yonas on 17.03.2016.
 */
public class LoadingView extends View {

    private String value = ".";

    public LoadingView() {
        super(new LoadingMenu());
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawString("Loading...", this.getWidth() / 2, this.getHeight() / 2);

    }

    @Override
    public void update(int tick) {}

    @Override
    public void render() {

    }

}
