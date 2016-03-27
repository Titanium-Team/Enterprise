package de.titanium.enterprise.View.FightView;

import de.titanium.enterprise.Data.DataManager;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Entity.Statistic.Statistics;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.FightView.DefenseGame.DefenseMenu;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.MenuView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Yonas on 11.03.2016.
 */
public class FightMenu extends MenuView implements GameComponent {

    private final Random random = new Random();

    //Time
    private long currentTime = System.currentTimeMillis();
    private int time = 1000;
    private int timeDistance = 500;

    //Hero One
    private Character heroOne = null;
    private boolean pressedOne = true;
    private boolean tmpOne = false;
    private boolean drawOne = true;
    private int comboOne = 0;

    private final static ArrayList<ArrayList<Integer>> areaOne = new ArrayList<>();
    private int stageOne = 0;

    //Hero Two
    private Character heroTwo = null;
    private boolean pressedTwo = true;
    private boolean tmpTwo = false;
    private boolean drawTwo = true;
    private int comboTwo = 0;

    private final static ArrayList<ArrayList<Integer>> areaTwo = new ArrayList<>();
    private int stageTwo = 0;

    //Hero Three
    private Character heroThree = null;
    private boolean pressedThree = true;
    private boolean tmpThree = false;
    private boolean drawThree = true;
    private int comboThree = 0;

    private final static ArrayList<ArrayList<Integer>> areaThree = new ArrayList<>();
    private int stageThree = 0;

    //Die Wahrscheinlichkeit das ein Button angezeigt wird
    private int chance = 3;

    static {
        areaOne.add(new ArrayList<Integer>() {{

            this.add(KeyEvent.VK_W);
            this.add(KeyEvent.VK_A);
            this.add(KeyEvent.VK_S);
            this.add(KeyEvent.VK_D);

        }});
        areaOne.add(new ArrayList<Integer>() {{

            this.add(KeyEvent.VK_Q);
            this.add(KeyEvent.VK_E);
            this.add(KeyEvent.VK_Y);
            this.add(KeyEvent.VK_X);
            this.add(KeyEvent.VK_C);

        }});

        areaTwo.add(new ArrayList<Integer>() {{

            this.add(KeyEvent.VK_T);
            this.add(KeyEvent.VK_F);
            this.add(KeyEvent.VK_G);
            this.add(KeyEvent.VK_H);

        }});
        areaTwo.add(new ArrayList<Integer>() {{

            this.add(KeyEvent.VK_R);
            this.add(KeyEvent.VK_Z);
            this.add(KeyEvent.VK_V);
            this.add(KeyEvent.VK_B);
            this.add(KeyEvent.VK_N);

        }});


        areaThree.add(new ArrayList<Integer>() {{

            this.add(KeyEvent.VK_I);
            this.add(KeyEvent.VK_J);
            this.add(KeyEvent.VK_K);
            this.add(KeyEvent.VK_L);

        }});
        areaThree.add(new ArrayList<Integer>() {{

            this.add(KeyEvent.VK_U);
            this.add(KeyEvent.VK_O);
            this.add(KeyEvent.VK_M);
            //Punkt und Komma noch

        }});
    }

    public FightMenu() {}

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //Button rendering
        LivingEntity[] heroes = Enterprise.getGame().getDataManager().getOne("game.heroes");
        Image failedImage = Textures.FAILED_BUTTON.getImage();
        Image checkedImage = Textures.CHECKED_BUTTON.getImage();

        if(!(heroes[0].isAlive())) { //Der Held ist verstorben
            g.drawImage(failedImage, 790, 60, (int) (failedImage.getWidth(null) * 0.3), (int) (failedImage.getHeight(null) * 0.3), null);
        } else if(!(this.heroOne == null) && this.pressedOne && this.drawOne) { //die nächste Taste anzeigen
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(this.heroOne.toString(), 20), 190, 60, null);
        } else if (!(this.pressedOne)) { //der Spieler hat die Taste verpasst
            g.drawImage(failedImage, 190, 60, (int) (failedImage.getWidth(null) * 0.3), (int) (failedImage.getHeight(null) * 0.3), null);
        } else if(this.pressedOne && !(this.drawOne)) { //der Spieler hat die aktuelle Taste geschafft
            g.drawImage(checkedImage, 190, 60, (int) (checkedImage.getWidth(null) * 0.3), (int) (checkedImage.getHeight(null) * 0.3), null);
        }

        if(!(heroes[1].isAlive())) { //Der Held ist verstorben
            g.drawImage(failedImage, 790, 60, (int) (failedImage.getWidth(null) * 0.3), (int) (failedImage.getHeight(null) * 0.3), null);
        } else if(!(this.heroTwo == null) && this.pressedTwo && this.drawTwo) { //die nächste Taste anzeigen
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(this.heroTwo.toString(), 20), 490, 60, null);
        } else if (!(this.pressedTwo)) { //der Spieler hat die Taste verpasst
            g.drawImage(failedImage, 490, 60, (int) (failedImage.getWidth(null) * 0.3), (int) (failedImage.getHeight(null) * 0.3), null);
        } else if(this.pressedTwo && !(this.drawTwo)) { //der Spieler hat die aktuelle Taste geschafft
            g.drawImage(checkedImage, 490, 60, (int) (checkedImage.getWidth(null) * 0.3), (int) (checkedImage.getHeight(null) * 0.3), null);
        }

        if(!(heroes[2].isAlive())) { //Der Held ist verstorben
            g.drawImage(failedImage, 790, 60, (int) (failedImage.getWidth(null) * 0.3), (int) (failedImage.getHeight(null) * 0.3), null);
        } else if(!(this.heroThree == null) && this.pressedThree && this.drawThree) { //die nächste Taste anzeigen
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(this.heroThree.toString(), 20), 790, 60, null);
        } else if (!(this.pressedThree)) { //der Spieler hat die Taste verpasst
            g.drawImage(failedImage, 790, 60, (int) (failedImage.getWidth(null) * 0.3), (int) (failedImage.getHeight(null) * 0.3), null);
        } else if(this.pressedThree && !(this.drawThree)) { //der Spieler hat die aktuelle Taste geschafft
            g.drawImage(checkedImage, 790, 60, (int) (checkedImage.getWidth(null) * 0.3), (int) (checkedImage.getHeight(null) * 0.3), null);
        }

        //Combo Rendering
        g.drawImage(Enterprise.getGame().getTextBuilder().toImage(String.valueOf(this.comboOne), 8), 195, 40, null);
        g.drawImage(Enterprise.getGame().getTextBuilder().toImage(String.valueOf(this.comboTwo), 8), 495, 40, null);
        g.drawImage(Enterprise.getGame().getTextBuilder().toImage(String.valueOf(this.comboThree), 8), 795, 40, null);

        //Border
        g.drawImage(Textures.BORDER_DOWN.getImage(), 0, 0, null, null);

    }

    @Override
    public void update(int tick) {

        LivingEntity[] heroes = Enterprise.getGame().getDataManager().getOne("game.heroes");

        //Es wird geprüft ob der Button gedrückt wurde, falls er noch nicht gedrückt wurde
        if(!(this.tmpOne) && !(this.heroOne == null) && heroes[0].isAlive()) {
            this.tmpOne = Enterprise.getGame().getKeyManager().isPressed(this.heroOne);
            if(this.tmpOne) {
                this.drawOne = false;
            }
        }

        if(!(this.tmpTwo) && !(this.heroTwo == null) && heroes[1].isAlive()) {
            this.tmpTwo = Enterprise.getGame().getKeyManager().isPressed(this.heroTwo);
            if(this.tmpTwo) {
                this.drawTwo = false;
            }
        }

        if(!(this.tmpThree) && !(this.heroThree == null) && heroes[2].isAlive()) {
            this.tmpThree = Enterprise.getGame().getKeyManager().isPressed(this.heroThree);
            if(this.tmpThree) {
                this.drawThree = false;
            }
        }

        //Es wird nach der Zeit (ohne Abstand) geprüft ob er innerhalb der Zeit gedrückt wurde.
        if((this.currentTime + this.time) < System.currentTimeMillis()) {

            if(!(this.heroOne == null) && heroes[0].isAlive()) {

                this.pressedOne = this.tmpOne;
                this.tmpOne = false;
                this.heroOne = null;

                if(!(this.pressedOne)) {
                    this.chance--;
                } else {
                    this.comboOne++;
                }
            }

            if(!(this.heroTwo == null) && heroes[1].isAlive()) {

                this.pressedTwo = this.tmpTwo;
                this.tmpTwo = false;
                this.heroTwo = null;

                if(!(this.pressedTwo)) {
                    this.chance--;
                } else {
                    this.comboTwo++;
                }
            }

            if(!(this.heroThree == null) && heroes[2].isAlive()) {

                this.pressedThree = this.tmpThree;
                this.tmpThree = false;
                this.heroThree = null;

                if(!(this.pressedThree)) {
                    this.chance--;
                } else {
                    this.comboThree++;
                }
            }

        }


        if((this.currentTime + this.time + this.timeDistance) < System.currentTimeMillis()) {

            if(this.pressedOne && heroes[0].isAlive()) {

                //20% Wahrscheinlichkeit das ein neuer Button auftaucht
                boolean chance = (this.random.nextInt(this.chance) == 0);

                if(chance) {
                    this.heroOne = this.getRandomButton(this.areaOne, this.stageOne);
                    this.drawOne = true;
                }

            }

            if(this.pressedTwo && heroes[1].isAlive()) {

                //20% Wahrscheinlichkeit das ein neuer Button auftaucht
                boolean chance = (this.random.nextInt(this.chance) == 0);

                if(chance) {
                    this.heroTwo = this.getRandomButton(this.areaTwo, this.stageTwo);
                    this.drawTwo = true;
                }

            }

            if(this.pressedThree && heroes[2].isAlive()) {

                //20% Wahrscheinlichkeit das ein neuer Button auftaucht
                boolean chance = (this.random.nextInt(this.chance) == 0);

                if(chance) {
                    this.heroThree = this.getRandomButton(this.areaThree, this.stageThree);
                    this.drawThree = true;
                }

            }

            this.currentTime = System.currentTimeMillis();

            if(!(this.pressedOne && heroes[0].isAlive()) && !(this.pressedTwo && heroes[1].isAlive()) && !(this.pressedThree && heroes[2].isAlive())) {
                DataManager dataManager = Enterprise.getGame().getDataManager();

                //queue animations
                if(heroes[0].isAlive()) {
                    heroes[0].getAnimationQueue().add(Animations.RANGER_ATTACK);
                }
                if(heroes[1].isAlive()) {
                    heroes[1].getAnimationQueue().add(Animations.RANGER_ATTACK);
                }
                if(heroes[2].isAlive()) {
                    heroes[2].getAnimationQueue().add(Animations.RANGER_ATTACK);
                }

                LivingEntity enemy = dataManager.getOne("game.enemy");
                enemy.getAnimationQueue().add(Animations.RANGER_IDLE);
                enemy.getAnimationQueue().add(Animations.RANGER_BLOCK);

                //Calculate Damage
                double damageOne = Math.max(heroes[0].calculateDamage(enemy, this.comboOne), 0);
                double damageTwo = Math.max(heroes[1].calculateDamage(enemy, this.comboTwo), 0);
                double damageThree = Math.max(heroes[2].calculateDamage(enemy, this.comboThree), 0);


                // Es wird sichergestellt das alle Funktionen einen gültigen Wert liefern.
                // Es sollte niemals NaN verwendet werden, oder eine Zahl die kleiner als 0 ist (?).
                // @Cleanup: Eventuell sollten die Funktionen von sich aus das prüfen?
                if(Double.isNaN(damageOne)) {
                    damageOne = 0;
                }

                if(Double.isNaN(damageTwo)) {
                    damageTwo = 0;
                }

                if(Double.isNaN(damageThree)) {
                    damageThree = 0;
                }

                //update statistics, damage dealt
                heroes[0].getGameStatistic().update(Statistics.DAMAGE_DEALT, damageOne);
                heroes[1].getGameStatistic().update(Statistics.DAMAGE_DEALT, damageOne);
                heroes[2].getGameStatistic().update(Statistics.DAMAGE_DEALT, damageOne);

                //update statistics, longest key streak
                heroes[0].getGameStatistic().update(Statistics.LONGEST_KEY_STREAK, this.comboOne);
                heroes[1].getGameStatistic().update(Statistics.LONGEST_KEY_STREAK, this.comboTwo);
                heroes[2].getGameStatistic().update(Statistics.LONGEST_KEY_STREAK, this.comboThree);

                // TODO @Improve:  Es muss noch ein Verteidigungswert berechnet werden, dieser wird dann von dem Schaden
                // abgezogen. Die Formel dafür wurde noch nicht aufgestellt.
                enemy.setHealth(
                        enemy.getHealth() - (damageOne + damageTwo + damageThree)
                );

                if(!(enemy.isAlive())) { //swich to main menu
                    // TODO Game End screen
                    // Wenn das Spiel bzw. die aktuelle Runde vorbei ist, sollte der Spieler eine Übersicht über seine
                    // Helden bekommen. Mit einigen Statistiken zum Kampf.
                    enemy.getAnimationQueue().add(Animations.RANGER_DIE);
                    System.out.println("You killed the enemy");
                    Enterprise.getGame().getViewManager().switchTo(GameMenuView.class);
                } else { //Switch to Defense Game
                    Enterprise.getGame().getViewManager().changeMenu(FightView.class, new DefenseMenu());
                }
            }
        }

        // Time und Time-Distance verringern sich
        // Jede Sekunde, also alle 50 Ticks, wird die Zeit zwischen den Tasten verringert, um 1ms, sollten mehrere Tasten
        // nicht gedrückt worden sein, werden es maximal 2-5ms/Sekunde weniger. Ist begrenz auf eine minstend Zeit von 500ms.
        // Sobald die Zeit zuwischen den Tasten unter 750ms rutscht, wird die Anzahl an Tasten die gedrückt werden müssen
        // erhöht.
        // @TODO: Falls eine Taste wegfällt, soll dieser Bereich auf eine Taste umgelagert werden. Dies kann ggf. auch in die
        // FightMenu#getRandomButton Methode ausgelagert werden?
        if(tick == 50) {
            int value = 0;
            if(!(this.pressedOne)) {
                value++;
            }
            if(!(this.pressedTwo)) {
                value++;
            }
            if(!(this.pressedThree)) {
                value++;
            }

            if(this.time - (2 + value) > 500) {
                this.time -= (2 + value);
            }

            if(this.timeDistance - (1 + value) > 100) {
                this.timeDistance -= (1 + value);
            }

            if(this.time < 750 && this.stageOne < 1) {
                this.stageOne++;
                this.stageTwo++;
                this.stageThree++;
            }
        }

    }

    /**
     * Diese Funktion gibt einen zufälligen Button aus allen verfügbaren Stages zurück.
     * @param lists
     * @param stage
     * @return
     */
    private Character getRandomButton(ArrayList<ArrayList<Integer>> lists, int stage) {

        ArrayList<Integer> keys = new ArrayList<>();

        for(int i = 0; i < stage+1; i++) {
            keys.addAll(lists.get(i));
        }

        return Character.valueOf(KeyEvent.getKeyText(keys.get(this.random.nextInt(keys.size()))).toCharArray()[0]);

    }

    @Override
    public void render() {
        super.repaint();
    }

}
