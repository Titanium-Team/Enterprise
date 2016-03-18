package de.titanium.enterprise.Sprite;

import de.titanium.enterprise.Loading.Loadable;

import java.awt.image.BufferedImage;

/**
 * Created by Yonas on 12.03.2016.
 */
public interface Texture extends Loadable {

    /**
     * Gibt das Image zurück.
     * @return
     */
    BufferedImage getImage();

}
