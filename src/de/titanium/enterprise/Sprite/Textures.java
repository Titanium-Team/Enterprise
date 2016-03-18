package de.titanium.enterprise.Sprite;

import de.titanium.enterprise.Enterprise;

import javax.imageio.ImageIO;
import java.awt.*;
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
