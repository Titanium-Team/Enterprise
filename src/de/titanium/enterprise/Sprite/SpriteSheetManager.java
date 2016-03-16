package de.titanium.enterprise.Sprite;

import java.io.IOException;

/**
 * Created by Yonas on 15.03.2016.
 */
public class SpriteSheetManager {

    private SpriteSheet heroes;
    private SpriteSheet background;

    public SpriteSheetManager() {
        try {
            this.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() throws IOException {

        this.heroes = new SpriteSheet("./assets/sprite.png");
        this.background = new SpriteSheet("./assets/background.png");

    }

    public SpriteSheet getHeroes() {
        return this.heroes;
    }

    public SpriteSheet getBackground() {
        return this.background;
    }
}
