package de.titanium.enterprise.Data.DataContainer;

import de.titanium.enterprise.Enterprise;

import java.util.ArrayList;
import java.util.List;

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

        Enterprise.getGame().getDatabase().store();
    }

    /**
     * Gibt alle DataContainer zurück.
     * @return
     */
    public DataContainer[] values() {
        return this.dataContainers.toArray(new DataContainer[this.dataContainers.size()]);
    }

}
