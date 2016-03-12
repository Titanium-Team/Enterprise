package de.titanium.enterprise.Utils;

/**
 * Created by Yonas on 08.03.2016.
 */
public class Area {

    private Position min;
    private Position max;

    public Area(Position a, Position b) {

        double minX = Math.min(a.getX(), b.getX());
        double minY = Math.min(a.getY(), b.getY());

        double maxX = Math.max(a.getX(), b.getX());
        double maxY = Math.max(a.getY(), b.getY());

        this.min = new Position(minX, minY);
        this.max = new Position(maxX, maxY);


    }

    public boolean contains(Position position) {

        return  position.getX() >= this.min.getX() &&
                position.getX() <= this.max.getX() &&
                position.getY() >= this.min.getY() &&
                position.getY() <= this.max.getY();

    }

}
