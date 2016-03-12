package de.titanium.enterprise.View.Views;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.View.Menu.MenuView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Yonas on 12.03.2016.
 */
public class DefenseMenu extends MenuView implements GameComponent {

    private int x = 0;

    public DefenseMenu() {
        Enterprise.getGame().addComponent(this);
        this.keyListener();
    }

    private void keyListener() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("HELLO");
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawRect(x, 0, 20, 50);

    }

    @Override
    public void update(double deltaTime, int tick) {
        this.x += (deltaTime / 20);
    }

    @Override
    public void render() {
        this.repaint();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
