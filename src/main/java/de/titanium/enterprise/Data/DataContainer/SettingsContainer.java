package de.titanium.enterprise.Data.DataContainer;

import de.SweetCode.SweetDB.DataSet.DataSet;
import de.SweetCode.SweetDB.DataType.DataTypes;
import de.SweetCode.SweetDB.Optional;
import de.SweetCode.SweetDB.Table.Syntax.SyntaxRuleBuilder;
import de.SweetCode.SweetDB.Table.Table;
import de.titanium.enterprise.Enterprise;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SettingsContainer implements DataContainer {

    @Override
    public String getName() {
        return "Settings - DataContainer";
    }

    @Override
    public void store() {

        Table table = Enterprise.getGame().getDatabase().table("settings").get();
        table.all().clear();

        // Hier werden alle RenderhingHints gespeichert
        for (Map.Entry<RenderingHints.Key, Object> entry : Enterprise.getGame().getRenderingHints().entrySet()) {

            table.insert()
                    .add("name", entry.getKey().toString())
                    .add("value", entry.getValue().toString())
                    .build();

        }

        // Hier wird der ausgewaehlte Monitor gespeichert.
        table.insert()
                .add("name", "display")
                .add("value", Enterprise.getGame().getGameView().getFrame().getGraphicsConfiguration().getDevice().getIDstring())
                .build();

    }

    @Override
    public void load() {

        Optional<Table> tableOptional = Enterprise.getGame().getDatabase().table("settings");

        if (tableOptional.isPresent()) {

            // Die Tabelle existiert, die Daten koennen geladen werden
            Table table = tableOptional.get();

            for (DataSet dataSet : table.all()) {

                String name = dataSet.get("name").get().as(DataTypes.STRING);
                String value = dataSet.get("value").get().as(DataTypes.STRING);

                switch (name.toLowerCase()) {

                    case "global rendering quality key": {
                        Object renderingValue = null;
                        switch (value.toLowerCase()) {
                            case "highest quality rendering methods":
                                renderingValue = RenderingHints.VALUE_RENDER_QUALITY;
                                break;
                            case "default rendering methods":
                                renderingValue = RenderingHints.VALUE_RENDER_DEFAULT;
                                break;
                            case "fastest rendering methods":
                                renderingValue = RenderingHints.VALUE_RENDER_SPEED;
                                break;
                            default:
                                throw new IllegalArgumentException(String.format("%s is an invalid RenderingValue.", String.valueOf(renderingValue)));
                        }
                        Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_RENDERING, renderingValue);
                    }
                    break;

                    case "global antialiasing enable key": {
                        Object renderingValue = null;
                        switch (value.toLowerCase()) {
                            case "default antialiasing rendering mode":
                                renderingValue = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
                                break;
                            case "nonantialiased rendering mode":
                                renderingValue = RenderingHints.VALUE_ANTIALIAS_OFF;
                                break;
                            case "antialiased rendering mode":
                                renderingValue = RenderingHints.VALUE_ANTIALIAS_ON;
                                break;
                            default:
                                throw new IllegalArgumentException(String.format("%s is an invalid RenderingValue.", String.valueOf(renderingValue)));
                        }
                        Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, renderingValue);
                    }
                    break;

                    case "dithering quality key": {
                        Object renderingValue = null;
                        switch (value.toLowerCase()) {
                            case "default dithering mode":
                                renderingValue = RenderingHints.VALUE_DITHER_DEFAULT;
                                break;
                            case "nondithered rendering mode":
                                renderingValue = RenderingHints.VALUE_DITHER_DISABLE;
                                break;
                            case "dithered rendering mode":
                                renderingValue = RenderingHints.VALUE_DITHER_ENABLE;
                                break;
                            default:
                                throw new IllegalArgumentException(String.format("%s is an invalid RenderingValue.", String.valueOf(renderingValue)));
                        }
                        Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_DITHERING, renderingValue);
                    }
                    break;

                    case "stroke normalization control key": {
                        Object renderingValue = null;
                        switch (value.toLowerCase()) {
                            case "default stroke normalization":
                                renderingValue = RenderingHints.VALUE_STROKE_DEFAULT;
                                break;
                            case "normalize strokes for consistent rendering":
                                renderingValue = RenderingHints.VALUE_STROKE_NORMALIZE;
                                break;
                            case "pure stroke conversion for accurate paths":
                                renderingValue = RenderingHints.VALUE_STROKE_PURE;
                                break;
                            default:
                                throw new IllegalArgumentException(String.format("%s is an invalid RenderingValue.", String.valueOf(renderingValue)));
                        }
                        Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_STROKE_CONTROL, renderingValue);
                    }
                    break;

                    case "image interpolation method key": {
                        Object renderingValue = null;
                        switch (value.toLowerCase()) {
                            case "bicubic image interpolation mode":
                                renderingValue = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
                                break;
                            case "bilinear image interpolation mode":
                                renderingValue = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
                                break;
                            case "nearest neighbor image interpolation mode":
                                renderingValue = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
                                break;
                            default:
                                throw new IllegalArgumentException(String.format("%s is an invalid RenderingValue.", String.valueOf(renderingValue)));
                        }
                        Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_INTERPOLATION, renderingValue);
                    }
                    break;

                    case "fractional metrics enable key": {
                        Object renderingValue = null;
                        switch (value.toLowerCase()) {
                            case "default fractional text metrics mode":
                                renderingValue = RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT;
                                break;
                            case "fractional text metrics mode":
                                renderingValue = RenderingHints.VALUE_FRACTIONALMETRICS_ON;
                                break;
                            case "integer text metrics mode":
                                renderingValue = RenderingHints.VALUE_FRACTIONALMETRICS_OFF;
                                break;
                            default:
                                throw new IllegalArgumentException(String.format("%s is an invalid RenderingValue.", String.valueOf(renderingValue)));
                        }
                        Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_FRACTIONALMETRICS, renderingValue);
                    }
                    break;

                    case "display": {

                        final GraphicsDevice[] graphicsDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
                        for (int i = 0; i < graphicsDevices.length; i++) {
                            if(graphicsDevices[i].getIDstring().equalsIgnoreCase(value)) {
                                Rectangle bounds = graphicsDevices[i].getDefaultConfiguration().getBounds();
                                JFrame frame = Enterprise.getGame().getGameView().getFrame();
                                Enterprise.getGame().getGameView().getFrame().setLocation((int) (bounds.getX() - ((frame.getWidth() - bounds.getWidth()) / 2)), (int) (bounds.getY() - ((frame.getHeight() - bounds.getHeight()) / 2)));
                            }
                        }



                    }
                    break;

                }

            }

        } else {

            Enterprise.getGame().getDatabase().createTable()
                    .name("settings")
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("name")
                                .dataType(DataTypes.STRING)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("value")
                                .dataType(DataTypes.STRING)
                                .build()
                    )
                    .build();

            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
            Enterprise.getGame().getRenderingHints().put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        }


    }

}