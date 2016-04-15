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
            return Textures.ACHIEVEMENT_ICON_HEALING;
        }

    },
    DAMAGE_5 {

        // Wird freigeschaltet, wenn der Spieler 5+ Damage auf einmal macht.

        @Override
        public String getName() {
            return "kleiner Mann";
        }

        @Override
        public String getDescription() {
            return "5+ Schaden";
        }

        @Override
        public Texture getTexture() {
            return Textures.ACHIEVEMENT_ICON_SWORD;
        }

    },
    DAMAGE_10 {

        // Wird freigeschaltet, wenn der Spieler 10+ Damage auf einmal macht.

        @Override
        public String getName() {
            return "Aua.";
        }

        @Override
        public String getDescription() {
            return "10+ Schaden";
        }

        @Override
        public Texture getTexture() {
            return Textures.ACHIEVEMENT_ICON_SWORD;
        }

    },
    DAMAGE_20 {

        // Wird freigeschaltet, wenn der Spieler 20+ Damage auf einmal macht.

        @Override
        public String getName() {
            return "Ich dachte wir sind Freunde.";
        }

        @Override
        public String getDescription() {
            return "20+ Schaden";
        }

        @Override
        public Texture getTexture() {
            return Textures.ACHIEVEMENT_ICON_SWORD;
        }

    },
    DEFENSESCORE_1000 {

        // Wird freigeschaltet, wenn der Spieler einen Defense-Score von 1000 oder mehr erreicht.

        @Override
        public String getName() {
            return "Baby";
        }

        @Override
        public String getDescription() {
            return "1000 oder mehr";
        }

        @Override
        public Texture getTexture() {
            return Textures.ACHIEVEMENT_ICON_WINNER;
        }

    },
    DEFENSESCORE_197 {

        // Wird freigeschaltet, wenn der Spieler direkt gegen die Rampe "faehrt".

        @Override
        public String getName() {
            return "197.";
        }

        @Override
        public String getDescription() {
            return "197.";
        }

        @Override
        public Texture getTexture() {
            return Textures.ACHIEVEMENT_ICON_WINNER;
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
            return Textures.ACHIEVEMENT_ICON_WINNER;
        }

    },
    DEFENSESCORE_5000 {

        // Wird freigeschaltet, wenn der Spieler einen Defense-Score von 5000 oder mehr erreicht.

        @Override
        public String getName() {
            return "Formel 1";
        }

        @Override
        public String getDescription() {
            return "5000 oder mehr";
        }

        @Override
        public Texture getTexture() {
            return Textures.ACHIEVEMENT_ICON_WINNER;
        }

    };

    public static Achievement byName(String name) {

        for(Achievement entry : Achievements.values()) {
            if(entry.getName().equals(name)) {
                return entry;
            }
        }

        return null;

    }
}
