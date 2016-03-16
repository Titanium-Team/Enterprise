package de.titanium.enterprise.Scores;

/**
 * Created by 204g01 on 16.03.2016.
 */
public interface Comparable<T> {

    boolean isGreater(T o);
    boolean isLess(T o);
    boolean isEqual(T o);

}

