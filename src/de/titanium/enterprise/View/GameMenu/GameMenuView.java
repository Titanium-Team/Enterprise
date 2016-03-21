package de.titanium.enterprise.View.GameMenu;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.FightView.FightView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yonas on 21.03.2016.
 */
public class GameMenuView extends View {

    private int selectedOption = 0;
    private final Map<String, Class<? extends View>> options = new HashMap<>();
    private final List<List<String>> descriptions = new ArrayList<>();

    {

        this.options.put("Fight", FightView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Erlebe gefaehrliche");
            this.add("Abenteuer mit deinen");
            this.add("lieblings Helden.");
            this.add("");
            this.add("Dabei wirst du");
            this.add("dich schweren");
            this.add("Gegnern stellen");
            this.add("muessen. Die keine");
            this.add("Ruecksicht nehmen");
            this.add("werden.");

        }});

        this.options.put("Fight!", FightView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Erlebe gefaehrliche");
            this.add("Abenteuer mit deinen");
            this.add("lieblings Helden.");
            this.add("");
            this.add("Dabei wirst du");
            this.add("dich schweren");
            this.add("Gegnern stellen");
            this.add("muessen. Die keine");
            this.add("Ruecksicht nehmen");
            this.add("werden3.");

        }});

        this.options.put("Fight!!", FightView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Erlebe gefaehrliche");
            this.add("Abenteuer mit deinen");
            this.add("lieblings Helden.");
            this.add("");
            this.add("Dabei wirst du");
            this.add("dich schweren");
            this.add("Gegnern stellen");
            this.add("muessen. Die keine");
            this.add("Ruecksicht nehmen");
            this.add("werden2.");

        }});

        this.options.put("Fight!!!", FightView.class);
        this.descriptions.add(new ArrayList<String>() {{

            this.add("Erlebe gefaehrliche");
            this.add("Abenteuer mit deinen");
            this.add("lieblings Helden.");
            this.add("");
            this.add("Dabei wirst du");
            this.add("dich schweren");
            this.add("Gegnern stellen");
            this.add("muessen. Die keine");
            this.add("Ruecksicht nehmen");
            this.add("werden1.");

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
                g.drawImage(Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 15), this.getWidth() / 2, 50 + x * 35, null);

                int i = 0;
                for(String value : this.descriptions.get(this.selectedOption)) {
                    g.drawImage(Enterprise.getGame().getTextBuilder().toImage(value, 7), 990, 80 + i * 20, null);
                    i++;
                }
            } else {
                g.drawImage(Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 10), this.getWidth() / 2, 50 + x * 35, null);
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
