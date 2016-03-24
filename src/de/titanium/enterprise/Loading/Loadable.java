package de.titanium.enterprise.Loading;

/**
 * Created by Yonas on 17.03.2016.
 */
public interface Loadable {

    /**
     * Gibt den Namen des Objektes zurück das aktuell geladen wird.
     * @return
     */
    String getName();

    /**
     * Diese Methode wird aufgerufen, wenn das Objekt geladen werden soll.
     */
    void load();

}
