package de.titanium.enterprise.View.HeroesView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.Entities.Archer;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.util.UUID;

/**
 * Created by Yonas on 23.03.2016.
 */
public class HeroesView extends View {

    private Archer archer = new Archer(UUID.randomUUID(), "Yonas", 100, 100, 5, 5, 5);

    public HeroesView(MenuView viewMenu) {
        super(viewMenu);
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //draw background and frame
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        //Draw hero
        for(int x = 0; x < 400; x++) {
            g.drawLine(900 - x, 300 - (int) archer.calculateDamage(null, x), 900 - x, 300 - (int) archer.calculateDamage(null, x+1));
        }

    }

    @Override
    public void update(int tick) {

    }

    @Override
    public void render() {

    }

}
