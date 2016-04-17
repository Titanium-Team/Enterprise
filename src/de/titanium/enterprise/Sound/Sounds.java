package de.titanium.enterprise.Sound;

import de.titanium.enterprise.Enterprise;

import javax.sound.sampled.*;
import java.io.IOException;

public enum Sounds implements Sound {

    MUSIC_ONE {

        private Clip clip;

        @Override
        public String getName() {
            return "Music - Super Awesome";
        }

        @Override
        public Clip getSound() {
            return this.clip;
        }

        @Override
        public void load() {
            this.clip = Sounds.loadSound("/assets/sounds/track 2.wav");
        }

    };

    private static Clip loadSound(String path) {

        try {

            AudioInputStream stream = AudioSystem.getAudioInputStream(Enterprise.class.getResource(path));

            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        return null;
    }

}
