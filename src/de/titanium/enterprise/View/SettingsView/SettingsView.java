package de.titanium.enterprise.View.SettingsView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Yonas on 22.03.2016.
 */
public class SettingsView extends View {

    private int selectedOption = 0;
    private final Map<String, Map<String, Object>> options = new LinkedHashMap<>();
    private final Map<String, Integer> selectedValue = new LinkedHashMap<>();

    public SettingsView(MenuView viewMenu) {
        super(viewMenu);
    }

    {

        this.options.put("Antialiasing", new LinkedHashMap<String, Object>() {{

            this.put("On", RenderingHints.VALUE_ANTIALIAS_ON);
            this.put("Default", RenderingHints.VALUE_ANTIALIAS_DEFAULT);
            this.put("Off", RenderingHints.VALUE_ANTIALIAS_OFF);

        }});
        this.selectedValue.put("Antialiasing", this.selectedByKey("Antialiasing"));

        this.options.put("Text-Antialiasing", new LinkedHashMap<String, Object>() {{

            this.put("On", RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            this.put("Default", RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
            this.put("Gasp", RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            this.put("LCD HBGR", RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);
            this.put("LCD HRGB", RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            this.put("LCD VBGR", RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR);
            this.put("LCD VRGB", RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);

        }});
        this.selectedValue.put("Text-Antialiasing", this.selectedByKey("Text-Antialiasing"));

        this.options.put("Dithering", new LinkedHashMap<String, Object>() {{

           this.put("Enable", RenderingHints.VALUE_DITHER_ENABLE);
           this.put("Default", RenderingHints.VALUE_DITHER_DEFAULT);
           this.put("Disable", RenderingHints.VALUE_DITHER_DISABLE);

        }});
        this.selectedValue.put("Dithering", this.selectedByKey("Dithering"));

        this.options.put("Rendering", new LinkedHashMap<String, Object>() {{

            this.put("Quality", RenderingHints.VALUE_RENDER_QUALITY);
            this.put("Default", RenderingHints.VALUE_RENDER_DEFAULT);
            this.put("Speed", RenderingHints.VALUE_RENDER_SPEED);

        }});
        this.selectedValue.put("Rendering", this.selectedByKey("Rendering"));

        this.options.put("Fractional Metrics", new LinkedHashMap<String, Object>() {{

            this.put("On", RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            this.put("Default", RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
            this.put("Off", RenderingHints.VALUE_FRACTIONALMETRICS_OFF);

        }});
        this.selectedValue.put("Fractional Metrics", this.selectedByKey("Fractional Metrics"));

        this.options.put("Stroke Control", new LinkedHashMap<String, Object>() {{

            this.put("Normalize", RenderingHints.VALUE_STROKE_NORMALIZE);
            this.put("Default", RenderingHints.VALUE_STROKE_DEFAULT);
            this.put("Pure", RenderingHints.VALUE_STROKE_PURE);

        }});
        this.selectedValue.put("Stroke Control", this.selectedByKey("Stroke Control"));
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //draw background and frame
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        //draw settings
        int x = 0;
        for(Map.Entry<String, Map<String, Object>> entry : this.options.entrySet()) {

            String[] valueKeys = entry.getValue().keySet().toArray(new String[entry.getValue().keySet().size()]);

            //draw option
            if(x == this.selectedOption) {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 15);
                g.drawImage(image, (this.getWidth() / 2 - 300) - image.getWidth(null) / 2, 50 + x * 35, null);

                //draw selected value
                Image valueImage = Enterprise.getGame().getTextBuilder().toImage(valueKeys[this.selectedValue.get(entry.getKey())], 10);
                g.drawImage(valueImage, 600, 50 + x * 35, null);

            } else {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 10);
                g.drawImage(image, (this.getWidth() / 2 - 300) - image.getWidth(null) / 2, 50 + x * 35, null);

                Image valueImage = Enterprise.getGame().getTextBuilder().toImage(valueKeys[this.selectedValue.get(entry.getKey())], 10);
                g.drawImage(valueImage, 600, 50 + x * 35, null);

            }

            x++;
        }
    }

    /**
     * Gibt den RenderingHints.Key zurück, abhängig von dem übergebenen Namen.
     * @param name
     * @return
     */
    private RenderingHints.Key keyByName(String name) {

        RenderingHints.Key key = null;
        if(name.equalsIgnoreCase("Antialiasing")) {
            key = RenderingHints.KEY_ANTIALIASING;
        } else if(name.equalsIgnoreCase("Text-Antialiasing")) {
            key = RenderingHints.KEY_TEXT_ANTIALIASING;
        } else if(name.equals("Dithering")) {
            key = RenderingHints.KEY_DITHERING;
        } else if(name.equals("Rendering")) {
            key = RenderingHints.KEY_RENDERING;
        } else if(name.equals("Fractional Metrics")) {
            key = RenderingHints.KEY_FRACTIONALMETRICS;
        } else if(name.equals("Stroke Control")) {
            key = RenderingHints.KEY_STROKE_CONTROL;
        }

        return key;

    }

    /**
     * Diese Methode gibt den aktuellen Wert zurück, der aktuell auch im Programm verwendet wird.
     * @param name
     * @return
     */
    private int selectedByKey(String name) {

        RenderingHints.Key key = this.keyByName(name);
        int selected = 0;
        Object search = Enterprise.getGame().getRenderingHints().get(key);
        Object[] values = this.options.get(name).values().toArray(new Object[this.options.get(name).values().size()]);
        for(int i = 0; i < values.length; i++) {
            if(search == values[i]) {
                selected = i;
            }
        }

        return selected;

    }

    @Override
    public void update(int tick) {

        if(tick % 4 == 0) {

            //Speichern
            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) {
                for(Map.Entry<String, Map<String, Object>> entry : this.options.entrySet()) {
                    Enterprise.getGame().getRenderingHints().put(
                        this.keyByName(entry.getKey()),
                        entry.getValue().values().toArray(new Object[entry.getValue().values().size()])[this.selectedValue.get(entry.getKey())]
                    );
                }
                Enterprise.getGame().getViewManager().switchTo(GameMenuView.class);
            }

            //Im Menu nach oben oder unten bewegen
            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_DOWN)) {
                this.selectedOption++;

                if(this.selectedOption >= this.options.size()) {
                    this.selectedOption = 0;
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_UP)) {
                this.selectedOption--;

                if(this.selectedOption < 0) {
                    this.selectedOption = this.options.size() - 1;
                }
            }

            //Im Menu einen neuen Wert für eine Einstellung auswählen
            String[] keys = this.options.keySet().toArray(new String[this.options.size()]);
            int current = this.selectedValue.get(keys[this.selectedOption]).intValue();
            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_RIGHT)) {
                this.selectedValue.put(keys[this.selectedOption], current+1);

                if((current + 1) >= this.options.get(keys[this.selectedOption]).keySet().size()) {
                    this.selectedValue.put(keys[this.selectedOption], 0);
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_LEFT)) {
                this.selectedValue.put(keys[this.selectedOption], current-1);

                if((current - 1) < 0) {
                    this.selectedValue.put(keys[this.selectedOption], this.options.get(keys[this.selectedOption]).keySet().size()-1);
                }
            }
        }

    }

    @Override
    public void render() {
        this.repaint();
    }

}
