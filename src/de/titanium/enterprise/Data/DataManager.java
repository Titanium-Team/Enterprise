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

    public <T> void add(String name, final T dataEntry) {

        if(this.dataEntries.containsKey(name)) {
            this.dataEntries.get(name).add(dataEntry);
        } else {
            this.dataEntries.put(name, new ArrayList<T>() {{

                this.add(dataEntry);

            }});
        }

    }

    public <T> T getOne(String name, Class<T> type) {

        if(this.dataEntries.containsKey(name) && this.dataEntries.get(name).size() > 0) {
            return (T) this.dataEntries.get(name).get(0);
        }

        return null;

    }

    public <T> List<T> get(String name) {

        if(this.dataEntries.containsKey(name)) {
            return (List<T>) this.dataEntries.get(name);
        }

        return null;

    }

    public <T> boolean contains(String name) {

        return (this.dataEntries.containsKey(name) && !(this.dataEntries.get(name).isEmpty()));

    }

}
