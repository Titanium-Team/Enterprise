package de.titanium.enterprise.Data;

import java.util.HashMap;

public class DataManager {

    private HashMap<String, Object> dataEntries = new HashMap<>();

    public DataManager() {}

    /**
     * Diese Methode fuegt dem DataManager einen neuen Eintrag hinzu, dabei funktioniert der Name als eine Art "Pfad" bzw.
     * Key, um spaeter das Objekt abzurufen.
     * @param name
     * @param dataEntry
     * @param <T>
     */
    public <T> void set(String name, final T dataEntry) {

        // @Improve: Anstelle eines Strings sollte man eher Enums benutzen die einen Pfad angeben. Sollte unnuetige
        // Fehler vermeiden.
        this.dataEntries.put(name, dataEntry);

    }

    /**
     * Diese Methode gibt den passenden Eintrag zurueck.
     * @param name
     * @param <T>
     * @return
     */
    public <T> T get(String name) {

        return (T) this.dataEntries.get(name);

    }
    /**
     * Diese Methode prueft, ob ein bestimmer Pfad vorhanden ist.
     * @param name
     * @return
     */
    public boolean contains(String name) {

        return (this.dataEntries.containsKey(name));

    }

    @Override
    public String toString() {
        return String.format("dataEntries: %s", this.dataEntries);
    }

}
