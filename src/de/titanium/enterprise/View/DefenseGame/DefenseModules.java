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
                    new Rectangle(x, 0, 320, height),
                    new Rectangle(x, height + space, 320, 120 - (height + space))
            };

        }

    },
    STAIR {

        @Override
        public Rectangle[] getRectangles(int x, int space, int height) {

            Random random = new Random();
            Rectangle[] rectangles = new Rectangle[44];

            //Die Steigung der Treppe
            int deltaY = -height + random.nextInt(120 - (space + height) + height);
            if(deltaY < 20 && deltaY > 0) {
                deltaY = 20;
            } else if(deltaY > -20 && deltaY < 0) {
                deltaY = -20;
            }

            //Der Wert wird auf einen Wert abgerundet, der durch 20 teilbar ist, damit es zu keinen Rundungsfehlen kommt, bei den weiteren Rechenschritten.
            while(deltaY % 20 != 0) {
                deltaY--;
            }

            rectangles[0] = new Rectangle(x, 0, 110, height);
            rectangles[1] = new Rectangle(x, height + space, 110, 120 - (space + height));
            rectangles[42] = new Rectangle(x+210, 0, 110, height + deltaY);
            rectangles[43] = new Rectangle(x+210, (height + deltaY) + space, 110, (120 - (space + height)) - deltaY);

            deltaY /= 20;

            for(int i = 2; i < 42; i += 2) {

                height += deltaY;
                rectangles[i] = new Rectangle(x+105+(i/2)*5, 0, 5, height);
                rectangles[i+1] = new Rectangle(x+105+(i/2)*5, (height + space), 5, 120 - (space + height));

            }

            return rectangles;
        }

    }

}
