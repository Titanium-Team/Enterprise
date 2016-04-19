package de.titanium.enterprise.Sprite;

import de.titanium.enterprise.Loading.Loadable;

import java.awt.image.BufferedImage;

public interface Texture extends Loadable {

    /**
     * Gibt das Image zurueck.
     * @return
     */
    BufferedImage getImage();

}
