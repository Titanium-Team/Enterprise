package de.titanium.enterprise.Loading;

public interface Loadable {

    /**
     * Gibt den Namen des Objektes zurueck das aktuell geladen wird.
     * @return
     */
    String getName();

    /**
     * Diese Methode wird aufgerufen, wenn das Objekt geladen werden soll.
     */
    void load();

}
