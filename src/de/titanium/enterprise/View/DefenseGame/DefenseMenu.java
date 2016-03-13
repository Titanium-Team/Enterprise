package de.titanium.enterprise.View.DefenseGame;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.View.Menu.MenuView;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Yonas on 12.03.2016.
 */
public class DefenseMenu extends MenuView implements GameComponent {

    private final Random random = new Random();
    private List<Rectangle[]> rectangles = new ArrayList<>();

    private int space = 40;
    private int tick = 0;

    public DefenseMenu() {
        Enterprise.getGame().addComponent(this);
        this.keyListener();

        for(int i = 0; i < 5; i++) {
            this.rectangles.add(DefenseModules.LINE.getRectangles(1080 + i * 270, this.space, 20));
        }
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

        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

        Iterator<Rectangle[]> iterator = this.rectangles.iterator();
        while (iterator.hasNext()) {
            for(Rectangle rectangle : iterator.next()) {
                g.fillRect(
                        (int) rectangle.getX(),
                        (int) rectangle.getY(),
                        (int) rectangle.getWidth(),
                        (int) rectangle.getHeight()
                );
            }
        }

    }

    @Override
    public void update(double deltaTime, int tick) {

        this.tick++;

        //Alle 5 Sekunden wird der Abstand zwischen den Beiden Modulen um 1 verringert.
        if(this.tick % 100 == 0) {
            this.space--;
        }

        //Updaten aller Module
        Iterator<Rectangle[]> rectangles = this.rectangles.iterator();
        Queue<Rectangle[]> tmp = new LinkedTransferQueue<>();

        while (rectangles.hasNext()) {

            Rectangle[] rec = rectangles.next();

            //Falls das letzte Elemente sich nicht mehr im Screen befindet wird es entfernt und ein neues wird hinzugefügt.

            int x = 10;
             if(rec[rec.length-1].x + rec[rec.length-1].getWidth() < 0) {
                rectangles.remove();

                Rectangle[] last = this.rectangles.get(this.rectangles.size() - 1);
                tmp.add(DefenseModules.values()[this.random.nextInt(DefenseModules.values().length)].getRectangles(1080 - x, this.space, (int) last[last.length - 2].getHeight()));
            } else {
                for (Rectangle rectangle : rec) {
                    rectangle.x -= x / 2;
                }
            }

        }

        this.rectangles.addAll(tmp);

    }

    @Override
    public void render() {
        this.revalidate();
        this.repaint();
    }

    @Override
    public boolean isActive() {
        return true;
    }

}
