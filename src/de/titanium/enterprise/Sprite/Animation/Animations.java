package de.titanium.enterprise.Sprite.Animation;

import de.titanium.enterprise.Enterprise;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Created by Yonas on 16.03.2016.
 */
public enum Animations implements Animation {

    RANGER_ATTACK {

        private final BufferedImage[] frames = new BufferedImage[52];
        private final BufferedImage image = Enterprise.getGame().getSpriteSheetManager().load("./assets/animations/ranger_attack_animation.png");
        {
            int x = 0;
            for (int i = 0; i < 13; i++) {
                this.frames[i] = this.image.getSubimage(x * 1080, 0, 1078, 1040);
                x++;
            }

            x = 0;
            for (int i = 13; i < 26; i++) {
                this.frames[i] = this.image.getSubimage(x * 1080, 1043, 1078, 1040);
                x++;
            }

            x = 0;
            for (int i = 26; i < 39; i++) {
                this.frames[i] = this.image.getSubimage(x * 1080, 2085, 1078, 1040);
                x++;
            }

            x = 0;
            for (int i = 39; i < 52; i++) {
                this.frames[i] = this.image.getSubimage(x * 1080, 3127, 1078, 1040);
                x++;
            }
        }

        @Override
        public Animator getAnimator() {

            return new Animator(this, this.frames.clone(), 50);

        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public double getWidthScale() {
            return 0.16153;
        }

    },
    RANGER_IDLE {

        private final BufferedImage[] frames = new BufferedImage[60];
        private final BufferedImage image = Enterprise.getGame().getSpriteSheetManager().load("./assets/animations/ranger_idle_animation.png");

        {
            int x = 0;
            for (int i = 0; i < 15; i++) {
                this.frames[i] = this.image.getSubimage(x * 818, 0, 816, 986);
                x++;
            }

            x = 0;
            for (int i = 15; i < 30; i++) {
                this.frames[i] = this.image.getSubimage(x * 818, 989, 816, 986);
                x++;
            }

            x = 0;
            for (int i = 30; i < 45; i++) {
                this.frames[i] = this.image.getSubimage(x * 818, 1977, 816, 986);
                x++;
            }

            x = 0;
            for (int i = 45; i < 60; i++) {
                this.frames[i] = this.image.getSubimage(x * 818, 2965, 816, 986);
                x++;
            }
        }

        @Override
        public Animator getAnimator() {

            return new Animator(this, this.frames.clone(), 50);

        }

        @Override
        public int getHeight() {
            return 168; //168
        }

        @Override
        public double getWidthScale() {
            return 0.2; //197.2
        }

    }

}
