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
