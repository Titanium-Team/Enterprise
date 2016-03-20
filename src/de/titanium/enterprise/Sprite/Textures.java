package de.titanium.enterprise.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yonas on 12.03.2016.
 */
public enum Textures implements Texture {

    BACKGROUND {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Background Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/background.png");
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    BORDER_UP {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Border-UP Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/border.png").getSubimage(1, 1, 1280, 543);
        }


        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    BORDER_DOWN {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Border-DOWN Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/border.png").getSubimage(1, 546, 1280, 181);
        }


        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_0 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-0 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1, 1, 231, 270);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_1 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-1 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(234, 1, 165, 266);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_2 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-2 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(401, 1, 233, 275);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_3 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-3 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(636, 1, 233, 275);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_4 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-2 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(871, 1, 235, 275);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_5 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-5 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1108, 1, 225, 274);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_6 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-6 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1335, 1, 233, 273);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_7 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-7 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1570, 1, 211, 277);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_8 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-8 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1783, 1, 230, 276);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_9 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-9 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(2015, 1, 219, 270);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_A {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-A Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(2236, 1, 238, 265);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_B {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-B Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(2476, 1, 209, 265);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_C {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-C Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1, 280, 220, 263);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_D {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-D Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(223, 280, 227, 261);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_E {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-E Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(452, 280, 196, 268);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_F {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-F Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(650, 280, 181, 267);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_G {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-G Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(833, 280, 290, 269);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_H {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-H Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1125, 280, 225, 259);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_I {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-I Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1352, 280, 129, 265);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_J {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-J Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1483, 280, 192, 265);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_K {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-K Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1677, 280, 220, 267);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_L {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-L Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1899, 280, 206, 270);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_M {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-M Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(2107, 280, 206, 270);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_N {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-N Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(2414, 280, 229, 262);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_O {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-O Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(2645, 280, 234, 265);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_P {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-P Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1, 555, 234, 268);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_Q {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Q Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(209, 555, 275, 269);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_R {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-R Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(486, 555, 275, 269);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_S {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-S Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(732, 555, 212, 268);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_T {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-T Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(946, 555, 234, 268);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_U {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-U Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1182, 555, 241, 263);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_V {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-V Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1425, 555, 243, 263);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_W {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-W Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1670, 555, 309, 263);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_X {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-X Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(1981, 555, 246, 271);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_Y {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Y Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(2229, 555, 255, 259);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_Z {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Z Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("./assets/alphabet.png").getSubimage(2486, 555, 241, 267);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
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
