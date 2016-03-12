package de.titanium.enterprise.KeyCombo;

/**
 * Created by Yonas on 09.03.2016.
 */
public class ComboResult {

    private final int keys;

    public ComboResult(int keys) {
        this.keys = keys;
    }

    /**
     * Gibt die Anzahl zur�ck die der Spieler an Tasten gedr�ckt hat.
     * @return
     */
    public int getKeys() {
        return this.keys;
    }

}
