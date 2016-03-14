package de.titanium.enterprise.View.DefenseGame;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.View.Menu.MenuView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by Yonas on 12.03.2016.
 */
public class DefenseMenu extends MenuView implements GameComponent {

    private final Random random = new Random();
    private List<Rectangle[]> rectangles = new ArrayList<>();
    private Rectangle player = null;

    private int space = 40;
    private final int height = 20;
    private final int width = 320;
    private int tick = 0;

    public DefenseMenu() {
        Enterprise.getGame().addComponent(this);

        for(int i = 0; i < 5; i++) {
            this.rectangles.add(DefenseModules.LINE.getRectangles(1280 + i * this.width, this.space, this.width, this.height));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        //g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        g.setColor(new Color(238, 100, 86));

        //Alle Elemente einzeichnen
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

        //Den Spieler zeichnen
        g.setColor(Color.BLACK);

        if(!(player == null)) {
            g.fillRect(
                    (int) this.player.getX(),
                    (int) this.player.getY(),
                    (int) this.player.getWidth(),
                    (int) this.player.getHeight()
            );
        }

    }

    @Override
    public void update(double deltaTime, int tick) {

        this.tick++;

        //Alle 20 Sekunden wird der Abstand zwischen den Beiden Modulen um 1 verringert.
        if(this.tick == 1000) {
            this.space--;
            this.tick = 0;
        }

        //Es wird auf die Tastatureingabe reagiert
        if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_W)) {
            this.player.y -= 3;
        }
        if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_S)) {
            this.player.y += 3;
        }

        //Einen neuen Spieler erstellen, falls es ihn noch nicht gibt
        if(this.player == null) {
            this.player = new Rectangle(this.getWidth() / 2, this.height + this.space / height, 5, 5);
        } else { //Collision detection

            for(Rectangle[] rectangles : this.rectangles) {
                for(Rectangle r : rectangles) {
                    if(this.player.intersects(r)) {
                        //TODO Collision detected
                        break;
                    }
                }
            }

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
                tmp.add(DefenseModules.values()[this.random.nextInt(DefenseModules.values().length)].getRectangles(1280 - x, this.space, this.width, (int) last[last.length - 2].getHeight()));

                System.out.println("Distance: " + ((1280 - x) - (last[last.length - 2].getX())));
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
