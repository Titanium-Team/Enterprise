package de.titanium.enterprise.View.GameMenu.SettingsView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class SettingsView extends View {

    private int selectedOption = 0;
    private final Map<String, Setting> options = new LinkedHashMap<>();
    private final Map<String, Integer> selectedValue = new LinkedHashMap<>();

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
        for(Map.Entry<String, Setting> entry : this.options.entrySet()) {

            //draw option
            if(x == this.selectedOption) {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 15);
                g.drawImage(image, (this.getWidth() / 2 - 300) - image.getWidth(null) / 2, 50 + x * 35, null);

                //draw selected value
                Image valueImage = Enterprise.getGame().getTextBuilder().toImage(entry.getValue().getOptions()[this.selectedValue.get(entry.getKey()).intValue()], 15);
                g.drawImage(valueImage, 600, 50 + x * 35, null);

                //draw description
                int i = 0;
                for(String value : entry.getValue().getDescription()[this.selectedValue.get(entry.getKey())]) {
                    g.drawImage(Enterprise.getGame().getTextBuilder().toImage(value, 7), 990, 80 + i * 20, null);
                    i++;
                }
            } else {
                Image image = Enterprise.getGame().getTextBuilder().toImage(entry.getKey(), 10);
                g.drawImage(image, (this.getWidth() / 2 - 300) - image.getWidth(null) / 2, 50 + x * 35, null);

                Image valueImage = Enterprise.getGame().getTextBuilder().toImage(entry.getValue().getOptions()[this.selectedValue.get(entry.getKey()).intValue()], 10);
                g.drawImage(valueImage, 600, 50 + x * 35, null);

            }

            x++;
        }

        //Draw description headline
        g.drawImage(Enterprise.getGame().getTextBuilder().toImage("Beschreibung", 10), 1010, 50, null);

        //ESC zum Speichern
        g.drawImage(Enterprise.getGame().getTextBuilder().toImage("ESC druecken um zu speichern", 10), 233, 380, null);

        // Hier werden die Achievements gezeichnet, falls welche freigeschaltet wurden.
        Enterprise.getGame().getAchievementManager().handle(g);

    }


    @Override
    public void update(int tick) {

        if(tick % 4 == 0) {

            //Speichern
            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) {
                for(Map.Entry<String, Setting> entry : this.options.entrySet()) {
                    entry.getValue().change(
                            entry.getValue().getOptions()[this.selectedValue.get(entry.getKey()).intValue()]
                    );
                }
                Enterprise.getGame().getViewManager().switchView(GameMenuView.class);
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_S)) { ////Im Menu nach oben oder unten bewegen
                this.selectedOption++;

                if(this.selectedOption >= this.options.size()) {
                    this.selectedOption = 0;
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_W)) {
                this.selectedOption--;

                if(this.selectedOption < 0) {
                    this.selectedOption = this.options.size() - 1;
                }
            }

            //Im Menu einen neuen Wert fuer eine Einstellung auswaehlen
            String[] keys = this.options.keySet().toArray(new String[this.options.size()]);
            int current = this.selectedValue.get(keys[this.selectedOption]).intValue();
            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_D)) {
                this.selectedValue.put(keys[this.selectedOption], current+1);

                if((current + 1) >= this.options.get(keys[this.selectedOption]).getOptions().length) {
                    this.selectedValue.put(keys[this.selectedOption], 0);
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_A)) {
                this.selectedValue.put(keys[this.selectedOption], current-1);

                if((current - 1) < 0) {
                    this.selectedValue.put(keys[this.selectedOption], this.options.get(keys[this.selectedOption]).getOptions().length-1);
                }
            }
        }

    }

    @Override
    public void render() {
        this.repaint();
    }

    //Alle Optionen
    {

        {
            //Antialiasing
            Setting<String> setting = new Setting<String>("Antialiasing", new String[]{"On", "Default", "Off"}) {

                @Override
                public String[][] getDescription() {
                    String[][] description = new String[][]{
                            {"Rendering is done", "with antialiasing"},
                            {"Rendering is done", "with a default", "antialiasing mode", "chosen by the", "implementation"},
                            {"Rendering is done", "without antialiasing"}
                    };

                    return description;
                }

                @Override
                public int getDefaultSelected() {

                    Object value = Enterprise.getGame().getRenderingHints().get(RenderingHints.KEY_ANTIALIASING);
                    if (value == RenderingHints.VALUE_ANTIALIAS_ON) {
                        return 0;
                    }
                    if (value == RenderingHints.VALUE_ANTIALIAS_DEFAULT) {
                        return 1;
                    }

                    return 2;
                }

                @Override
                public void change(String input) {

                    switch (input) {

                        case "On":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            break;

                        case "Default":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);
                            break;

                        case "Off":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                            break;

                    }

                }

            };
            this.options.put(setting.getName(), setting);
            this.selectedValue.put(setting.getName(), setting.getDefaultSelected());
        }

        {
            //Dithering
            Setting<String> setting = new Setting<String>("Dithering", new String[]{"Enable", "Default", "Disable"}) {

                @Override
                public String[][] getDescription() {
                    String[][] description = new String[][]{
                            {"dither when rendering", "geometry, if needed."},
                            {"use a default for", "dithering chosen by", "the implementation."},
                            {"do not dither when", "rendering geometry."}
                    };

                    return description;
                }

                @Override
                public int getDefaultSelected() {

                    Object value = Enterprise.getGame().getRenderingHints().get(RenderingHints.KEY_DITHERING);
                    if (value == RenderingHints.VALUE_DITHER_ENABLE) {
                        return 0;
                    }
                    if (value == RenderingHints.VALUE_DITHER_DEFAULT) {
                        return 1;
                    }

                    return 2;
                }

                @Override
                public void change(String input) {

                    switch (input) {

                        case "Enable":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
                            break;

                        case "Default":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DEFAULT);
                            break;

                        case "Disable":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
                            break;

                    }

                }

            };
            this.options.put(setting.getName(), setting);
            this.selectedValue.put(setting.getName(), setting.getDefaultSelected());
        }

        {
            //Rendering
            Setting<String> setting = new Setting<String>("Rendering", new String[]{"Quality", "Default", "Speed"}) {

                @Override
                public String[][] getDescription() {
                    String[][] description = new String[][]{
                            {"rendering algorithms", "are chosen with a", "preference for", "output quality."},
                            {"rendering algorithms", "are chosen by the", "implementation for", "for a good tradeoff", "of performance vs.", "quality."},
                            {"rendering algorithms", "are chosen with a", "preference for", "output speed."}
                    };

                    return description;
                }

                @Override
                public int getDefaultSelected() {

                    Object value = Enterprise.getGame().getRenderingHints().get(RenderingHints.KEY_RENDERING);
                    if (value == RenderingHints.VALUE_RENDER_QUALITY) {
                        return 0;
                    }
                    if (value == RenderingHints.VALUE_RENDER_DEFAULT) {
                        return 1;
                    }

                    return 2;
                }

                @Override
                public void change(String input) {

                    switch (input) {

                        case "Quality":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                            break;

                        case "Default":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
                            break;

                        case "Speed":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
                            break;

                    }

                }

            };
            this.options.put(setting.getName(), setting);
            this.selectedValue.put(setting.getName(), setting.getDefaultSelected());
        }

        {
            //Fractional Metrics
            Setting<String> setting = new Setting<String>("Fractional Metrics", new String[]{"On", "Default", "Off"}) {

                @Override
                public String[][] getDescription() {
                    String[][] description = new String[][]{
                            {"character glyphs are", "positioned with", "sub-pixel accuracy."},
                            {"character glyphs are", "positioned with", "accuracy chosen by", "the implementation."},
                            {"character glyphs are", "positioned with", "advance widths", "rounded to pixel", "boundaries."}
                    };

                    return description;
                }

                @Override
                public int getDefaultSelected() {

                    Object value = Enterprise.getGame().getRenderingHints().get(RenderingHints.KEY_FRACTIONALMETRICS);
                    if (value == RenderingHints.VALUE_FRACTIONALMETRICS_ON) {
                        return 0;
                    }
                    if (value == RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT) {
                        return 1;
                    }

                    return 2;
                }

                @Override
                public void change(String input) {

                    switch (input) {

                        case "On":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                            break;

                        case "Default":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT);
                            break;

                        case "Off":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
                            break;

                    }

                }

            };
            this.options.put(setting.getName(), setting);
            this.selectedValue.put(setting.getName(), setting.getDefaultSelected());
        }

        {
            //Stroke Control
            Setting<String> setting = new Setting<String>("Stroke Control", new String[]{"Normalize", "Default", "Pure"}) {

                @Override
                public String[][] getDescription() {
                    String[][] description = new String[][]{
                            {"geometry should be", "normalized to", "improve uniformity", "or spacing of lines", "and overall", "aesthetics."},
                            {"geometry may be", "modified or left", "pure depending on", "the tradeoffs in", "a given", "implementation."},
                            {"geometry should be", "left unmodified and", "rendered with", "sub-pixel accuracy."}
                    };

                    return description;
                }

                @Override
                public int getDefaultSelected() {

                    Object value = Enterprise.getGame().getRenderingHints().get(RenderingHints.KEY_STROKE_CONTROL);
                    if (value == RenderingHints.VALUE_STROKE_NORMALIZE) {
                        return 0;
                    }
                    if (value == RenderingHints.VALUE_STROKE_DEFAULT) {
                        return 1;
                    }

                    return 2;
                }

                @Override
                public void change(String input) {

                    switch (input) {

                        case "Normalize":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
                            break;

                        case "Default":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
                            break;

                        case "Pure":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
                            break;

                    }

                }

            };
            this.options.put(setting.getName(), setting);
            this.selectedValue.put(setting.getName(), setting.getDefaultSelected());
        }

        {
            //Stroke Control
            Setting<String> setting = new Setting<String>("Interpolation", new String[]{"Bicubic", "Bilinear", "Neighbor"}) {

                @Override
                public String[][] getDescription() {
                    String[][] description = new String[][]{
                            {"the color sample of", "9 nearby integer", "coordinate sample ins", "the image are", "interpolated using a", "a cubic function in", "both X and Y to", "produce a color", "sample."},
                            {"the color sample of", "the 4 nearest", "neighboring integer", "coordinate sample in", "the image are", "interpolated linearly", "to produce a color", "sample."},
                            {"the color sample of", "the nearest", "neighboring", "integer coordinate", "sample in the", "image is used."},
                    };

                    return description;
                }

                @Override
                public int getDefaultSelected() {

                    Object value = Enterprise.getGame().getRenderingHints().get(RenderingHints.KEY_INTERPOLATION);
                    if (value == RenderingHints.VALUE_INTERPOLATION_BICUBIC) {
                        return 0;
                    }
                    if (value == RenderingHints.VALUE_INTERPOLATION_BILINEAR) {
                        return 1;
                    }

                    return 2;
                }

                @Override
                public void change(String input) {

                    switch (input) {

                        case "Bicubic":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                            break;

                        case "Bilinear":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                            break;

                        case "Neighbor":
                            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                            break;

                    }

                }

            };
            this.options.put(setting.getName(), setting);
            this.selectedValue.put(setting.getName(), setting.getDefaultSelected());
        }

        {
            //Monitor
            final GraphicsDevice[] graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            String[] monitors = new String[graphicsDevices.length];
            for(int i = 0; i < graphicsDevices.length; i++) {
                monitors[i] = graphicsDevices[i].getIDstring();
            }

            Setting<String> setting = new Setting<String>("Monitor", monitors) {

                @Override
                public String[][] getDescription() {

                    String[][] description = new String[graphicsDevices.length][1];
                    for(int i = 0; i < graphicsDevices.length; i++) {
                        description[i] = new String[] { graphicsDevices[i].getIDstring() };
                    }

                    return description;
                }

                @Override
                public int getDefaultSelected() {

                    for(int i = 0; i < graphicsDevices.length; i++) {
                        if(Enterprise.getGame().getGameView().getFrame().getGraphicsConfiguration().getDevice() == graphicsDevices[i]) {
                            return i;
                        }
                    }

                    return 0;
                }

                @Override
                public void change(String input) {

                    String[][] descriptions = this.getDescription();

                    for(int i = 0; i < descriptions.length; i++) {
                        if(descriptions[i][0].equals(input)) {
                            Rectangle bounds = graphicsDevices[i].getDefaultConfiguration().getBounds();
                            JFrame frame = Enterprise.getGame().getGameView().getFrame();
                            Enterprise.getGame().getGameView().getFrame().setLocation((int) (bounds.getX() - ((frame.getWidth() - bounds.getWidth()) / 2)), (int) (bounds.getY() - ((frame.getHeight() - bounds.getHeight()) / 2)));
                        }
                    }

                }

            };
            this.options.put(setting.getName(), setting);
            this.selectedValue.put(setting.getName(), setting.getDefaultSelected());
        }

        {

            String[] volumeOptions = new String[101];
            final String[][] volumeOptionsDesc = new String[volumeOptions.length][];

            for(int i = 0; i < volumeOptions.length; i++) volumeOptions[i] = String.valueOf(i);
            for(int i = 0; i < volumeOptionsDesc.length; i++) volumeOptionsDesc[i] = new String[] { ("Volume: " + String.valueOf(i)) };

            // Music Volume
            Setting<String> setting = new Setting<String>("Music", volumeOptions) {

                @Override
                public String[][] getDescription() {
                    return volumeOptionsDesc;
                }

                @Override
                public int getDefaultSelected() {
                    return (int) (Enterprise.getGame().getSoundPlayer().getVolume());
                }

                @Override
                public void change(String input) {

                    Enterprise.getGame().getSoundPlayer().updateVolume(Double.valueOf(input));

                }

            };
            this.options.put(setting.getName(), setting);
            this.selectedValue.put(setting.getName(), setting.getDefaultSelected());
        }

    }

}
