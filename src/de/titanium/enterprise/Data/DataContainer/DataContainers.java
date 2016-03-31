package de.titanium.enterprise.Data.DataContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yonas on 31.03.2016.
 */
public class DataContainers {

    private List<DataContainer> dataContainers = new ArrayList<>();

    public DataContainers() {}

    public void add(DataContainer dataContainer) {
        this.dataContainers.add(dataContainer);
    }

    /**
     * Diese Methode ruft von allen DataContainers die DataContainer#store Methode auf.
     */
    public void store() {
        for(DataContainer dataContainer : this.dataContainers) {
            dataContainer.store();
        }
    }

    /**
     * Gibt alle DataContainer zurück.
     * @return
     */
    public DataContainer[] values() {
        return this.dataContainers.toArray(new DataContainer[this.dataContainers.size()]);
    }

}
