package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;

import java.awt.*;

/**
 * Created by Yonas on 22.03.2016.
 */
public class DefaultMenu extends MenuView {

    public DefaultMenu() {}

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //Border
        g.drawImage(Textures.BORDER_DOWN.getImage(), 0, 0, null, null);

    }

    @Override
    public void update(int tick) {

    }

    @Override
    public void render() {

    }

}
