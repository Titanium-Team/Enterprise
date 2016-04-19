package de.titanium.enterprise.Sprite.Animation;

import java.awt.image.BufferedImage;

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
     * Gibt den Animations-Typen zurueck.
     * @return
     */
    public Animation getType() {
        return this.type;
    }

    /**
     * Diese Methode geht zum naechsten Frame.
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
     * Gibt die Anzahl an Frames zurueck, die diese Animation hat.
     * @return
     */
    public int getAmount() {
        return this.textures.length;
    }

    /**
     * Gibt den Index des aktullen Frames zurueck.
     * @return
     */
    public int getIndex() {
        return this.currentFrame;
    }

    /**
     * Gibt den aktuellen Frame zurueck, der gerendert werden soll.
     * @return
     */
    public BufferedImage getFrame() {
        return this.textures[this.currentFrame];
    }

    /**
     * Setzt den Index des aktuellen Frames.
     * @param index
     */
    public void setIndex(int index) {
        if(index < 0 || index >= this.textures.length) {
            throw new IndexOutOfBoundsException(String.format("Index out of bounds (%d >= 0 || %d < %d).", index, index, this.textures.length));
        }

        this.currentFrame = index;
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
