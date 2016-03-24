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

    /**
     * Diese Methode fügt ein Loadable der Liste hinzu.
     * @param loadable
     */
    public void add(Loadable loadable) {
        this.loadables.add(loadable);
    }

    /**
     * Diese Methode fügt ein Array an Loadables der Liste hinzu.
     * @param loadables
     */
    public void add(Loadable[] loadables) {
        this.loadables.addAll(Arrays.asList(loadables));
    }

    /**
     * Diese Methode ruft alle Loadable#load Funktionen auf, die sich aktuell in der Liste befinden.
     */
    public void load() {
        for(Loadable loadable : this.loadables) {
            this.current = loadable;
            loadable.load();
        }
        this.loadables.clear();
        this.current = null;
    }

    /**
     * Gibt das aktuelle Objekt zurück, dass geladen wird. Falls keins aktuell geladen wird, wird null zurückgegeben.
     * @return
     */
    public Loadable getCurrent() {
        return this.current;
    }
}
