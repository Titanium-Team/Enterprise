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

    private Queue<Rectangle[]> rectangles = new LinkedTransferQueue<>();

    private int space = 50;
    private int tick = 0;

    public DefenseMenu() {
        Enterprise.getGame().addComponent(this);
        this.keyListener();

        for(int i = 0; i < 5; i++) {
            this.rectangles.add(DefenseModules.LINE.getRectangles(1080 + i * 270, this.space, 80));
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

        g.setColor(Color.BLACK);
        for(Rectangle[] set : this.rectangles) {
            for(Rectangle rectangle : set) {
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
            //this.space--;
        }

        //Updaten aller Module
        Iterator<Rectangle[]> iterator = this.rectangles.iterator();
        Queue<Rectangle[]> tmp = new LinkedTransferQueue<>();

        while (iterator.hasNext()) {

            Rectangle[] rectangles = iterator.next();

            if(rectangles[rectangles.length-1].x + rectangles[rectangles.length-1].getWidth() < 0) {
                iterator.remove();
                tmp.add(DefenseModules.STAIR.getRectangles(1070, this.space,(int) rectangles[rectangles.length - 2].getHeight()));
                System.out.println("Unten: " + rectangles[rectangles.length -1].getHeight());
                System.out.println("Oben: " +  rectangles[rectangles.length -2].getHeight());
            } else {
                for (Rectangle rectangle : rectangles) {
                    rectangle.x -= 5;
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
