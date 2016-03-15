package de.titanium.enterprise.View.DefenseGame;

import java.awt.*;
import java.util.Random;

/**
 * Created by Yonas on 13.03.2016.
 */
public enum DefenseModules implements DefenseModule {

    LINE {

        @Override
        public Rectangle[] getRectangles(int x, int space, int width, int height) {

            return new Rectangle[] {
                    new Rectangle(x, 20, width, height - 20),
                    new Rectangle(x, height + space, width, 160 - (height + space))
            };

        }

    },
    STAIR {

        @Override
        public Rectangle[] getRectangles(int x, int space, int width, int height) {

            Random random = new Random();
            Rectangle[] rectangles = new Rectangle[44];
            //Die Steigung der Treppe
            //min + ran(max - min)
            int deltaY = -(height - 20) + random.nextInt(160 - (space + height) + (height - 20));
            if(deltaY < 20 && deltaY > 0) {
                deltaY = 20;
            } else if(deltaY > -20 && deltaY < 0) {
                deltaY = -20;
            }

            //Der Wert wird auf einen Wert abgerundet, der durch 20 teilbar ist, damit es zu keinen Rundungsfehlen kommt, bei den weiteren Rechenschritten.
            while(deltaY % 20 != 0) {
                deltaY--;
            }

            rectangles[0] = new Rectangle(x, 20, ((width - 100) / 2), height - 20);
            rectangles[1] = new Rectangle(x, height + space, ((width - 100) / 2), 160 - (space + height));
            rectangles[42] = new Rectangle(x + (width - 100) - 10, 20, ((width - 100) / 2), height + deltaY - 20);
            rectangles[43] = new Rectangle(x + (width - 100) - 10, (height + deltaY) + space, ((width - 100) / 2), (160 - (space + height)) - deltaY);


            deltaY /= 20;

            for(int i = 2; i < 42; i += 2) {

                height += deltaY;
                rectangles[i] = new Rectangle(x+((width - 100) / 2)-5+(i/2)*5, 20, 5, height - 20);
                rectangles[i+1] = new Rectangle(x+((width - 100) / 2)-5+(i/2)*5, (height + space), 5, 160 - (space + height));

            }

            return rectangles;
        }

    },
    //Dies muss am Ende des Enums sein, weil es nur am Start aufgerufen werden soll und nicht zufällif.
    START {
        @Override
        public Rectangle[] getRectangles(int x, int space, int width, int height) {


            Rectangle[] rectangles = new Rectangle[42];

            int temp = 1;
            int delta1 = height - 20;
            int delta2 = 160 - (height + space);

            //Stufen
            for(int i = 0; i < 42; i += 2) {

                rectangles[i] = new Rectangle(x + temp * 10, 20, 10, delta1 / 20 * temp);
                rectangles[i + 1] = new Rectangle(x + temp * 10, 160 - delta2 / 20 * temp, 10, delta2 / 20 * temp);
                temp++;
            }

            rectangles[40] = new Rectangle(x + 200, 20, width - 200, height - 20);
            rectangles[41] = new Rectangle(x + 200, height + space, width - 200, 160 - (space + height));

            return rectangles;
        }
    }

}
