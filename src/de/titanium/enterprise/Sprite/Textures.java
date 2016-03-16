package de.titanium.enterprise.Sprite;

import de.titanium.enterprise.Enterprise;

import java.awt.*;

/**
 * Created by Yonas on 12.03.2016.
 */
public enum Textures implements Texture {

    BACKGROUND {
        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 540;
        }

        @Override
        public int getWidth() {
            return 1280;
        }

        @Override
        public Image getImage() {
            return Enterprise.getGame().getSpriteSheetManager().getBackground().get(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    },
    BORDER_UP {

        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 543;
        }

        @Override
        public int getWidth() {
            return 1280;
        }

        @Override
        public Image getImage() {
            return Enterprise.getGame().getSpriteSheetManager().getHeroes().get(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }

    },
    BORDER_DOWN {

        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 544;
        }

        @Override
        public int getHeight() {
            return 180;
        }

        @Override
        public int getWidth() {
            return 1280;
        }

        @Override
        public Image getImage() {
            return Enterprise.getGame().getSpriteSheetManager().getHeroes().get(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }

}
