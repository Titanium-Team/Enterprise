package de.titanium.enterprise.View.FightView.DefenseGame;

import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Data.Datas.Score;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.FightView.FightMenu;
import de.titanium.enterprise.View.FightView.FightView;
import de.titanium.enterprise.View.MenuView;

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

    //Die Größe des Bewegungsbereiches
    private int space = 40;
    //Die Starthöhe der Module
    private final int height = 50;
    //Die Breite der Module
    private final int width = 320;
    //Die Anzahl der Ticks
    private int tick = 0;
    //Die Geschwindigkeit der Module
    private int speed = 10;
    //Die Geschwindigkeit des Spielers
    private int movement = 2;

    public DefenseMenu() {
        this.rectangles.add(DefenseModules.START.getRectangles(1600, this.space, this.width, this.height));
        for(int i = 1; i < 6; i++) {
            this.rectangles.add(DefenseModules.LINE.getRectangles(1600 + i * this.width, this.space, this.width, this.height));
        }
    }


    @Override
    protected void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        g.setColor(new Color(130,(this.tick/40 >= 170 ? 0 : 180 - this.tick/40), 30));

        //Alle Elemente einzeichnen
        Iterator<Rectangle[]> iterator = this.rectangles.iterator();
        for (int i = 0; i < this.rectangles.size(); i++) {
            Iterator<Rectangle> rectangles = Arrays.asList(this.rectangles.get(i).clone()).iterator();
            while (rectangles.hasNext()) {
                Rectangle rectangle = rectangles.next();
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
        //Die Punkteanzahl zeichnen
        g.drawString("Punkte: " + this.tick, 950, 20);

        //Border
        g.drawImage(Textures.BORDER_DOWN.getImage(), 0, 0, null, null);

    }

    @Override
    public void update(int tick) {

        this.tick++;

        //Alle 10 Sekunden wird der Abstand zwischen den Beiden Modulen um 1 verringert.
        if(this.tick % 500 == 0 && this.speed < 20){
            this.speed += 2;
            if(this.speed == 14 || this.speed == 18){
                this.movement++;
            }
        }

        //Alle 10 Sekunden wird der Abstand zwischen den Beiden Modulen um 1 verringert.
        if(this.tick % 500 == 0 && this.space > 20) {
            this.space--;
        }


        //Einen neuen Spieler erstellen, falls es ihn noch nicht gibt
        if(this.player == null) {
            this.player = new Rectangle(this.getWidth() / 2, this.height + this.space / 2, 5, 5);
        } else { //Collision detection

            for(Rectangle[] rectangles : this.rectangles) {
                for(Rectangle r : rectangles) {
                    if(this.player.intersects(r)) {

                        Score score = new Score(this.tick,"Score:");
                        if(!Enterprise.getGame().getDataManager().contains("game.defense.scores")) {
                            Enterprise.getGame().getDataManager().add("game.defense.scores", new BinarySearchTree<Score>());
                        }

                        Enterprise.getGame().getDataManager().getOne("game.defense.scores", BinarySearchTree.class).insert(score);
                        Enterprise.getGame().getViewManager().changeMenu(FightView.class, new FightMenu());
                        break;
                    }
                }
            }

        }

        //Es wird auf die Tastatureingabe reagiert
        if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_W)) {
            this.player.y -= (this.player.y > 4 ? this.movement : 0 );
        }
        if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_S)) {
            this.player.y += (this.player.y < 134 ? this.movement : 0);
        }

        //Updaten aller Module
        Iterator<Rectangle[]> rectangles = this.rectangles.iterator();
        Queue<Rectangle[]> tmp = new LinkedTransferQueue<>();

        while (rectangles.hasNext()) {

            Rectangle[] rec = rectangles.next();

            //Falls das letzte Elemente sich nicht mehr im Screen befindet wird es entfernt und ein neues wird hinzugefügt.
             if(rec[rec.length-1].x + rec[rec.length-1].getWidth() < 0) {
                rectangles.remove();

                Rectangle[] last = this.rectangles.get(this.rectangles.size() - 1);
                tmp.add(DefenseModules.values()[this.random.nextInt(DefenseModules.values().length-1)].getRectangles((int) (last[last.length - 2].getX() + last[last.length -2].getWidth() - this.speed), this.space, this.width, (int) last[last.length - 2].getHeight()));
             } else {
                for (Rectangle rectangle : rec) {
                    rectangle.x -= this.speed/2;
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

}
