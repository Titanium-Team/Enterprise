package de.titanium.enterprise.Sound;

import de.titanium.enterprise.Loading.Loadable;

import javax.sound.sampled.Clip;

public interface Sound extends Loadable {

    /**
     * Gibt den Sound zurück, der gespielt werden soll.
     * @return
     */
    Clip getSound();

}
