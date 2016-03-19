package de.titanium.enterprise.Sprite.Animation;

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
        public String getName() {
            return "Ranger Attack Animation";
        }

        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/ranger_attack_animation.png");

            int x = 0;
            for (int i = 0; i < 26; i++) {
                this.frames[i] = this.image.getSubimage(x * 481, 1, 479, 462);
                x++;
            }

            x = 0;
            for (int i = 26; i < 52; i++) {
                this.frames[i] = this.image.getSubimage(x * 481, 465, 479, 462);
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
        public int getWidth() {
            return 162;
        }

    },
    RANGER_IDLE {

        private final BufferedImage[] frames = new BufferedImage[60];
        private BufferedImage image = null;

        @Override
        public String getName() {
            return "Ranger Idle Animation";
        }

        @Override
        public void load() {

            this.image = Animations.loadImage("./assets/animations/ranger_idle_animation.png");

            int x = 0;
            for (int i = 0; i < 30; i++) {
                this.frames[i] = this.image.getSubimage(x * 365, 1, 363, 438);
                x++;
            }

            x = 0;
            for (int i = 30; i < 60; i++) {
                this.frames[i] = this.image.getSubimage(x * 365, 441, 363, 438);
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
        public int getWidth() {
            return 180;
        }

    },
    RANGER_DIE {

        private final BufferedImage[] frames = new BufferedImage[22];
        private BufferedImage image = null;

        @Override
        public String getName() {
            return "Ranger Die Animation";
        }

        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/ranger_die_animation.png");

            for (int i = 0; i < 22; i++) {
                this.frames[i] = this.image.getSubimage(i * 652, 1, 650, 568);
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
        public int getWidth() {
            return 146;
        }

    },
    RANGER_BLOCK {

        private final BufferedImage[] frames = new BufferedImage[25];
        private BufferedImage image = null;

        @Override
        public String getName() {
            return "Ranger Block Animation";
        }

        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/ranger_block_animation.png");

            for (int i = 0; i < 25; i++) {
                this.frames[i] = this.image.getSubimage(i * 449, 1, 447, 456);
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
        public int getWidth() {
            return 171;
        }
    },
    ARCHER_IDLE {

        private final BufferedImage[] frames = new BufferedImage[60];
        private BufferedImage image = null;

        @Override
        public String getName() {
            return "Archer Idle Animation";
        }

        @Override
        public void load() {
            this.image = Animations.loadImage("./assets/animations/archer_idle_animation.png");

            int x = 0;
            for (int i = 0; i < 30; i++) {
                this.frames[i] = this.image.getSubimage(x * 341, 1, 339, 428);
                x++;
            }

            x = 0;
            for (int i = 30; i < 60; i++) {
                this.frames[i] = this.image.getSubimage(x * 341, 431, 339, 428);
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
        public int getWidth() {
            return 212;
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
