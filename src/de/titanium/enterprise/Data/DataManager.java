package de.titanium.enterprise.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yonas on 16.03.2016.
 */
public class DataManager {

    private HashMap<Class<? extends DataEntry>, List<DataEntry>> dataEntries = new HashMap<>();

    public DataManager() {}

    public void add(final DataEntry dataEntry) {

        if(this.dataEntries.containsKey(dataEntry.getClass())) {
            this.dataEntries.get(dataEntry.getClass()).add(dataEntry);
        } else {
            this.dataEntries.put(dataEntry.getClass(), new ArrayList<DataEntry>() {{

                this.add(dataEntry);

            }});
        }

    }

    public <T extends DataEntry> T getOne(Class<T> clazz) {

        if(this.dataEntries.containsKey(clazz) && this.dataEntries.get(clazz).size() > 0) {
            return (T) this.dataEntries.get(clazz).get(0);
        }

        return null;

    }

    public <T extends DataEntry> List<T> get(Class<T> clazz) {

        if(this.dataEntries.containsKey(clazz)) {
            return (List<T>) this.dataEntries.get(clazz);
        }

        return null;

    }

    public <T extends DataEntry> boolean contains(Class<T> clazz) {

        return (this.dataEntries.containsKey(clazz) && !(this.dataEntries.get(clazz).isEmpty()));

    }

    public HashMap<Class<? extends DataEntry>, List<DataEntry>> getDataEntries() {
        return dataEntries;
    }
}
