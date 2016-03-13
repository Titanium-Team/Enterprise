package de.titanium.enterprise.View.DefenseGame;

import java.awt.*;

/**
 * Created by Yonas on 13.03.2016.
 */
public enum DefenseModules implements DefenseModule {

    LINE {

        @Override
        public Rectangle[] getRectangles(int x, int space, int height) {

            return new Rectangle[] {
                    new Rectangle(x, 0, 200, height),
                    new Rectangle(x, height + space, 200, 120 - (height + space))
            };

        }

    },
    STAIR {

        @Override
        public Rectangle[] getRectangles(int x, int space, int height) {

            Rectangle rectangle = new Rectangle(x, 0, 100, height);
            rectangle.setFrameFromDiagonal(x, 0, x + 100, height);

            return new Rectangle[] {rectangle};
        }

    }

}
