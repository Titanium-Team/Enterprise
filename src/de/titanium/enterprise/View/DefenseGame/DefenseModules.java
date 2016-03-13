package de.titanium.enterprise.View.DefenseGame;

import java.awt.*;
import java.util.Random;

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

            Random random = new Random();
            Rectangle[] rectangles = new Rectangle[24];

            int deltaY = -height + random.nextInt(120 - (space + height) + height );
            deltaY = (deltaY < 10 && deltaY > -10 ? 10 : deltaY);

            rectangles[0] = new Rectangle(x, 0, 50, height);
            rectangles[1] = new Rectangle(x, height + space, 50, 120 - (space + height));
            rectangles[22] = new Rectangle(x+150, 0, 50, height + deltaY);
            rectangles[23] = new Rectangle(x+150, (height + deltaY) + space, 50, (120 - (space + height)) + deltaY);

            deltaY /= 10;

            for(int i = 2; i < 22; i += 2) {

                height += deltaY;
                rectangles[i] = new Rectangle(x+40+(i/2)*10, 0, 10, height);
                rectangles[i+1] = new Rectangle(x+40+(i/2)*10, (height + space), 10, 120 - (space + height));

            }

            return rectangles;
        }

    }

}
