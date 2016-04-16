package de.titanium.enterprise.View.FightView.DefenseGame;

import java.awt.*;

public interface DefenseModule {

    /**
     * Gibt ein Array mit mehreren Rectangles zur�ck, die dann gezeichnet werden k�nnen.
     * @param x
     * @param space
     * @param width
     * @param height
     * @return
     */
    Rectangle[] getRectangles(int x, int space, int width, int height);

}
