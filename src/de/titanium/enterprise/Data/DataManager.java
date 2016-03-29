package de.titanium.enterprise.Data;

import java.util.HashMap;

/**
 * Created by Yonas on 16.03.2016.
 */
public class DataManager {

    private HashMap<String, Object> dataEntries = new HashMap<>();

    public DataManager() {}

    /**
     * Diese Methode f�gt dem DataManager einen neuen Eintrag hinzu, dabei funktioniert der Name als eine Art "Pfad" bzw.
     * Key, um sp�ter das Objekt abzurufen.
     * @param name
     * @param dataEntry
     * @param <T>
     */
    public <T> void set(String name, final T dataEntry) {

        // @Improve: Anstelle eines Strings sollte man eher Enums benutzen die einen Pfad angeben. Sollte unn�tige
        // Fehler vermeiden.
        this.dataEntries.put(name, dataEntry);

    }

    /**
     * Diese Methode gibt den passenden Eintrag zur�ck.
     * @param name
     * @param <T>
     * @return
     */
    public <T> T get(String name) {

        return (T) this.dataEntries.get(name);

    }
    /**
     * Diese Methode pr�ft, ob ein bestimmer Pfad vorhanden ist.
     * @param name
     * @return
     */
    public boolean contains(String name) {

        return (this.dataEntries.containsKey(name));

    }

}
