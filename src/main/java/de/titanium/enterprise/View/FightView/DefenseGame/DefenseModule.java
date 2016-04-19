package de.titanium.enterprise.View.FightView.DefenseGame;

import java.awt.*;

public interface DefenseModule {

    /**
     * Gibt ein Array mit mehreren Rectangles zurueck, die dann gezeichnet werden koennen.
     * @param x
     * @param space
     * @param width
     * @param height
     * @return
     */
    Rectangle[] getRectangles(int x, int space, int width, int height);

}
