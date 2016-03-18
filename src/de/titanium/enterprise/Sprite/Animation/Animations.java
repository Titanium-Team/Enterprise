package de.titanium.enterprise.Sprite.Animation;

import de.titanium.enterprise.Enterprise;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by Yonas on 16.03.2016.
 */
public enum Animations implements Animation {

    RANGER_ATTACK {

        private final BufferedImage[] frames = new BufferedImage[52];
        private BufferedImage image;


        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/ranger_attack_animation.png");

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

            return new Animator(this, this.frames, 1);

        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public double getWidthScale() {
            return 0.1555;
        }

    },
    RANGER_IDLE {

        private final BufferedImage[] frames = new BufferedImage[60];
        private BufferedImage image = null;

        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/ranger_idle_animation.png");
            int x = 0;
            for (int i = 0; i < 9; i++) {
                this.frames[i] = this.image.getSubimage(x * 872, 1, 870, 1052);
                x++;
            }

            x = 0;
            for (int i = 9; i < 18; i++) {
                this.frames[i] = this.image.getSubimage(x * 872, 1055, 870, 1052);
                x++;
            }

            x = 0;
            for (int i = 18; i < 27; i++) {
                this.frames[i] = this.image.getSubimage(x * 872, 2109, 870, 1052);
                x++;
            }

            x = 0;
            for (int i = 27; i < 36; i++) {
                this.frames[i] = this.image.getSubimage(x * 872, 3163, 870, 1052);
                x++;
            }

            x = 0;
            for (int i = 36; i < 45; i++) {
                this.frames[i] = this.image.getSubimage(x * 872, 4217, 870, 1052);
                x++;
            }

            x = 0;
            for (int i = 45; i < 54; i++) {
                this.frames[i] = this.image.getSubimage(x * 872, 5271, 870, 1052);
                x++;
            }

            x = 0;
            for (int i = 54; i < 60; i++) {
                this.frames[i] = this.image.getSubimage(x * 872, 6325, 870, 1052);
                x++;
            }

        }


        @Override
        public Animator getAnimator() {

            return new Animator(this, this.frames, 1);

        }

        @Override
        public int getHeight() {
            return 168; //168
        }

        @Override
        public double getWidthScale() {
            return 0.15209125475; //197.2
        }

    },
    RANGER_DIE {

        private final BufferedImage[] frames = new BufferedImage[22];
        private BufferedImage image = null;

        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/ranger_die_animation.png");
            int x = 0;
            for (int i = 0; i < 8; i++) {
                this.frames[i] = this.image.getSubimage(x * 1562, 0, 1560, 1362);
                x++;
            }

            x = 0;
            for (int i = 8; i < 16; i++) {
                this.frames[i] = this.image.getSubimage(x * 1562, 1365, 1560, 1362);
                x++;
            }

            x = 0;
            for (int i = 16; i < 22; i++) {
                this.frames[i] = this.image.getSubimage(x * 1562, 2729, 1560, 1362);
                x++;
            }
        }

        @Override
        public Animator getAnimator() {
            return new Animator(this, this.frames, 1);
        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public double getWidthScale() {
            return 0.1076923076;
        }

    },
    RANGER_BLOCK {

        private final BufferedImage[] frames = new BufferedImage[25];
        private BufferedImage image = null;

        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/ranger_block_animation.png");
            int x = 0;
            for (int i = 0; i < 13; i++) {
                this.frames[i] = this.image.getSubimage(x * 1074, 1, 1072, 1095);
                x++;
            }

            x = 0;
            for (int i = 13; i < 25; i++) {
                this.frames[i] = this.image.getSubimage(x * 1074, 1098, 1072, 1095);
                x++;
            }

        }

        @Override
        public Animator getAnimator() {
            return new Animator(this, this.frames, 2);
        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public double getWidthScale() {
            return 0.1567164179104478;
        }
    },
    ARCHER_IDLE {

        private final BufferedImage[] frames = new BufferedImage[60];
        private BufferedImage image = null;

        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/archer_idle_animation.png");
            int x = 0;
            for (int i = 0; i < 20; i++) {
                this.frames[i] = this.image.getSubimage(x * 816, 1, 814, 1028);
                x++;
            }

            x = 0;
            for (int i = 20; i < 40; i++) {
                this.frames[i] = this.image.getSubimage(x * 816, 1031, 814, 1028);
                x++;
            }

            x = 0;
            for (int i = 40; i < 60; i++) {
                this.frames[i] = this.image.getSubimage(x * 816, 2061, 814, 1028);
                x++;
            }
        }

        @Override
        public Animator getAnimator() {
            return new Animator(this, this.frames, 1);
        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public double getWidthScale() {
            return 0.206;
        }

    };

    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
