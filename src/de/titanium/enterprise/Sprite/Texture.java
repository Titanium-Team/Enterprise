package de.titanium.enterprise.Sprite;

import java.awt.*;

/**
 * Created by Yonas on 12.03.2016.
 */
public interface Texture {

    /**
     * Gibt die X-Position in dem Sprite-Sheet der oberen, linken Ecke des Bildes zurück.
     * @return
     */
    int getX();

    /**
     * Gibt die Y-Position in dem Sprite-Sheet der oberen, linken Ecke des Bildes zurück.
     * @return
     */
    int getY();

    /**
     * Gibt die Höhe der Texture zurück.
     * @return
     */
    int getHeight();

    /**
     * Gibt die Breite der Texture zurück.
     * @return
     */
    int getWidth();

    /**
     * Gibt das Image zurück.
     * @return
     */
    Image getImage();

}
