package de.titanium.enterprise.KeyManager;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Yonas on 14.03.2016.
 */
public class KeyManager extends KeyAdapter {

    private int keyCode = -1;

    public KeyManager() {
        this.register();
    }

    private void register() {

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {

                synchronized (KeyManager.class) {

                    if(e.getID() == KeyEvent.KEY_PRESSED) {
                        keyCode = e.getKeyCode();
                    } else if(e.getID() == KeyEvent.KEY_RELEASED) {
                        keyCode = -1;
                    }

                }

                return false;
            }

        });

    }


    public boolean isPressed(int keyCode) {

        return (this.keyCode == keyCode);

    }

}
