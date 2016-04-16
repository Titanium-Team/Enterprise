package de.titanium.enterprise;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyManager extends KeyAdapter {

    private int keyCode = -1;

    public KeyManager() {
        this.register();
    }

    private void register() {

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {

                if(e.getID() == KeyEvent.KEY_PRESSED) {
                    keyCode = e.getKeyCode();
                } else if(e.getID() == KeyEvent.KEY_RELEASED) {
                    keyCode = -1;
                }

                return false;
            }


        });

    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }


    /**
     * Diese Methode prueft, ob der Key, der angegeben wurde, gedrueckt wurde.
     * @param keyCode
     * @return
     */
    public boolean isPressed(int keyCode) {

        return (this.keyCode == keyCode);

    }

    /**
     * Gibt true zur�ck, wenn aktuell irgendeine Taste gedr�ckt wird.
     * @return
     */
    public boolean isPressed() {
        return (this.keyCode != -1);
    }

    /**
     * Gibt den aktullen Key-Code der gedr�ckten Taste zur�ck.
     * @return
     */
    public int getKeyCode() {
        return keyCode;
    }

}
