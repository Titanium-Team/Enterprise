package de.titanium.enterprise.Sprite.Animation;

import de.titanium.enterprise.Loading.Loadable;

public interface Animation extends Loadable {

    /**
     * Erstellt einen neuen Animator, mit diesem Animations-Typen.
     * @return
     */
    Animator createAnimator();

    /**
     * Gibt die Groesse des zu zeichnenden Bildes zurueck.
     * @return
     */
    int getHeight();

    /**
     * Gibt die Breite des zu zeichnenden Bildes zurueck.
     * @return
     */
    int getWidth();

}
