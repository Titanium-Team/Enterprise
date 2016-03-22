package de.titanium.enterprise.View.SettingsView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yonas on 22.03.2016.
 */
public class SettingsView extends View {

    private int selectedOption = 0;
    private final Map<String, Map<String, Object>> options = new LinkedHashMap<>();
    private final Map<String, Integer> selectedValue = new LinkedHashMap<>();
    private final Map<String, List<List<String>>> description = new LinkedHashMap<>();

    public SettingsView(MenuView viewMenu) {
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

        //draw settings
        int x = 0;
        for(Map.Entry<String, Map<String, Object>> entry : this.options.entrySet()) {

            String[] valueKeys = entry.getValue().keySet().toArray(new String[entry.getValue().keySet().size()]);

            //draw option
            if(x == this.selectedOption) {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 15);
                g.drawImage(image, (this.getWidth() / 2 - 300) - image.getWidth(null) / 2, 50 + x * 35, null);

                //draw selected value
                Image valueImage = Enterprise.getGame().getTextBuilder().toImage(valueKeys[this.selectedValue.get(entry.getKey())], 15);
                g.drawImage(valueImage, 600, 50 + x * 35, null);

                //draw description
                int i = 0;
                for(String value : this.description.get(entry.getKey()).get(this.selectedValue.get(entry.getKey()))) {
                    g.drawImage(Enterprise.getGame().getTextBuilder().toImage(value, 7), 990, 80 + i * 20, null);
                    i++;
                }
            } else {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 10);
                g.drawImage(image, (this.getWidth() / 2 - 300) - image.getWidth(null) / 2, 50 + x * 35, null);

                Image valueImage = Enterprise.getGame().getTextBuilder().toImage(valueKeys[this.selectedValue.get(entry.getKey())], 10);
                g.drawImage(valueImage, 600, 50 + x * 35, null);

            }

            x++;
        }

        //ESC zum Speichern
        g.drawImage(Enterprise.getGame().getTextBuilder().toImage("ESC druecken um zu speichern", 10), 233, 280, null);

    }

    /**
     * Gibt den RenderingHints.Key zur�ck, abh�ngig von dem �bergebenen Namen.
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
     * Diese Methode gibt den aktuellen Wert zur�ck, der aktuell auch im Programm verwendet wird.
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
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_DOWN)) { ////Im Menu nach oben oder unten bewegen
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

            //Im Menu einen neuen Wert f�r eine Einstellung ausw�hlen
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

    {

        //Antialiasing
        this.options.put("Antialiasing", new LinkedHashMap<String, Object>() {{

            this.put("On", RenderingHints.VALUE_ANTIALIAS_ON);
            this.put("Default", RenderingHints.VALUE_ANTIALIAS_DEFAULT);
            this.put("Off", RenderingHints.VALUE_ANTIALIAS_OFF);

        }});
        this.selectedValue.put("Antialiasing", this.selectedByKey("Antialiasing"));
        this.description.put("Antialiasing", new ArrayList<List<String>>() {{

            this.add(new ArrayList<String>() {{

                this.add("Rendering is done");
                this.add("with antialiasing");

            }});
            this.add(new ArrayList<String>() {{

                this.add("rendering is done");
                this.add("with a default");
                this.add("antialiasing mode");
                this.add("chosen by the");
                this.add("implementation.");

            }});
            this.add(new ArrayList<String>() {{

                this.add("Rendering is done");
                this.add("without antialiasing");

            }});

        }});

        //Text-Antialiasing
        this.options.put("Text-Antialiasing", new LinkedHashMap<String, Object>() {{

            this.put("On", RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            this.put("Default", RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
            this.put("Gasp", RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

        }});
        this.selectedValue.put("Text-Antialiasing", this.selectedByKey("Text-Antialiasing"));
        this.description.put("Text-Antialiasing", new ArrayList<List<String>>() {{

            this.add(new ArrayList<String>() {{

                this.add("text rendering is");
                this.add("done with some");
                this.add("form of antialiasing");

            }});
            this.add(new ArrayList<String>() {{

                this.add("text rendering is");
                this.add("done with a default");
                this.add("antialiasing mode");
                this.add("chosen by the");
                this.add("implementation.");

            }});
            this.add(new ArrayList<String>() {{

                this.add("text rendering is");
                this.add("is requested to use");
                this.add("information in the");
                this.add("font resource which");
                this.add("specifies for each");
                this.add("point size");

            }});

        }});


        //Dithering
        this.options.put("Dithering", new LinkedHashMap<String, Object>() {{

            this.put("Enable", RenderingHints.VALUE_DITHER_ENABLE);
            this.put("Default", RenderingHints.VALUE_DITHER_DEFAULT);
            this.put("Disable", RenderingHints.VALUE_DITHER_DISABLE);

        }});
        this.selectedValue.put("Dithering", this.selectedByKey("Dithering"));
        this.description.put("Dithering", new ArrayList<List<String>>() {{

            this.add(new ArrayList<String>() {{

                this.add("dither when rendering");
                this.add("geometry, if needed.");

            }});
            this.add(new ArrayList<String>() {{

                this.add("use a default for");
                this.add("dithering chosen by");
                this.add("the implementation");

            }});
            this.add(new ArrayList<String>() {{

                this.add("do not dither when");
                this.add("rendering geometry.");

            }});

        }});

        //Rendering
        this.options.put("Rendering", new LinkedHashMap<String, Object>() {{

            this.put("Quality", RenderingHints.VALUE_RENDER_QUALITY);
            this.put("Default", RenderingHints.VALUE_RENDER_DEFAULT);
            this.put("Speed", RenderingHints.VALUE_RENDER_SPEED);

        }});
        this.selectedValue.put("Rendering", this.selectedByKey("Rendering"));
        this.description.put("Rendering", new ArrayList<List<String>>() {{

            this.add(new ArrayList<String>() {{

                this.add("rendering algorithms");
                this.add("are chosen with a");
                this.add("preference for");
                this.add("output quality");

            }});
            this.add(new ArrayList<String>() {{

                this.add("rendering algorithms");
                this.add("are chosen by the");
                this.add("implementation for");
                this.add("for a good tradeoff");
                this.add("of performance vs.");
                this.add("quality");

            }});
            this.add(new ArrayList<String>() {{

                this.add("rendering algorithms");
                this.add("are chosen with a");
                this.add("preference for");
                this.add("output speed");

            }});

        }});

        //Fractional Metrics
        this.options.put("Fractional Metrics", new LinkedHashMap<String, Object>() {{

            this.put("On", RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            this.put("Default", RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
            this.put("Off", RenderingHints.VALUE_FRACTIONALMETRICS_OFF);

        }});
        this.selectedValue.put("Fractional Metrics", this.selectedByKey("Fractional Metrics"));
        this.description.put("Fractional Metrics", new ArrayList<List<String>>() {{

            this.add(new ArrayList<String>() {{

                this.add("character glyphs are");
                this.add("positioned with");
                this.add("sub-pixel accuracy");

            }});
            this.add(new ArrayList<String>() {{

                this.add("character glyphs are");
                this.add("positioned with");
                this.add("accuracy chosen by");
                this.add("the implementation");

            }});
            this.add(new ArrayList<String>() {{

                this.add("character glyphs are");
                this.add("positioned with");
                this.add("advance widths");
                this.add("rounded to pixel");
                this.add("boundaries.");

            }});

        }});


        //Stroke Control
        this.options.put("Stroke Control", new LinkedHashMap<String, Object>() {{

            this.put("Normalize", RenderingHints.VALUE_STROKE_NORMALIZE);
            this.put("Default", RenderingHints.VALUE_STROKE_DEFAULT);
            this.put("Pure", RenderingHints.VALUE_STROKE_PURE);

        }});
        this.selectedValue.put("Stroke Control", this.selectedByKey("Stroke Control"));
        this.description.put("Stroke Control", new ArrayList<List<String>>() {{

            this.add(new ArrayList<String>() {{

                this.add("geometry should be");
                this.add("normalized to");
                this.add("improve uniformity");
                this.add("or spacing of lines");
                this.add("and overall");
                this.add("aesthetics.");

            }});
            this.add(new ArrayList<String>() {{

                this.add("geometry may be");
                this.add("modified or left");
                this.add("pure depending on");
                this.add("the tradeoffs in");
                this.add("a given");
                this.add("implementation.");

            }});
            this.add(new ArrayList<String>() {{

                this.add("geometry should be");
                this.add("left unmodified and");
                this.add("rendered with");
                this.add("sub-pixel accuracy.");

            }});

        }});

    }

}
