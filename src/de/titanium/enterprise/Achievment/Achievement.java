package de.titanium.enterprise.Achievment;

import de.titanium.enterprise.Sprite.Texture;

/**
 * Created by Yonas on 29.03.2016.
 */
public interface Achievement {

    /**
     * Gibt den Namen des Achievements zurueck, damit dieser dem Nutzer angezeigt werden kann.
     * @return
     */
    String getName();

    /**
     * Gibt die Beschreibung des Achievements zurueck.
     * @return
     */
    String getDescription();

    /**
     * Gibt die Texture zurueck die eingeblendet werden soll, wenn das Achievement freigeschaltet wird.
     * @return
     */
    Texture getTexture();

}
