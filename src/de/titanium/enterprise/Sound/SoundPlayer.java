package de.titanium.enterprise.Sound;

import javax.sound.sampled.Clip;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yonas on 13.04.2016.
 */
public class SoundPlayer extends Thread {

    private List<Clip> playList = new LinkedList<>();
    private int current = 0;
    private boolean isPlaying = true;

    public SoundPlayer() {}

    public void add(Sound sound) {
        this.playList.add(sound.getSound());
    }

    public Clip getCurrent() {
        return this.playList.get(this.current);
    }

    @Override
    public void run() {

        while (true) {

            if(this.isPlaying) {

                if (!this.playList.get(this.current).isRunning()) {
                    this.current++;

                    if (this.current >= this.playList.size()) {
                        this.current = 0;
                    }


                    this.playList.get(this.current).setMicrosecondPosition(0);
                    this.playList.get(this.current).start();
                }
            }

        }

    }

    public synchronized void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;

        if(!(this.isPlaying)) {
            this.getCurrent().stop();
        }
    }

}
