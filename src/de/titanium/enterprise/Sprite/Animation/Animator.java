package de.titanium.enterprise.Sprite.Animation;

import de.titanium.enterprise.Sprite.Texture;

/**
 * Created by Yonas on 16.03.2016.
 */
public class Animator {

    private final Texture[] textures;
    private int currentFrame = 0;

    public Animator(Texture[] textures) {
        this.textures = textures;
    }

}
