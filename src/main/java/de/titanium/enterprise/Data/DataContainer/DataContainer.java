package de.titanium.enterprise.Data.DataContainer;

import de.titanium.enterprise.Loading.Loadable;

public interface DataContainer extends Loadable {

    /**
     * Diese Methode speichert die Daten auf der Festplatte.
     */
    void store();

}
