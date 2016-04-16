package de.titanium.enterprise.Sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.LinkedList;
import java.util.List;

public class SoundPlayer extends Thread {

    private List<Clip> playList = new LinkedList<>();
    private int current = 0;
    private double volume = 25D;

    public SoundPlayer() {}

    /**
     * Fügt der List einen neuen Sound hinzu.
     * @param sound
     */
    public void add(Sound sound) {
        this.playList.add(sound.getSound());
    }

    /**
     * Gibt den Sound, der aktuell gespielt wird, zurück.
     * @return
     */
    public Clip getCurrent() {
        return this.playList.get(this.current);
    }

    @Override
    public void run() {

        while (true) {

            if (!this.playList.get(this.current).isRunning()) {
                this.current++;

                if (this.current >= this.playList.size()) {
                    this.current = 0;
                }

                this.updateVolume(this.volume);
                this.playList.get(this.current).setMicrosecondPosition(0);
                this.playList.get(this.current).start();
            }

        }

    }

    /**
     * Updated das aktuelle Volume.
     * @param volume
     */
    public void updateVolume(double volume) {
        this.volume = volume;

        if(!(this.playList.isEmpty())) {
            // @See: http://helpdesk.objects.com.au/java/how-to-control-volume-of-audio-clip
            FloatControl gainControl = (FloatControl) this.playList.get(this.current).getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(this.volume / 100) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }

    }

    /**
     * Gibt die aktuelle Lautstaerke zurueck.
     * @return
     */
    public double getVolume() {
        return this.volume;
    }
}
