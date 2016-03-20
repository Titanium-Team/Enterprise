package de.titanium.enterprise;

import de.titanium.enterprise.Sprite.Texture;
import de.titanium.enterprise.Sprite.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Yonas on 20.03.2016.
 */
public class TextBuilder {

    public TextBuilder() {}

    /**
     * Diese Methode verwandelt den übergebenen String in ein Image das diesen Text enthält.
     * @param value Der Text der umgewandelt werden soll.
     * @param font Die Größe des Textes.
     * @return
     */
    public Image toImage(String value, int font) {

        double size = (font / 30D);
        int width = 0;
        int height = 0;

        for(char c : value.toCharArray()) {

            Texture t = this.byChar(c);

            if(t == null) {
                width += 40;
            } else {
                height = Math.max(height, t.getImage().getHeight());
                width += t.getImage().getWidth();
            }

        }

        width *= size;
        height *= size;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        int x = 0;
        for(char c : value.toCharArray()) {

            Texture texture = this.byChar(c);

            if(texture == null) {
                x += 10; //Leerzeichen
            } else {
                Image i = texture.getImage();
                g.drawImage(i, x, 0, (int) (i.getWidth(null) * size), (int) (i.getHeight(null) * size), null);
                x += i.getWidth(null) * size;
            }

        }

        //free memory
        g.dispose();

        return image;

    }

    public Texture byChar(char c) {
        c = Character.toUpperCase(c);
        switch (c) {

            case '0': return Textures.ALPHABET_0;
            case '1': return Textures.ALPHABET_1;
            case '2': return Textures.ALPHABET_2;
            case '3': return Textures.ALPHABET_3;
            case '4': return Textures.ALPHABET_4;
            case '5': return Textures.ALPHABET_5;
            case '6': return Textures.ALPHABET_6;
            case '7': return Textures.ALPHABET_7;
            case '8': return Textures.ALPHABET_8;
            case '9': return Textures.ALPHABET_9;
            case 'A': return Textures.ALPHABET_A;
            case 'B': return Textures.ALPHABET_B;
            case 'C': return Textures.ALPHABET_C;
            case 'D': return Textures.ALPHABET_D;
            case 'E': return Textures.ALPHABET_E;
            case 'F': return Textures.ALPHABET_F;
            case 'G': return Textures.ALPHABET_G;
            case 'H': return Textures.ALPHABET_H;
            case 'I': return Textures.ALPHABET_I;
            case 'J': return Textures.ALPHABET_J;
            case 'K': return Textures.ALPHABET_K;
            case 'L': return Textures.ALPHABET_L;
            case 'M': return Textures.ALPHABET_M;
            case 'N': return Textures.ALPHABET_N;
            case 'O': return Textures.ALPHABET_O;
            case 'P': return Textures.ALPHABET_P;
            case 'Q': return Textures.ALPHABET_Q;
            case 'R': return Textures.ALPHABET_R;
            case 'S': return Textures.ALPHABET_S;
            case 'T': return Textures.ALPHABET_T;
            case 'U': return Textures.ALPHABET_U;
            case 'V': return Textures.ALPHABET_V;
            case 'W': return Textures.ALPHABET_W;
            case 'X': return Textures.ALPHABET_X;
            case 'Y': return Textures.ALPHABET_Y;
            case 'Z': return Textures.ALPHABET_Z;

        }

        return null;

    }

}
