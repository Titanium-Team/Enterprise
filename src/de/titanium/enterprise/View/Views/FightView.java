package de.titanium.enterprise.View.Views;


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
    private AnimationQueue animationQueue = new AnimationQueue(Animations.RANGER_IDLE);


    boolean test = true;

    @Override
    public void update(int tick) {
        if(test) {
            this.animationQueue.add(Animations.RANGER_ATTACK);
            this.animationQueue.add(Animations.RANGER_ATTACK);
            this.animationQueue.add(Animations.RANGER_ATTACK);
            this.animationQueue.add(Animations.RANGER_ATTACK);
            test = false;
        }
        this.animationQueue.element().next();
    }

    @Override
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        //draw background and frame
        graphics.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        graphics.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        Animator animator = this.animationQueue.element();
        graphics.drawImage(animator.getFrame(), 50, 270, (int) (animator.getFrame().getWidth() * 0.2), animator.getType().getHeight(), null);



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
