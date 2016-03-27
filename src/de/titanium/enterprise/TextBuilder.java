package de.titanium.enterprise;

import de.titanium.enterprise.Sprite.Texture;
import de.titanium.enterprise.Sprite.Textures;

import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yonas on 20.03.2016.
 */
public class TextBuilder {

    private final Map<String, Image> cache = new HashMap<>();
    private final Map<String, Long> expires = new HashMap<>();

    public TextBuilder() {}

    /**
     * Diese Methode verwandelt den �bergebenen String in ein Image das diesen Text enth�lt.
     * @param value Der Text der umgewandelt werden soll.
     * @param font Die Gr��e des Textes.
     * @return
     */
    public Image toImage(String value, int font) {


        // Dies ist der einhaltliche Cache-Key der aus dem Value an sich (also dem Text) und der Font-Gr��e besteht.
        String cacheKey = String.format("%s->%d", value, font);

        // In diesem Code Teil wird nun gepr�ft, ob es aktuell Werte gibt, die sich im Cache befinden, deren Zeit
        // "abgelaufen" ist und deshalb, falls sie nochmal abgefragt werden sollten, neu gerendert werden m�ssen.
        // Dies sollte immer wieder ein wenig RAM freischaufeln.
        for(Map.Entry<String, Long> entry : this.expires.entrySet()) {
            if(entry.getValue() <= System.currentTimeMillis()) {
                this.cache.remove(entry.getKey());
            }
        }

        // Falls sich der Value aktuell im Cache befindet, dann k�nnen wird einfach diesen Wert aus dem Cache zur�ck geben
        // und sparen uns das komplette erzeugen.
        // Hier muss nicht mehr gepr�ft werden, ob das Image bereits abgelaufen ist, da das ja bereits weiter oben
        // gemacht wird.
        if(this.cache.containsKey(cacheKey)) {
            return this.cache.get(cacheKey);
        }

        // @Improve: Man muss auf Rundungsfehlerpr�fen, da es sonst dazu kommt das einige Buchstaben manchmal
        // abgeschnitten sind, wird etwas weiter unten zwar versucht mit Konstanten auszugleichen funktioniert aber
        // noch nicht wirklich 100%ig, k�nnte besser sein.
        double size = (font / 30D);
        int width = 0;
        int height = 0;

        // @Imrpove: Die Breite des Leerzeichen sollte auch von der allgemeinen gew�hlten Schriftgr��e abh�ngig
        // gemacht werden, wirkt sonst eventuell etwas klein manchmal. Sollte aber erst gemacht werden, wenn man
        // auch einen maximalen Wert angeben kann f�r die Breite des Bildes, da es sonst dazu f�hren kann das
        // nochmal alle Texte angepasst werden m�ssen. :( Falls der Wert h�her ist als 10px.
        int space = 10;

        for(char c : value.toCharArray()) {

            Texture t = this.byChar(c);

            if(t == null) {
                width += 40;
            } else {
                height = Math.max(height, t.getImage().getHeight());
                width += t.getImage().getWidth();
            }

        }

        width = (int) (width * size + 5);
        height = (int) (height * size + 1);

        // Nun wird das neue Bild erstellt.
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        // Die RenderingHints werden gesetzt, die aktuell vom Programm genutzt werden..
        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        // Ab hier beginnt das eigentliche rendern der
        int x = 0;

        for (char c : value.toCharArray()) {

            Texture texture = this.byChar(c);

            if (texture == null) {
                x += space; //Leerzeichen
            } else {
                Image i = texture.getImage();

                int letterHeight = (int) (i.getHeight(null) * size);
                int letterWidth = (int) (i.getWidth(null) * size);
                int y = (height - letterHeight);

                g.drawImage(i, x, y, letterWidth, letterHeight, null);
                x += letterWidth;

            }

        }

        g.dispose();

        // Sobald alles fertig gerendert wurde, wird das Image plus passenden Key in den Cache gepackt und fertig.
        this.cache.put(cacheKey, image);
        this.expires.put(cacheKey, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(120));

        return image;

    }

    /**
     * Diese Methode gibt die passende Textur abh�ngig vom Character zur�ck, sollte der Character unbekannt sein
     * gibt die Methode null zur�ck.
     *
     * @param c
     * @return
     */
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
            case '!': return Textures.ALPHABET_EXCLAMATION_MARK;
            case '+': return Textures.ALPHABET_PLUS;
            case ',': return Textures.ALPHABET_COMMA;
            case '-': return Textures.ALPHABET_MINUS;
            case '.': return Textures.ALPHABET_DOT;
            case '?': return Textures.ALPHABET_QUESTION_MARK;

        }

        return null;

    }

}
