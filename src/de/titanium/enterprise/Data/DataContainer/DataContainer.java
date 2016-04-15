package de.titanium.enterprise.Data.DataContainer;

import de.titanium.enterprise.Loading.Loadable;

/**
 * Created by Yonas on 31.03.2016.
 */
public interface DataContainer extends Loadable {

    /**
     * Diese Methode speichert die Daten auf der Festplatte.
     */
    void store();

}
