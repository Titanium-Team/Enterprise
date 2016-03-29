package de.titanium.enterprise.Achievment;

import de.titanium.enterprise.Sprite.Texture;

/**
 * Created by Yonas on 29.03.2016.
 */
public interface Achievement {

    /**
     * Gibt den Namen des Achievements zur�ck, damit dieser dem Nutzer angezeigt werden kann.
     * @return
     */
    String getName();

    /**
     * Gibt die Texture zur�ck die eingeblendet werden soll, wenn das Achievement freigeschaltet wird.
     * @return
     */
    Texture getTexture();

}
