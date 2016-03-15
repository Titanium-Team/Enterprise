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
    int getX();

    /**
     * Gibt die Y-Position in dem Sprite-Sheet der oberen, linken Ecke des Bildes zur�ck.
     * @return
     */
    int getY();

    /**
     * Gibt die H�he der Texture zur�ck.
     * @return
     */
    int getHeight();

    /**
     * Gibt die Breite der Texture zur�ck.
     * @return
     */
    int getWidth();

    /**
     * Gibt das Image zur�ck.
     * @return
     */
    Image getImage();

}
