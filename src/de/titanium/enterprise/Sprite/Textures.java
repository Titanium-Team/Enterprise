package de.titanium.enterprise.Sprite;

import de.titanium.enterprise.Enterprise;

import java.awt.*;

/**
 * Created by Yonas on 12.03.2016.
 */
public enum Textures implements Texture {

    RAHMEN_OBEN {

        @Override
        public int getStartX() {
            return 0;
        }

        @Override
        public int getStartY() {
            return 0;
        }

        @Override
        public int getEndX() {
            return 1280;
        }

        @Override
        public int getEndY() {
            return 522;
        }

        @Override
        public Image getImage() {
            return Enterprise.getGame().getSpriteSheetManager().getHeroes().get(this.getStartX(), this.getStartY(), this.getEndX(), this.getEndY());
        }

    }

}
