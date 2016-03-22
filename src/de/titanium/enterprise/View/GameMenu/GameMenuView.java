package de.titanium.enterprise.View.GameMenu;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.FightView.FightView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.SettingsView.SettingsView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Yonas on 21.03.2016.
 */
public class GameMenuView extends View {

    private int selectedOption = 0;
    private final Map<String, Class<? extends View>> options = new LinkedHashMap<>();
    private final List<List<String>> descriptions = new ArrayList<>();

    {

        this.options.put("Fight", FightView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Erlebe gefaehrliche");
            this.add("Abenteuer mit deinen");
            this.add("lieblings Helden.");
            this.add("");
            this.add("Dabei wirst du dich");
            this.add("schweren Gegnern");
            this.add("stellen muessen.");
            this.add("Die keine Ruecksicht");
            this.add("nehmen werden.");

        }});

        this.options.put("Skills", null);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Verwnde deine Punkte");
            this.add("um einen Vorteil");
            this.add("fuer deine Helden");
            this.add("im Kampf zu erzielen");

        }});

        this.options.put("Heroes", null);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Erhalte eine Uebersicht");
            this.add("ueber alle Helden die");
            this.add("das Spiel zu bieten hat");

        }});

        this.options.put("Settings", SettingsView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Veraendere einige");
            this.add("Einstellungen, um noch");
            this.add("mehr Spaﬂ am Spielen");
            this.add("zu haben");

        }});

    }

    public GameMenuView(MenuView viewMenu) {
        super(viewMenu);
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //draw background and frame
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        //draw menu
        int x = 0;
        for(Map.Entry<String, Class<? extends View>> entry : this.options.entrySet()) {

            if(x == this.selectedOption) {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 15);
                g.drawImage(Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 15), (this.getWidth() / 2 - 150) - image.getWidth(null) / 2, 50 + x * 35, null);

                int i = 0;
                for(String value : this.descriptions.get(this.selectedOption)) {
                    g.drawImage(Enterprise.getGame().getTextBuilder().toImage(value, 7), 990, 80 + i * 20, null);
                    i++;
                }
            } else {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 10);
                g.drawImage(image, (this.getWidth() / 2 - 150) - image.getWidth(null) / 2, 50 + x * 35, null);
            }

            x++;
        }
    }


    @Override
    public void update(int tick) {

        if(tick % 4 == 0) {
            if (Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_DOWN)) {
                this.selectedOption++;

                if(this.selectedOption >= this.options.size()) {
                    this.selectedOption = 0;
                }
            }
            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_UP)) {
                this.selectedOption--;

                if(this.selectedOption < 0) {
                    this.selectedOption = this.options.size() - 1;
                }
            }
            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ENTER)) {
                Enterprise.getGame().getViewManager().switchTo(
                        this.options.values().toArray(new Class[this.options.size()])[this.selectedOption]
                );
            }
        }

    }

    @Override
    public void render() {
        this.repaint();
    }

}
