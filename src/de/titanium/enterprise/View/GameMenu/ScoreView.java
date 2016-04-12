package de.titanium.enterprise.View.GameMenu;

import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.TextBuilder;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 204g02 on 08.04.2016.
 */
public class ScoreView extends View {

    public ScoreView(MenuView menuView) {
        super(menuView);
    }

    @Override
    public void paintComponent(Graphics graphics) {

        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHints(Enterprise.getGame().getRenderingHints());
        super.paintComponent(graphics);

        //backgroud
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);



        //border
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);


        List<Double> scores = new ArrayList<>();
        this.inorder(Enterprise.getGame().getDataManager().<BinarySearchTree<Double>>get("game.scores"), scores);

        TextBuilder textBuilder = Enterprise.getGame().getTextBuilder();

        for(int i = 0; i < scores.size(); i++) {

            g.drawImage(
                    textBuilder.toImage(String.format("%d. %.2f", (i + 1), scores.get(i)), 8),
                    50,
                    80 + 30 * i,
                    null
            );

        }

        // Hier werden die Achievements gezeichnet, falls welche freigeschaltet wurden.
        Enterprise.getGame().getAchievementManager().handle(g);

    }

    private void inorder(BinarySearchTree<Double> current, List<Double> scores){
        if(current != null) {
            if (!current.getRightTree().isEmpty()) {
                this.inorder(current.getRightTree(), scores);
            }
            scores.add(current.getContent().doubleValue());
            if (!current.getLeftTree().isEmpty()) {
                this.inorder(current.getLeftTree(), scores);
            }
        }
    }


    @Override
    public void update(int tick) {
        if(tick % 4 == 0) {

            if (Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) { //zurueck zum hauptmenue

                // Wenn "ESC" gedrueckt wird, dann wird man wieder ins Hauptmenue gebracht.
                Enterprise.getGame().getViewManager().switchTo(GameMenuView.class);

            }
        }
    }

    @Override
    public void render() {
        this.repaint();
    }

}
