package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.GameComponent;

import javax.swing.*;
import java.awt.*;

public abstract class MenuView extends JPanel implements GameComponent {

    public MenuView() {
        this.setMaximumSize(new Dimension(1280, 180));
        this.setMinimumSize(new Dimension(1280, 180));
        this.setPreferredSize(new Dimension(1280, 180));
        Enterprise.getGame().addComponent(this);
    }

    @Override
    public boolean isActive() {
        return Enterprise.getGame().getViewManager().getCurrent().getMenuView() == this;
    }

}
