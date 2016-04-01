package de.titanium.enterprise.View.GameMenu;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.FightView.FightView;
import de.titanium.enterprise.View.HeroesView.HeroesView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.SettingsView.SettingsView;
import de.titanium.enterprise.View.StoryView.StoryView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        this.options.put("Heroes", HeroesView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Erhalte eine");
            this.add("Uebersicht ueber");
            this.add("alle Helden die das");
            this.add("Spiel zu bieten hat.");

        }});

        this.options.put("Story", StoryView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Erfahre was wirklich");
            this.add("hinter diesem Spiel");
            this.add("steht und erlebe eine");
            this.add("spannende und lustige");
            this.add("Geschichte");

        }});

        this.options.put("Settings", SettingsView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Veraendere einige");
            this.add("Einstellungen, um");
            this.add("nochmehr Spass am");
            this.add("Spielen zu haben");

        }});

        this.options.put("Exit", null);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Oh... ok... schade. Es");
            this.add("hat echt viel Spass");
            this.add("gemacht mit dir zu");
            this.add("spielen.");
            this.add("");
            this.add("Bis bald.");

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
                g.drawImage(image, 480 - image.getWidth(null) / 2, 150 + x * 35, null);

                // @Cleanup: Es waere besser, wenn der Code selbststendig die Zeilenumbrueche einbaut. Dies sollte entweder hier
                // oder allgemein in der TextBuilder#toImage Methode geloest werden. Dazu kann man dann z.B. eine maximale
                // Breite angeben.
                int i = 0;
                for(String value : this.descriptions.get(this.selectedOption)) {
                    g.drawImage(Enterprise.getGame().getTextBuilder().toImage(value, 7), 990, 80 + i * 20, null);
                    i++;
                }
            } else {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 10);
                g.drawImage(image, 480 - image.getWidth(null) / 2, 150 + x * 35, null);
            }

            x++;
        }

        // Hier werden die Achievements gezeichnet, falls welche freigeschaltet wurden.
        Enterprise.getGame().getAchievementManager().handle(g);

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

                Class goTo = this.options.values().toArray(new Class[this.options.size()])[this.selectedOption];

                //Wenn die Class nicht null ist handelt es sich um eine normale View und es kann gewechselt werden.
                if(!(goTo == null)) {
                    Enterprise.getGame().getViewManager().switchTo(goTo);
                } else {
                    //Falls goTo null ist handelt es sich um den "Exit"-Button
                    Enterprise.getGame().getDataContainers().store();
                    Enterprise.getGame().getDatabase().store();
                    System.exit(0);
                }
            }
        }

    }

    @Override
    public void render() {
        this.repaint();
    }

}
