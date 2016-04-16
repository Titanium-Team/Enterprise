package de.titanium.enterprise.Sound;

import de.titanium.enterprise.Loading.Loadable;

import javax.sound.sampled.Clip;

public interface Sound extends Loadable {

    /**
     * Gibt den Sound zur�ck, der gespielt werden soll.
     * @return
     */
    Clip getSound();

}
