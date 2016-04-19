package de.titanium.enterprise.View.FightView;

import de.titanium.enterprise.Data.DataManager;
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

public class FightView extends View {

    private final int[] xPos = new int[] { 50, 430, 740, 1050 };
    private final int[] yPos = new int[] { 270, 268, 270, 250 };

    public FightView(MenuView viewMenu) {
        super(viewMenu);
    }


    @Override
    public void update(int tick) {

        //Updates Animation of all Heroes and the Enemy
        DataManager dataManager = Enterprise.getGame().getDataManager();

        dataManager.<LivingEntity>get("game.enemy").getAnimationQueue().element().next();

        LivingEntity[] heroes = dataManager.get("game.heroes");
        for(int i = 0; i < heroes.length; i++) {
            heroes[i].getAnimationQueue().element().next();
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

        LivingEntity[] heroes = dataManager.get("game.heroes");

        for(int i = 0; i < heroes.length; i++) {

            // Der atkuelle Held
            LivingEntity hero = heroes[i];

            // Die aktuelle Animationn
            Animator animation = hero.getAnimationQueue().element();

            if(!(hero.isAlive()) && animation.getType().equals(hero.getAnimationQueue().getDefaultAnimation())) {
                continue;
            }

            g.drawImage(animation.getFrame(), this.xPos[i], this.yPos[i], animation.getType().getWidth(), animation.getType().getHeight(), null);

            // sein Name
            Image text = Enterprise.getGame().getTextBuilder().toImage(hero.getName(), 10);
            g.drawImage(text, Math.max(this.xPos[i] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[i] - 25, null);

            // Werte
            Image textHP = Enterprise.getGame().getTextBuilder().toImage(String.format("HP %.2f - %.2f", hero.getHealth(), hero.getMaxHealth()), 6);
            g.drawImage(textHP, Math.max(this.xPos[i] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[i] - 100, null);

            Image textAttack = Enterprise.getGame().getTextBuilder().toImage(String.format("ATK %.2f", hero.getAttackValue()), 6);
            g.drawImage(textAttack, Math.max(this.xPos[i] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[i] - 75, null);

            Image textDexterity = Enterprise.getGame().getTextBuilder().toImage(String.format("DEX %.2f", hero.getDexterity()), 6);
            g.drawImage(textDexterity, Math.max(this.xPos[i] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[i] - 50, null);

            Image textType = Enterprise.getGame().getTextBuilder().toImage(hero.getClass().getSimpleName(), 6);
            g.drawImage(textType, Math.max(this.xPos[i] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[i] + 200, null);

        }

        //Get Enemy
        LivingEntity enemy = dataManager.get("game.enemy");
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

        // Werte
        Image textHP = Enterprise.getGame().getTextBuilder().toImage(String.format("HP %.2f - %.2f", enemy.getHealth(), enemy.getMaxHealth()), 6);
        g.drawImage(textHP, Math.max(this.xPos[3] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[3] - 100, null);

        Image textAttack = Enterprise.getGame().getTextBuilder().toImage(String.format("ATK %.2f", enemy.getAttackValue()), 6);
        g.drawImage(textAttack, Math.max(this.xPos[3] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[3] - 75, null);

        Image textDexterity = Enterprise.getGame().getTextBuilder().toImage(String.format("DEX %.2f", enemy.getDexterity()), 6);
        g.drawImage(textDexterity, Math.max(this.xPos[3] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[3] - 50, null);

        Image textType = Enterprise.getGame().getTextBuilder().toImage(enemy.getClass().getSimpleName(), 6);
        g.drawImage(textType, Math.max(this.xPos[3] - ((text.getWidth(null) - animation.getType().getWidth()) / 2), 50), this.yPos[3] + 200, null);


        // Hier werden die Achievements gezeichnet, falls welche freigeschaltet wurden.
        Enterprise.getGame().getAchievementManager().handle(g);

    }

    @Override
    public void render() {
        this.repaint();
    }

}
