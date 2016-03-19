package de.titanium.enterprise.Loading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yonas on 17.03.2016.
 */
public class LoadingManager {

    private List<Loadable> loadables = new ArrayList<>();
    private Loadable current = null;

    public LoadingManager() {}

    public void add(Loadable loadable) {
        this.loadables.add(loadable);
    }

    public void add(Loadable[] loadables) {
        this.loadables.addAll(Arrays.asList(loadables));
    }

    public void load() {
        for(Loadable loadable : this.loadables) {
            this.current = loadable;
            loadable.load();
        }
        this.loadables.clear();
        this.current = null;
    }

    public Loadable getCurrent() {
        return this.current;
    }
}
