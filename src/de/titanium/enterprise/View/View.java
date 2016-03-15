package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;
import de.titanium.enterprise.View.Menu.MenuView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yonas on 08.03.2016.
 */
public abstract class View extends JPanel implements GameComponent {

    private MenuView viewMenu = null;

    public View(MenuView viewMenu) {
        this.viewMenu = viewMenu;

        this.setMaximumSize(new Dimension(1280, 600));
        this.setMinimumSize(new Dimension(1280, 600));
        this.setPreferredSize(new Dimension(1280, 600));
        Enterprise.getGame().addComponent(this);
    }

    public MenuView getMenuView() {
        return this.viewMenu;
    }

    @Override
    public boolean isActive() {
        return (Enterprise.getGame().getViewManager().getCurrent() == this);
    }

}
