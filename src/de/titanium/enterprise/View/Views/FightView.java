package de.titanium.enterprise.View.Views;

import de.titanium.enterprise.View.Menu.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;

/**
 * Created by Yonas on 11.03.2016.
 */
public class FightView extends View {

    public FightView(MenuView viewMenu) {
        super(viewMenu);

        this.setMaximumSize(new Dimension(1080, 600));
        this.setMinimumSize(new Dimension(1080, 600));
        this.setPreferredSize(new Dimension(1080, 600));
    }

    @Override
    public void update(double deltaTime, int tick) {

    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        graphics.setColor(Color.RED);
        graphics.fillRect(0, 0, 1080, 600);

    }

    @Override
    public void render() {}

}
