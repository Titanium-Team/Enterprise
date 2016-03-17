package de.titanium.enterprise.Sprite.Animation;

import java.awt.image.BufferedImage;

/**
 * Created by Yonas on 16.03.2016.
 */
public class Animator {

    private Animation type;
    private final BufferedImage[] textures;
    private int currentFrame = 0;
    private final int delay;

    public Animator(Animation type, BufferedImage[] textures, int delay) {
        this.type = type;
        this.textures = textures;
        this.delay = delay;
    }

    public Animation getType() {
        return this.type;
    }

    public void next() {
        if(this.currentFrame < this.textures.length - 1) {
            this.currentFrame++;
        } else {
            this.currentFrame = 0;
        }
    }

    public int getAmount() {
        return this.textures.length;
    }

    public int getIndex() {
        return this.currentFrame;
    }

    public BufferedImage getFrame() {
        return this.textures[this.currentFrame];
    }

}
