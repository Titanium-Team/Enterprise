package de.titanium.enterprise.Sprite;

import de.titanium.enterprise.Enterprise;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public enum Textures implements Texture {

    BACKGROUND {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Background Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/background.png");
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    DEFENSEGAME_BACKGROUND {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Defense-Game Background Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/defensegame-background.jpg");
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    DEFAULT_BACKGROUND {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Default Background Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/default-background.jpg");
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    BORDER_UP {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Border-UP Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/border.png").getSubimage(1, 1, 1280, 543);
        }


        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    BORDER_DOWN {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Border-DOWN Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/border.png").getSubimage(1, 546, 1280, 181);
        }


        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    FAILED_BUTTON {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Failed-Button Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/failed-button.png").getSubimage(1, 1, 148, 163);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    CHECKED_BUTTON {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Checked-Button Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/checked-button.png").getSubimage(1, 1, 148, 162);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_0 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-0 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1, 1, 58, 68);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_1 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-1 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(61, 1, 41, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_2 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-2 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(104, 1, 58, 69);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_3 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-3 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(164, 1, 58, 69);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_4 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-2 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(224, 1, 59, 68);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_5 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-5 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(285, 1, 56, 69);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_6 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-6 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(343, 1, 58, 68);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_7 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-7 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(403, 1, 53, 69);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_8 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-8 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(458, 1, 58, 69);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_9 {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-9 Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(518, 1, 55, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_A {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-A Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(575, 1, 60, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_B {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-B Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(637, 1, 52, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_C {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-C Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(691, 1, 55, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_D {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-D Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(748, 1, 57, 65);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_E {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-E Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(807, 1, 49, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_F {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-F Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(858, 1, 45, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_G {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-G Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(905, 1, 65, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_H {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-H Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(979, 1, 56, 65);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_I {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-I Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1037, 1, 32, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_J {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-J Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1071, 1, 48, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_K {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-K Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1121, 1, 55, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_L {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-L Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1178, 1, 51, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_M {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-M Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1231, 1, 76, 68);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_N {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-N Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1309, 1, 57, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_O {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-O Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1368, 1, 58, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_P {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-P Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1428, 1, 51, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_Q {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Q Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1481, 1, 69, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_R {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-R Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1552, 1, 61, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_S {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-S Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1615, 1, 53, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_T {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-T Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1670, 1, 59, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_U {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-U Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1731, 1, 60, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_V {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-V Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1793, 1, 61, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_W {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-W Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1856, 1, 77, 66);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_X {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-X Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1935, 1, 62, 68);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_Y {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Y Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(1999, 1, 64, 65);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_Z {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Z Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(2065, 1, 60, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_EXCLAMATION_MARK {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Exclamation-Mark Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(2127, 1, 27, 67);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_PLUS {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Plus Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(2156, 1, 42, 44);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_COMMA {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Comma Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(2200, 1, 25, 33);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_MINUS {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Minus Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(2227, 1, 43, 20);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_DOT {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Dot Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(2272, 1, 24, 24);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ALPHABET_QUESTION_MARK {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Alphabet-Question-Mark Texture";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/alphabet.png").getSubimage(2298, 1, 56, 68);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_AMULET {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Amulet";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(1, 1, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_ARROW {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Arrow";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(45, 1, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_AXE {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Axe";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(89, 1, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_CAMPFIRE {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Campfire";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(133, 1, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_COIN {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Coin";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(177, 1, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_EXIT {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Exit";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(1, 46, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_FLAG {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Flag";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(45, 46, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_GAME_MENU {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Game Menu";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(89, 46, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_HAMMER {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Hammer";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(133, 46, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_HEALING {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Healing";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(177, 46, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_LEVEL_UP {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Level Up";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(1, 91, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_LOCKER {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Locker";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(45, 91, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_MAGIC_HAT {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Magic Hat";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(89, 91, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_MAGIC_STONE {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Magic Stone";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(133, 91, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_MEAT {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Meat";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(177, 91, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_PAUSE {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Pause";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(1, 136, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_PLAY{

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Play";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(45, 136, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_POTION {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Potion";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(89, 136, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_REPLAY {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Replay";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(133, 136, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_RING {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Ring";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(177, 136, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_SCROLL {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Scroll";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(1, 181, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_SELL {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Sell";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(45, 181, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_SWORD {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Sword";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(89, 181, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_WINNER {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Winner";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(133, 181, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    },
    ACHIEVEMENT_ICON_WOODS {

        private BufferedImage image;

        @Override
        public String getName() {
            return "Achievement Icon - Woods";
        }

        @Override
        public void load() {
            this.image = Textures.loadImage("/assets/achievements.png").getSubimage(177, 181, 42, 43);
        }

        @Override
        public BufferedImage getImage() {
            return this.image;
        }

    };

    private static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Enterprise.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
