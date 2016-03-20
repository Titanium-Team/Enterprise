package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yonas on 08.03.2016.
 */
public abstract class View extends JPanel implements GameComponent {

    private MenuView viewMenu = null;

    public View(MenuView viewMenu) {
        this.viewMenu = viewMenu;

        this.setMaximumSize(new Dimension(1280, 540));
        this.setMinimumSize(new Dimension(1280, 540));
        this.setPreferredSize(new Dimension(1280, 540));

        Enterprise.getGame().addComponent(this);
    }

    public synchronized MenuView getMenuView() {
        return this.viewMenu;
    }

    @Override
    public boolean isActive() {
        return (Enterprise.getGame().getViewManager().getCurrent() == this);
    }

    public synchronized void changeMenu(MenuView menuView) {
        this.viewMenu = menuView;
    }
}
