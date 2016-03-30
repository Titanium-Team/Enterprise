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

    /**
     * Gibt den Animations-Typen zurück.
     * @return
     */
    public Animation getType() {
        return this.type;
    }

    /**
     * Diese Methode geht zum nächsten Frame.
     *
     * Falls man beim letzten Frame angekommen ist, startet die Animation erneut.
     */
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

    /**
     * Gibt die Anzahl an Frames zurück, die diese Animation hat.
     * @return
     */
    public int getAmount() {
        return this.textures.length;
    }

    /**
     * Gibt den Index des aktullen Frames zurück.
     * @return
     */
    public int getIndex() {
        return this.currentFrame;
    }

    /**
     * Gibt den aktuellen Frame zurück, der gerendert werden soll.
     * @return
     */
    public BufferedImage getFrame() {
        return this.textures[this.currentFrame];
    }

    @Override
    public String toString() {
        return String.format(
                "{type: %s, textures: %s, skippedFrames: %d, skipFrames: %d, currentFrame: %d}",
                this.type,
                this.textures,
                this.skipFrames,
                this.skippedFrames,
                this.currentFrame
                );
    }

}
