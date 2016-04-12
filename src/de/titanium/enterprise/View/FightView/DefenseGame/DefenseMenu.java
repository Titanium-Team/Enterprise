package de.titanium.enterprise.View.FightView.DefenseGame;

import de.titanium.enterprise.Achievment.Achievements;
import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Data.Datas.Score;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.Entities.Rogue;
import de.titanium.enterprise.Entity.Entities.Warrior;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Entity.Statistic.Statistics;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.FightView.FightMenu;
import de.titanium.enterprise.View.FightView.FightView;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
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

    //Die Groesse des Bewegungsbereiches
    private int space = 40;
    //Die Starthoehe der Module
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
        if(!(this.player == null)) {
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

                        // @Achievement
                        if(this.tick == 197) {
                            Enterprise.getGame().getAchievementManager().add(Achievements.DEFENSESCORE_197);
                        }

                        // Hier wird der Score dem BinaryTree hinzugefuegt.
                        if(!Enterprise.getGame().getDataManager().contains("game.defense.scores")) {
                            Enterprise.getGame().getDataManager().set("game.defense.scores", new BinarySearchTree<Score>());
                        }

                        // @Improve: Das hier ist erstmal eine erste Idee wie das ganze Aussehen koennte.
                        // Damit wird wenigstens schonmal etwas hier haben.
                        LivingEntity enemy = Enterprise.getGame().getDataManager().get("game.enemy");
                        LivingEntity hero = Enterprise.getGame().getDataManager().get("game.fight.maxDamage");

                        // Hier wird festgelegt wie hoch die KeyStreak ist und für den Rogue gelten besondere
                        // Regeln.
                        int keyStreak = this.random.nextInt(30) + 1;
                        double damage = enemy.calculateDamage(hero, keyStreak);

                        if(hero instanceof Warrior){
                            keyStreak = this.random.nextInt(50) + 1;
                            damage = enemy.calculateDamage(hero, keyStreak);
                        } else if (hero instanceof Rogue) {
                            if((this.random.nextInt(20) + 1) > ((int) hero.getDexterity())) {
                                damage = enemy.calculateDamage(hero, (int) hero.getDexterity());
                                Enterprise.getGame().getLogger().info("Damage Enemy -> " + damage + " -> Keys: " + hero.getDexterity());
                            }
                        } else Enterprise.getGame().getLogger().info("Damage Enemy -> " + damage + " -> Keys: " + keyStreak);

                        double defense = hero.calculateDefense(enemy, this.tick);

                        Enterprise.getGame().getLogger().info("Defense Value Player -> " + defense);

                        // Den Score für den abgewerten Schaden updaten
                        hero.getGameStatistic().update(Statistics.DAMAGE_BLOCKED, this.tick);

                        // Das Leben von dem Helden abziehen
                        hero.setHealth(
                                hero.getHealth() - (damage - defense)
                        );

                        // Den Wert für den höchsten Defense-Score
                        hero.getGameStatistic().update(Statistics.HIGHEST_DEFENSE_SCORE, this.tick);

                        // Falls der Held gestorben ist.
                        if(!(hero.isAlive())) {
                            hero.getAnimationQueue().add(Animations.RANGER_DIE);
                        }

                        boolean allDead = true;

                        // @Idea: Nun wird geprueft ob alle gestorben sind, falls ja, dann wird der End-Screen angezeigt.
                        for(LivingEntity entity : Enterprise.getGame().getDataManager().<LivingEntity[]>get("game.heroes")) {

                            if(entity.isAlive()) {
                                allDead = false;
                                // Falls noch mindestens ein Hero lebt, dann geht es weiter im Spiel und es wird im
                                // FightMenu der Angriff fortgesetzt.
                                Enterprise.getGame().getViewManager().changeMenu(FightView.class, new FightMenu());
                                break;

                            }

                        }

                        if(!(Enterprise.getGame().getDataManager().contains("game.tmp.score"))) {
                            Enterprise.getGame().getDataManager().set("game.tmp.score", Double.class);
                        }

                        Enterprise.getGame().getDataManager().set("game.tmp.score", Enterprise.getGame().getDataManager().<Double>get("game.tmp.score").doubleValue() + (this.tick / 100));

                        if(allDead) {
                            if(!(Enterprise.getGame().getDataManager().contains("game.scores"))) {
                                Enterprise.getGame().getDataManager().set("game.scores", new BinarySearchTree<Double>());
                            }

                            Enterprise.getGame().getDataManager().<BinarySearchTree<Double>>get("game.scores").insert(Enterprise.getGame().getDataManager().<Double>get("game.tmp.score"));
                            Enterprise.getGame().getDataManager().set("game.tmp.score", 0);

                            // @Idea: Falls das nicht der Fall ist, dann wird der Game-End-Screen angezeigt.
                            // Aktuell wird man einfach noch ins Hauptmenue zurueckgebracht.
                            Enterprise.getGame().getViewManager().switchTo(GameMenuView.class);
                        }
                        break;
                    }
                }
            }

            // @Achievement
            if(this.tick >= 1000) {
                Enterprise.getGame().getAchievementManager().add(Achievements.DEFENSESCORE_1000);
            }
            if(this.tick >= 2000) {
                Enterprise.getGame().getAchievementManager().add(Achievements.DEFENSESCORE_2000);
            }
            if(this.tick >= 5000) {
                Enterprise.getGame().getAchievementManager().add(Achievements.DEFENSESCORE_5000);
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

            //Falls das letzte Elemente sich nicht mehr im Screen befindet wird es entfernt und ein neues wird hinzugefuegt.
             if(rec[rec.length-1].x + rec[rec.length-1].getWidth() < 0) {
                rectangles.remove();

                Rectangle[] last = this.rectangles.get(this.rectangles.size() - 1);
                tmp.add(DefenseModules.values()[this.random.nextInt(DefenseModules.values().length-1)].getRectangles((int) (last[last.length - 2].getX() + last[last.length -2].getWidth() - this.speed), this.space, this.width, (int) last[last.length - 2].getHeight()));
             } else {
                for (Rectangle rectangle : rec) {
                    rectangle.x -= this.speed / 2;
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
