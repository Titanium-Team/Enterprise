package de.titanium.enterprise.Achievment;

import de.titanium.enterprise.Sprite.Texture;

public interface Achievement {

    /**
     * Returns the name of the achievement.
     * @return
     */
    String getName();

    /**
     * Returns the description of the achievement.
     * @return
     */
    String getDescription();

    /**
     * Returns the related texture.
     * @return
     */
    Texture getTexture();

}
