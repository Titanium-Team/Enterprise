package de.titanium.enterprise.Sprite.Animation;

import de.titanium.enterprise.Loading.Loadable;

/**
 * Created by Yonas on 16.03.2016.
 */
public interface Animation extends Loadable {

    /**
     * Erstellt einen neuen Animator, mit diesem Animations-Typen.
     * @return
     */
    Animator createAnimator();

    /**
     * Gibt die Größe des zu zeichnenden Bildes zurück.
     * @return
     */
    int getHeight();

    /**
     * Gibt die Breite des zu zeichnenden Bildes zurück.
     * @return
     */
    int getWidth();

}
