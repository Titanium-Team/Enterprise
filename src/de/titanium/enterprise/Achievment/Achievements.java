package de.titanium.enterprise.Achievment;

import de.titanium.enterprise.Sprite.Texture;
import de.titanium.enterprise.Sprite.Textures;

/**
 * Created by Yonas on 29.03.2016.
 */
public enum Achievements implements Achievement {

    TEST {
        @Override
        public String getName() {
            return "Test";
        }

        @Override
        public Texture getTexture() {
            return Textures.TEST;
        }
    }

}
