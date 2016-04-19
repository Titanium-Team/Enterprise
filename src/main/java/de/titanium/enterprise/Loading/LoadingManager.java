package de.titanium.enterprise.Loading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadingManager {

    private List<Loadable> loadables = new ArrayList<>();
    private Loadable current = null;
    private boolean isDone = false;

    private int currentIndex = 1;

    public LoadingManager() {}

    /**
     * Diese Methode fuegt ein Loadable der Liste hinzu.
     * @param loadable
     */
    public void add(Loadable loadable) {
        this.loadables.add(loadable);
    }

    /**
     * Diese Methode fuegt ein Array an Loadables der Liste hinzu.
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
            this.currentIndex++;
        }
        this.isDone = true;
        this.loadables.clear();
        this.current = null;
    }

    /**
     * Gibt das aktuelle Objekt zurueck, dass geladen wird. Falls keins aktuell geladen wird, wird null zurueckgegeben.
     * @return
     */
    public Loadable getCurrent() {
        return this.current;
    }

    /**
     * Gibt true zurück, wenn der LoadingManager fertig mit laden ist, ansonsten false.
     * @return
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gibt den "Index" des aktuellen Elements zurück.
     * @return
     */
    public int getCurrentIndex() {
        return this.currentIndex;
    }

    /**
     * Gibt die gesammte Größe der Liste zurück.
     * @return
     */
    public int getSize() {
        return this.loadables.size();
    }

}
