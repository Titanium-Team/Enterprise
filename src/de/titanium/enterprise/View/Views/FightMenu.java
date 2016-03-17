package de.titanium.enterprise.View.Views;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.View.Menu.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by Yonas on 11.03.2016.
 */
public class FightMenu extends MenuView implements GameComponent {

    private final Random random = new Random();

    private int countdown = 3;

    private JButton hero_one = new JButton("-");
    private JButton hero_two = new JButton("-");
    private JButton hero_three = new JButton("-");

    private char char_one = ' ';
    private boolean pressed_one = true;

    private String char_two = "__";
    private boolean pressed_two = false;

    private String char_three = "__";
    private boolean pressed_three = false;

    public FightMenu() {

        this.add(hero_one);
        this.add(hero_two);
        this.add(hero_three);

        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {

                if(char_one != ' ' && e.getKeyChar() == char_one) {
                    pressed_one = true;
                }

            }

        });

        Enterprise.getGame().addComponent(this);

    }

    @Override
    public void update(int tick) {

        this.requestFocus();
        if(tick == 20) {

            //Countdown
            if(this.countdown <= 3 && this.countdown >= 0) {

                this.hero_one.setText(String.format("%d", this.countdown));
                this.hero_two.setText(String.format("%d", this.countdown));
                this.hero_three.setText(String.format("%d", this.countdown));
                this.countdown--;

            } else {

                this.char_one = (this.pressed_one ? /*(char)(this.random.nextInt(26) + 'a')*/'a' : ' ');
                this.hero_one.setBackground(this.pressed_one ? Color.GREEN : Color.RED);
                this.hero_one.setText(Character.toString(this.char_one));

                this.pressed_one = false;

            }

        }
    }

    private void updateButtons() {

    }

    @Override
    public void render() {}

}
