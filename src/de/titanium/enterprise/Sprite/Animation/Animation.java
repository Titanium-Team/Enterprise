package de.titanium.enterprise.Sprite.Animation;

import de.titanium.enterprise.Loading.Loadable;

/**
 * Created by Yonas on 16.03.2016.
 */
public interface Animation extends Loadable {

    Animator getAnimator();

    int getHeight();

    double getWidthScale();

}
