package de.titanium.enterprise.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yonas on 08.03.2016.
 */
public class GameView {

    private JFrame frame = new JFrame();
    private JPanel content = new JPanel();
    private JPanel menu = new JPanel();

    public GameView() {

        this.frame = new JFrame();
        this.frame.setTitle("Enterprise - The Game");
        this.frame.setPreferredSize(new Dimension(1080, 720));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.content, BorderLayout.NORTH);
        this.frame.add(this.menu, BorderLayout.SOUTH);

        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

    }

    /**
     * Ersetzt die aktuelle View. Der alte Container wird geleert und mit neuem Inhalt gefüllt.
     * @param currentView
     */
    public void setCurrentView(View currentView){

        this.frame.remove(this.content);
        this.frame.remove(this.menu);

        this.content = currentView;
        this.menu = currentView.getMenuView();
        this.frame.add(this.content, BorderLayout.NORTH);
        this.frame.add(this.menu, BorderLayout.SOUTH);

        this.frame.validate();
        this.frame.repaint();

    }



}
