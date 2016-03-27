package de.titanium.enterprise.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yonas on 16.03.2016.
 */
public class DataManager {

    private HashMap<String, List> dataEntries = new HashMap<>();

    public DataManager() {}

    /**
     * Diese Methode fügt dem DataManager einen neuen Eintrag hinzu, dabei funktioniert der Name als eine Art "Pfad" bzw.
     * Key, um später das Objekt abzurufen.
     * @param name
     * @param dataEntry
     * @param <T>
     */
    public <T> void add(String name, final T dataEntry) {

        // @Improve: Anstelle eines Strings sollte man eher Enums benutzen die einen Pfad angeben. Sollte unnötige
        // Fehler vermeiden.
        if(this.dataEntries.containsKey(name)) {
            this.dataEntries.get(name).add(dataEntry);
        } else {
            this.dataEntries.put(name, new ArrayList<T>() {{

                this.add(dataEntry);

            }});
        }

    }

    /**
     * Diese Methode gibt nur den ersten aller Einträge zurück.
     * @param name
     * @param <T>
     * @return
     */
    public <T> T getOne(String name) {

        if(this.dataEntries.containsKey(name) && this.dataEntries.get(name).size() > 0) {
            return (T) this.dataEntries.get(name).get(0);
        }

        return null;

    }

    /**
     * Diese Methode gibt eine Liste aller Einträge zurück.
     * @param name
     * @param <T>
     * @return
     */
    public <T> List<T> get(String name) {

        if(this.dataEntries.containsKey(name)) {
            return (List<T>) this.dataEntries.get(name);
        }

        return null;

    }

    /**
     * Diese Methode prüft, ob ein bestimmer Pfad vorhanden ist.
     * @param name
     * @return
     */
    public boolean contains(String name) {

        return (this.dataEntries.containsKey(name) && !(this.dataEntries.get(name).isEmpty()));

    }

}
