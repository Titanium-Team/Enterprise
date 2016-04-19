package de.titanium.enterprise.View;

import javax.swing.*;
import java.awt.*;

public class GameView {

    private JFrame frame = new JFrame();
    private JPanel content = new JPanel();
    private JPanel menu = new JPanel();

    public GameView() {

        this.frame = new JFrame();
        this.frame.setTitle("Enterprise - The GameUtils");
        this.frame.setUndecorated(true);
        this.frame.setPreferredSize(new Dimension(1280, 720));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.content, BorderLayout.NORTH);
        this.frame.add(this.menu, BorderLayout.SOUTH);
        
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

    }

    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Ersetzt die aktuelle View. Der alte Container wird geleert und mit neuem Inhalt gefuellt.
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

    public void swapMenu(View view) {

        this.frame.remove(this.menu);

        this.menu = view.getMenuView();
        this.frame.add(this.menu, BorderLayout.SOUTH);

        this.frame.validate();
        this.frame.repaint();

    }



}
