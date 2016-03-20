package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;

import java.util.HashMap;

/**
 * Created by Yonas on 08.03.2016.
 */
public class ViewManager {

    private HashMap<Class<? extends View>, View> views = new HashMap<>();
    private View current = null;

    public ViewManager() {}

    public boolean register(View view) {
        this.views.put(view.getClass(), view);
        return true;
    }

    public View getCurrent() {
        return this.current;
    }

    /**
     * Diese Methode wechselt die im Spiel angezeigte View. Dies beinhaltet die View sowie sein Menu.
     * @param view
     * @return
     */
    public boolean switchTo(Class<? extends View> view) {

        if(this.views.containsKey(view)) {

            Enterprise.getGame().getGameView().setCurrentView(this.views.get(view));
            this.current = this.views.get(view);

            return true;
        }

        return false;

    }

    public synchronized boolean changeMenu(Class<? extends View> view, MenuView menuView) {

        if(this.views.containsKey(view)) {
            this.views.get(view).changeMenu(menuView);
            Enterprise.getGame().getGameView().swapMenu(this.views.get(view));
            return true;
        }

        return false;
    }


}
