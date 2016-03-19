package de.titanium.enterprise.Loading;

import de.titanium.enterprise.Data.DataEntry;

/**
 * Created by Yonas on 17.03.2016.
 */
public interface Loadable extends DataEntry {

    String getName();

    void load();

}
