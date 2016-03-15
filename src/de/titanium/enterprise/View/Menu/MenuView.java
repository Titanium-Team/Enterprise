package de.titanium.enterprise.View.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Yonas on 08.03.2016.
 */
public abstract class MenuView extends JPanel {

    public MenuView() {
        this.setMaximumSize(new Dimension(1280, 180));
        this.setMinimumSize(new Dimension(1280, 180));
        this.setPreferredSize(new Dimension(1280, 180));
    }

    public void addButton(JButton menuButton, ActionListener actionListener) {
        this.add(menuButton);
        menuButton.addActionListener(actionListener);
    }
}
