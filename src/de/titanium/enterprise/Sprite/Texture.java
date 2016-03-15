package de.titanium.enterprise.Sprite;

import java.awt.*;

/**
 * Created by Yonas on 12.03.2016.
 */
public interface Texture {

    /**
     * Gibt die X-Position in dem Sprite-Sheet der oberen, linken Ecke des Bildes zur�ck.
     * @return
     */
    int getStartX();

    /**
     * Gibt die Y-Position in dem Sprite-Sheet der oberen, linken Ecke des Bildes zur�ck.
     * @return
     */
    int getStartY();

    /**
     * Gibt die X-Position in dem Sprite-Sheet der unteren, rechten Ecke des Bildes zur�ck.
     * @return
     */
    int getEndX();

    /**
     * Gibt die Y-Position in dem Sprite-Sheet der unteren, rechten Ecke des Bildes zur�ck.
     * @return
     */
    int getEndY();

    /**
     * Gibt das Image zur�ck.
     * @return
     */
    Image getImage();

}
