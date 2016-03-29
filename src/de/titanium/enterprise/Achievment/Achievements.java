package de.titanium.enterprise.Achievment;

import de.titanium.enterprise.Sprite.Texture;
import de.titanium.enterprise.Sprite.Textures;

/**
 * Created by Yonas on 29.03.2016.
 */
public enum Achievements implements Achievement {

    WELCOME {

        // Wird freigeschaltet, wenn der Spieler zum ersten mal das Spiel startet.

        @Override
        public String getName() {
            return "Welcome";
        }

        @Override
        public String getDescription() {
            return "Willkommen im Spiel.";
        }

        @Override
        public Texture getTexture() {
            return Textures.TEST;
        }

    },
    DAMAGE_5 {

        // Wird freigeschaltet, wenn der Spieler 5+ Damage aufeinmal macht.

        @Override
        public String getName() {
            return "kleiner mann";
        }

        @Override
        public String getDescription() {
            return "5+ Schaden";
        }

        @Override
        public Texture getTexture() {
            return Textures.TEST;
        }

    },
    DEFENSESCORE_2000 {

        // Wird freigeschaltet, wenn der Spieler einen Defense-Score von 2000 oder mehr erreicht.

        @Override
        public String getName() {
            return "Runner";
        }

        @Override
        public String getDescription() {
            return "2000 oder mehr";
        }

        @Override
        public Texture getTexture() {
            return Textures.TEST;
        }

    }

}
