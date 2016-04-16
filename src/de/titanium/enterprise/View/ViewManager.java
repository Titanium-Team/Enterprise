package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;

import java.util.HashMap;

public class ViewManager {

    private HashMap<Class<? extends View>, View> views = new HashMap<>();
    private View current = null;

    public ViewManager() {}

    /**
     * Diese Methode registriert eine neue View im ViewManager.
     * @param view
     * @return
     */
    public boolean register(View view) {
        this.views.put(view.getClass(), view);
        return true;
    }

    /**
     * Gibt die aktuell zu zeichnende View zurueck.
     * @return
     */
    public View getCurrent() {
        return this.current;
    }

    /**
     * Diese Methode wechselt die im Spiel angezeigte View. Dies beinhaltet die View sowie sein Menu.
     * @param view
     * @return
     */
    public boolean switchView(Class<? extends View> view) {

        if(this.views.containsKey(view)) {

            Enterprise.getGame().getGameView().setCurrentView(this.views.get(view));
            this.current = this.views.get(view);

            return true;
        }

        return false;

    }

    /**
     * Diese Methode aendert von einer View das Menu.
     * @param view
     * @param menuView
     * @return
     */
    public boolean switchMenu(Class<? extends View> view, MenuView menuView) {

        if(this.views.containsKey(view)) {
            this.views.get(view).changeMenu(menuView);
            Enterprise.getGame().getGameView().swapMenu(this.views.get(view));
            return true;
        }

        return false;
    }


}
