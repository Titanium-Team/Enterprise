package de.titanium.enterprise.View.FightView;


import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Data.DataManager;
import de.titanium.enterprise.Data.Datas.Score;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Sprite.Animation.Animator;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yonas on 11.03.2016.
 */
public class FightView extends View {

    private List<Score> scores = new ArrayList<>();

    private final int[] xPos = new int[] { 50, 430, 740, 1050 };
    private final int[] yPos = new int[] { 270, 268, 270, 250 };

    public FightView(MenuView viewMenu) {
        super(viewMenu);
    }


    @Override
    public void update(int tick) {

        //Updates Animation of all Heroes and the Enemy
        DataManager dataManager = Enterprise.getGame().getDataManager();

        dataManager.<LivingEntity>getOne("game.enemy").getAnimationQueue().element().next();

        LivingEntity[] heroes = dataManager.getOne("game.heroes");
        for(int i = 0; i < heroes.length; i++) {
            heroes[i].getAnimationQueue().element().next();
        }

        //Score
        if(Enterprise.getGame().getDataManager().contains("game.defense.scores")) {
            scores = new ArrayList<>();
            this.inorder(Enterprise.getGame().getDataManager().<BinarySearchTree>getOne("game.defense.scores"), scores);
        }

    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());


        //draw background and frame
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        //Draw Heroes
        DataManager dataManager = Enterprise.getGame().getDataManager();

        LivingEntity[] heroes = dataManager.getOne("game.heroes");

        for(int i = 0; i < heroes.length; i++) {
            LivingEntity hero = heroes[i];
            Animator animation = hero.getAnimationQueue().element();
            g.drawImage(animation.getFrame(), this.xPos[i], this.yPos[i], animation.getType().getWidth(), animation.getType().getHeight(), null);

            Image text = Enterprise.getGame().getTextBuilder().toImage(hero.getName(), 10);
            g.drawImage(text, this.xPos[i] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), this.yPos[i] - 25, null);
        }

        //Get Enemy
        LivingEntity enemy = dataManager.getOne("game.enemy");
        Animator animation = enemy.getAnimationQueue().element();

        //flip animation
        BufferedImage bufferedImage = animation.getFrame();
        AffineTransform affineTransform = AffineTransform.getScaleInstance(-1, 1);
        affineTransform.translate(-bufferedImage.getWidth(), 0);

        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        bufferedImage = affineTransformOp.filter(bufferedImage, null);

        //Draw Enemy
        g.drawImage(bufferedImage, this.xPos[3], this.yPos[3], animation.getType().getWidth(), animation.getType().getHeight(), null);
        Image text = Enterprise.getGame().getTextBuilder().toImage(enemy.getName(), 10);
        g.drawImage(text, this.xPos[3] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), this.yPos[3] - 25, null);

        // @Cleanup: Sollte hier nur zu Testzwecken sein und in den nächsten Tagen entfernt werden, sobald es eine bessere
        // Alternative zur Darstellung gibt.
        if (scores != null) {
            int y = 0;
            for (Score score : scores) {
                g.drawString(score.getName() + " | " + score.getScore(), 1000, 100 + y);
                y += 10;
            }
        }

    }


    private void inorder(BinarySearchTree current, List<Score> scores){

        if(current != null) {
            if (!current.getRightTree().isEmpty()) {
                this.inorder(current.getRightTree(), scores);
            }

            scores.add((Score) current.getContent());

            if (!current.getLeftTree().isEmpty()) {
                this.inorder(current.getLeftTree(), scores);
            }
        }
        
    }

    @Override
    public void render() {
        this.repaint();
    }

}
