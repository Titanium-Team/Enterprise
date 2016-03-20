package de.titanium.enterprise.View.FightView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.DefenseGame.DefenseMenu;
import de.titanium.enterprise.View.MenuView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by Yonas on 11.03.2016.
 */
public class FightMenu extends MenuView implements GameComponent {

    private final Random random = new Random();

    //Time
    private long currentTime = System.currentTimeMillis();
    private long time = 5000;

    //Buttons
    private int heroOne = KeyEvent.VK_A;
    private boolean pressedOne = true;
    private boolean tmpOne = false;

    private char heroTwo = KeyEvent.VK_B;
    private boolean pressedTwo = true;
    private boolean tmpTwo = false;

    private char heroThree = KeyEvent.VK_C;
    private boolean pressedThree = true;
    private boolean tmpThree = false;

    public FightMenu() {}

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());


        if(this.pressedOne) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(KeyEvent.getKeyText(this.heroOne), 10), 190, 60, null);
        }

        if(this.pressedTwo) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(KeyEvent.getKeyText(this.heroTwo), 15), 490, 60, null);
        }

        if(this.pressedThree) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(KeyEvent.getKeyText(this.heroThree), 20), 790, 60, null);
        }


        //Border
        g.drawImage(Textures.BORDER_DOWN.getImage(), 0, 0, null, null);

    }

    @Override
    public void update(int tick) {

        if(!(this.tmpOne)) {
            this.tmpOne = Enterprise.getGame().getKeyManager().isPressed(this.heroOne);
        }

        if(!(this.tmpTwo)) {
            this.tmpTwo = Enterprise.getGame().getKeyManager().isPressed(this.heroTwo);
        }

        if(!(this.tmpThree)) {
            this.tmpThree = Enterprise.getGame().getKeyManager().isPressed(this.heroThree);
        }

        //Prüfen ob der Spieler innerhalb der Zeit die Button gedrückt hat
        if(this.currentTime + this.time < System.currentTimeMillis()) {
            this.pressedOne = this.tmpOne;
            this.pressedTwo = this.tmpTwo;
            this.pressedThree = this.tmpThree;

            this.tmpOne = false;
            this.tmpTwo = false;
            this.tmpThree = false;

            this.currentTime = System.currentTimeMillis();

            //Falls er es nicht geschafft hat einen Button zudrücken wird das DefenseGame aufgerufen
            if (!(this.pressedTwo) && !(this.pressedTwo) && !(this.pressedThree)) {
                Enterprise.getGame().getViewManager().changeMenu(FightView.class, new DefenseMenu());
            }

        }

    }


    @Override
    public void render() {
        super.repaint();
    }

}
