package de.titanium.enterprise.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yonas on 15.03.2016.
 */
public class SpriteSheetManager {

    private SpriteSheet heroes;

    public SpriteSheetManager() {
        try {
            this.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage load(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }

    private void load() throws IOException {

        this.heroes = new SpriteSheet("./assets/sprite.png");

    }

    public SpriteSheet getHeroes() {
        return this.heroes;
    }

}
