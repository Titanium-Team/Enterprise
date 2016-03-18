package de.titanium.enterprise.Sprite.Animation;

import java.awt.image.BufferedImage;

/**
 * Created by Yonas on 16.03.2016.
 */
public class Animator {

    private Animation type;

    private final BufferedImage[] textures;

    private final int skipFrames;
    private int skippedFrames = 0;

    private int currentFrame = 0;


    public Animator(Animation type, BufferedImage[] textures, int skipFrames) {
        this.type = type;
        this.textures = textures;
        this.skipFrames = skipFrames;
    }

    public Animation getType() {
        return this.type;
    }

    public void next() {
        if(this.skippedFrames < this.skipFrames) {
            this.skippedFrames++;
        } else {
            this.skippedFrames = 0;
            if (this.currentFrame < this.textures.length - 1) {
                this.currentFrame++;
            } else {
                this.currentFrame = 0;
            }
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
