package de.titanium.enterprise.View.FightView.DefenseGame;

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
                    new Rectangle(x, 0, width, height),
                    new Rectangle(x, height + space, width, 140 - (height + space))
            };

        }

    },
    STAIR {

        @Override
        public Rectangle[] getRectangles(int x, int space, int width, int height) {

            Random random = new Random();
            Rectangle[] rectangles = new Rectangle[44];
            //Die Steigung der Treppe
            int deltaY = -(height) + random.nextInt(140 - (space + height) + (height));

            if(deltaY < 20 && deltaY > 0) {
                deltaY = 20;
            } else if(deltaY > -20 && deltaY < 0) {
                deltaY = -20;
            }

            if(deltaY > 60) {
                deltaY = 60;
            } else if(deltaY < -60) {
                deltaY = -60;
            }

            if(deltaY > 20 && deltaY < 60 ){
                deltaY = 40;
            }else if(deltaY < -20 && deltaY > -60){
                deltaY = -40;
            }

            rectangles[0] = new Rectangle(x, 0, ((width - 100) / 2), height);
            rectangles[1] = new Rectangle(x, height + space, ((width - 100) / 2), 140 - (space + height));
            rectangles[42] = new Rectangle(x + (width - 100) - 10, 0, ((width - 100) / 2), height + deltaY);
            rectangles[43] = new Rectangle(x + (width - 100) - 10, (height + deltaY) + space, ((width - 100) / 2), (140 - (space + height)) - deltaY);


            deltaY /= 20;

            for(int i = 2; i < 42; i += 2) {

                height += deltaY;
                rectangles[i] = new Rectangle(x+((width - 100) / 2)-5+(i/2)*5, 0, 5, height);
                rectangles[i+1] = new Rectangle(x+((width - 100) / 2)-5+(i/2)*5, (height + space), 5, 140 - (space + height));

            }

            return rectangles;
        }

    },
    //Dies muss am Ende des Enums sein, weil es nur am Start aufgerufen werden soll und nicht zufällif.
    START {
        @Override
        public Rectangle[] getRectangles(int x, int space, int width, int height) {


            Rectangle[] rectangles = new Rectangle[22];

            int temp = 1;
            int delta1 = height;
            int delta2 = 140 - (height + space);

            //Stufen
            for(int i = 0; i < 22; i += 2) {

                rectangles[i] = new Rectangle(x + temp * 20, 0, 10, delta1 / 10 * temp);
                rectangles[i + 1] = new Rectangle(x + temp * 20, 140 - delta2 / 10 * temp, 10, delta2 / 10 * temp);
                temp++;
            }

            rectangles[20] = new Rectangle(x + 200, 0, width - 200, height);
            rectangles[21] = new Rectangle(x + 200, height + space, width - 200, 140 - (space + height));

            return rectangles;
        }
    }

}
