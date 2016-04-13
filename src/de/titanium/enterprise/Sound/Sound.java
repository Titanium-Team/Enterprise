package de.titanium.enterprise.Sound;

import de.titanium.enterprise.Loading.Loadable;

import javax.sound.sampled.Clip;

/**
 * Created by Yonas on 13.04.2016.
 */
public interface Sound extends Loadable {

    Clip getSound();

}
