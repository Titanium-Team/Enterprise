package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;

import java.util.HashMap;

/**
 * Created by Yonas on 08.03.2016.
 */
public class ViewManager {

    private HashMap<Class<? extends View>, View> views = new HashMap<>();
    private Enterprise enterprise;
    private View current = null;

    public ViewManager(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public boolean register(View view) {
        this.enterprise.addComponent(view);
        this.views.put(view.getClass(), view);
        return true;
    }

    public View getCurrent() {
        return this.current;
    }

    public boolean switchTo(Class<? extends View> view) {

        if(this.views.containsKey(view)) {

            this.enterprise.getGameView().setCurrentView(this.views.get(view));
            this.current = this.views.get(view);

            return true;
        }

        return false;

    }


}
