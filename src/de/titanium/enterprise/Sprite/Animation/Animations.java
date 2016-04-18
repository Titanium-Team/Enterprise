package de.titanium.enterprise.Sprite.Animation;

import de.titanium.enterprise.Enterprise;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum Animations implements Animation {

    RANGER_ATTACK {

        private final BufferedImage[] frames = new BufferedImage[52];


        @Override
        public String getName() {
            return "Ranger Attack Animation";
        }

        @Override
        public void load() {

            BufferedImage image = Animations.loadImage("/assets/animations/ranger_attack_animation.png");

            int x = 0;
            for (int i = 0; i < 26; i++) {
                this.frames[i] = image.getSubimage(x * 481, 1, 479, 462);
                x++;
            }

            x = 0;
            for (int i = 26; i < 52; i++) {
                this.frames[i] = image.getSubimage(x * 481, 465, 479, 462);
                x++;
            }

        }

        @Override
        public Animator createAnimator() {

            return new Animator(this, this.frames, 1);

        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public int getWidth() {
            return 126;
        }

    },
    RANGER_IDLE {

        private final BufferedImage[] frames = new BufferedImage[60];

        @Override
        public String getName() {
            return "Ranger Idle Animation";
        }

        @Override
        public void load() {

            BufferedImage image = Animations.loadImage("/assets/animations/ranger_idle_animation.png");

            int x = 0;
            for (int i = 0; i < 30; i++) {
                this.frames[i] = image.getSubimage(x * 365, 1, 363, 438);
                x++;
            }

            x = 0;
            for (int i = 30; i < 60; i++) {
                this.frames[i] = image.getSubimage(x * 365, 441, 363, 438);
                x++;
            }


        }

        @Override
        public Animator createAnimator() {

            return new Animator(this, this.frames, 1);

        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public int getWidth() {
            return 126;
        }

    },
    RANGER_DIE {

        private final BufferedImage[] frames = new BufferedImage[22];

        @Override
        public String getName() {
            return "Ranger Die Animation";
        }

        @Override
        public void load() {

            BufferedImage image = Animations.loadImage("/assets/animations/ranger_die_animation.png");

            for (int i = 0; i < 22; i++) {
                this.frames[i] = image.getSubimage(i * 652, 1, 650, 568);
            }

        }

        @Override
        public Animator createAnimator() {
            return new Animator(this, this.frames, 1);
        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public int getWidth() {
            return 155;
        }

    },
    RANGER_BLOCK {

        private final BufferedImage[] frames = new BufferedImage[25];

        @Override
        public String getName() {
            return "Ranger Block Animation";
        }

        @Override
        public void load() {

            BufferedImage image = Animations.loadImage("/assets/animations/ranger_block_animation.png");

            for (int i = 0; i < 25; i++) {
                this.frames[i] = image.getSubimage(i * 449, 1, 447, 456);
            }

        }

        @Override
        public Animator createAnimator() {
            return new Animator(this, this.frames, 2);
        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public int getWidth() {
            return 126;
        }
    },
    RANGER_WALK {

        private final BufferedImage[] frames = new BufferedImage[50];

        @Override
        public String getName() {
            return "Ranger Walk Animation";
        }

        @Override
        public void load() {

            BufferedImage image = Animations.loadImage("/assets/animations/ranger_walk_animation.png");

            int x = 0;
            for (int i = 0; i < 25; i++) {
                this.frames[i] = image.getSubimage(x * 375, 1, 373, 465);
                x++;
            }

            x = 0;
            for (int i = 25; i < 50; i++) {
                this.frames[i] = image.getSubimage(x * 375, 468, 373, 465);
                x++;
            }

        }

        @Override
        public Animator createAnimator() {
            return new Animator(this, this.frames, 1);
        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public int getWidth() {
            return 135;
        }

    },
    ARCHER_IDLE {

        private final BufferedImage[] frames = new BufferedImage[60];

        @Override
        public String getName() {
            return "Archer Idle Animation";
        }

        @Override
        public void load() {

            BufferedImage image = Animations.loadImage("/assets/animations/archer_idle_animation.png");

            int x = 0;
            for (int i = 0; i < 30; i++) {
                this.frames[i] = image.getSubimage(x * 341, 1, 339, 428);
                x++;
            }

            x = 0;
            for (int i = 30; i < 60; i++) {
                this.frames[i] = image.getSubimage(x * 341, 431, 339, 428);
                x++;
            }


        }

        @Override
        public Animator createAnimator() {
            return new Animator(this, this.frames, 1);
        }

        @Override
        public int getHeight() {
            return 168;
        }

        @Override
        public int getWidth() {
            return 126;
        }

    },
    TORCH {

        private final BufferedImage[] frames = new BufferedImage[9];

        @Override
        public String getName() {
            return "Torch Animation";
        }

        @Override
        public void load() {

            BufferedImage image = Animations.loadImage("/assets/animations/animated_torch.png");

            for(int i = 0; i < 9; i++) {
                this.frames[i] = image.getSubimage(i * 32, 0, 32, 64);
            }

        }

        @Override
        public Animator createAnimator() {
            return new Animator(this, this.frames, 4);
        }

        @Override
        public int getHeight() {
            return 32;
        }


        @Override
        public int getWidth() {
            return 288;
        }

    };

    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Enterprise.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
