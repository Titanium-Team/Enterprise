package de.titanium.enterprise.View.Views;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.Menu.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;

/**
 * Created by Yonas on 11.03.2016.
 */
public class FightView extends View {

    public FightView(MenuView viewMenu) {
        super(viewMenu);
    }

    @Override
    public void update(double deltaTime, int tick) {

    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        //graphics.setColor(Color.RED);
        //graphics.fillRect(0,0,1080,600)
        graphics.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);


    }

    @Override
    public void render() {}

}
