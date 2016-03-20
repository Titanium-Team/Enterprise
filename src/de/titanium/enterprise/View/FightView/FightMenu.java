package de.titanium.enterprise.View.FightView;

import de.titanium.enterprise.Data.DataManager;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.DefenseGame.DefenseMenu;
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
        if(!(this.heroOne == null) && this.pressedOne && this.drawOne) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(this.heroOne.toString(), 20), 190, 60, null);
        } else if (!(this.pressedOne)) {
            Image image = Textures.FAILED_BUTTON.getImage();
            g.drawImage(image, 190, 60, (int) (image.getWidth(null) * 0.3), (int) (image.getHeight(null) * 0.3), null);
        } else if(this.pressedOne && !(this.drawOne)) {
            Image image = Textures.CHECKED_BUTTON.getImage();
            g.drawImage(image, 190, 60, (int) (image.getWidth(null) * 0.3), (int) (image.getHeight(null) * 0.3), null);
        }

        if(!(this.heroTwo == null) && this.pressedTwo && this.drawTwo) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(this.heroTwo.toString(), 20), 490, 60, null);
        } else if (!(this.pressedTwo)) {
            Image image = Textures.FAILED_BUTTON.getImage();
            g.drawImage(image, 490, 60, (int) (image.getWidth(null) * 0.3), (int) (image.getHeight(null) * 0.3), null);
        } else if(this.pressedTwo && !(this.drawTwo)) {
            Image image = Textures.CHECKED_BUTTON.getImage();
            g.drawImage(image, 490, 60, (int) (image.getWidth(null) * 0.3), (int) (image.getHeight(null) * 0.3), null);
        }

        if(!(this.heroThree == null) && this.pressedThree && this.drawThree) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(this.heroThree.toString(), 20), 790, 60, null);
        } else if (!(this.pressedThree)) {
            Image image = Textures.FAILED_BUTTON.getImage();
            g.drawImage(image, 790, 60, (int) (image.getWidth(null) * 0.3), (int) (image.getHeight(null) * 0.3), null);
        } else if(this.pressedThree && !(this.drawThree)) {
            Image image = Textures.CHECKED_BUTTON.getImage();
            g.drawImage(image, 790, 60, (int) (image.getWidth(null) * 0.3), (int) (image.getHeight(null) * 0.3), null);
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

        //Es wird geprüft ob der Button gedrückt wurde, falls er noch nicht gedrückt wurde
        if(!(this.tmpOne) && !(this.heroOne == null)) {
            this.tmpOne = Enterprise.getGame().getKeyManager().isPressed(this.heroOne);
            if(this.tmpOne) {
                this.drawOne = false;
            }
        }

        if(!(this.tmpTwo) && !(this.heroTwo == null)) {
            this.tmpTwo = Enterprise.getGame().getKeyManager().isPressed(this.heroTwo);
            if(this.tmpTwo) {
                this.drawTwo = false;
            }
        }

        if(!(this.tmpThree) && !(this.heroThree == null)) {
            this.tmpThree = Enterprise.getGame().getKeyManager().isPressed(this.heroThree);
            if(this.tmpThree) {
                this.drawThree = false;
            }
        }

        //Es wird nach der Zeit (ohne Abstand) geprüft ob er innerhalb der Zeit gedrückt wurde.
        if((this.currentTime + this.time) < System.currentTimeMillis()) {

            if(!(this.heroOne == null)) {

                this.pressedOne = this.tmpOne;
                this.tmpOne = false;
                this.heroOne = null;

                if(!(this.pressedOne)) {
                    this.chance--;
                } else {
                    this.comboOne++;
                }
            }

            if(!(this.heroTwo == null)) {

                this.pressedTwo = this.tmpTwo;
                this.tmpTwo = false;
                this.heroTwo = null;

                if(!(this.pressedTwo)) {
                    this.chance--;
                } else {
                    this.comboTwo++;
                }
            }

            if(!(this.heroThree == null)) {

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

            if(this.pressedOne) {

                //20% Wahrscheinlichkeit das ein neuer Button auftaucht
                boolean chance = (this.random.nextInt(this.chance) == 0);

                if(chance) {
                    this.heroOne = this.getRandomButton(this.areaOne, this.stageOne);
                    this.drawOne = true;
                }

            }

            if(this.pressedTwo) {

                //20% Wahrscheinlichkeit das ein neuer Button auftaucht
                boolean chance = (this.random.nextInt(this.chance) == 0);

                if(chance) {
                    this.heroTwo = this.getRandomButton(this.areaTwo, this.stageTwo);
                    this.drawTwo = true;
                }

            }

            if(this.pressedThree) {

                //20% Wahrscheinlichkeit das ein neuer Button auftaucht
                boolean chance = (this.random.nextInt(this.chance) == 0);

                if(chance) {
                    this.heroThree = this.getRandomButton(this.areaThree, this.stageThree);
                    this.drawThree = true;
                }

            }

            this.currentTime = System.currentTimeMillis();

            if(!(this.pressedOne) && !(this.pressedTwo) && !(this.pressedThree)) {
                DataManager dataManager = Enterprise.getGame().getDataManager();

                //Get Heroes
                LivingEntity[] heroes = dataManager.getOne("game.heroes", LivingEntity[].class);

                heroes[0].getAnimationQueue().add(Animations.RANGER_ATTACK);
                heroes[1].getAnimationQueue().add(Animations.RANGER_ATTACK);
                heroes[2].getAnimationQueue().add(Animations.RANGER_ATTACK);

                LivingEntity enemy = dataManager.getOne("game.enemy", LivingEntity.class);
                enemy.getAnimationQueue().add(Animations.RANGER_IDLE);
                enemy.getAnimationQueue().add(Animations.RANGER_BLOCK);

                //Calculate Damage
                double damageOne = heroes[0].calculateDamage(enemy, this.comboOne);
                double damageTwo = heroes[1].calculateDamage(enemy, this.comboTwo);
                double damageThree = heroes[2].calculateDamage(enemy, this.comboThree);

                //Switch to Defense Game
                Enterprise.getGame().getViewManager().changeMenu(FightView.class, new DefenseMenu());
            }
        }

        //Time und Time-Distance verringern sich
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
