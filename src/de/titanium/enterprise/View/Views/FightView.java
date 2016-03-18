package de.titanium.enterprise.View.Views;


import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Scores.BinarySearchTree;
import de.titanium.enterprise.Scores.Score;
import de.titanium.enterprise.Sprite.Animation.AnimationQueue;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Animation.Animator;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.Menu.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Yonas on 11.03.2016.
 */
public class FightView extends View {

    private List<Score> scores = new ArrayList<>();

    public FightView(MenuView viewMenu) {
        super(viewMenu);
    }
    private AnimationQueue ranger = new AnimationQueue(Animations.RANGER_IDLE);
    private AnimationQueue ranger1 = new AnimationQueue(Animations.RANGER_IDLE);
    private AnimationQueue ranger2 = new AnimationQueue(Animations.RANGER_IDLE);


    private boolean test = true;

    @Override
    public void update(int tick) {
        if(test) {
            ranger.add(Animations.RANGER_ATTACK);
            test = false;
        }
        this.ranger.element().next();
        this.ranger1.element().next();
        if(!(test)) {
            ranger2.add(Animations.RANGER_IDLE);
            ranger2.add(Animations.RANGER_BLOCK);
            test = true;
        }
        this.ranger2.element().next();
        //this.archer.element().next();

        //Score
        if(Enterprise.getGame().getDataManager().contains(BinarySearchTree.class)) {
            scores = new ArrayList<>();
            this.inorder(Enterprise.getGame().getDataManager().getOne(BinarySearchTree.class), scores);
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        //draw background and frame
        graphics.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        graphics.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        Animator rangerAnimator = this.ranger.element();
        graphics.drawImage(rangerAnimator.getFrame(), 50, 270, (int) (rangerAnimator.getFrame().getWidth() * rangerAnimator.getType().getWidthScale()), rangerAnimator.getType().getHeight(), null);

        Animator rangerAnimator1 = this.ranger1.element();
        graphics.drawImage(rangerAnimator1.getFrame(), 350, 270, (int) (rangerAnimator1.getFrame().getWidth() * rangerAnimator1.getType().getWidthScale()), rangerAnimator1.getType().getHeight(), null);

        Animator rangerAnimator2 = this.ranger2.element();
        graphics.drawImage(rangerAnimator2.getFrame(), 600, 270, (int) (rangerAnimator2.getFrame().getWidth() * rangerAnimator2.getType().getWidthScale()), rangerAnimator2.getType().getHeight(), null);


        if (scores != null) {
            int y = 0;
            for (Score score : scores) {
                graphics.drawString(score.getName() + " | " + score.getScore(), 1000, 100 + y);
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
