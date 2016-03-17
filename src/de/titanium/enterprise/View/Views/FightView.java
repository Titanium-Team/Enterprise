package de.titanium.enterprise.View.Views;

import de.titanium.enterprise.Sprite.Animation.AnimationQueue;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Animation.Animator;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.Menu.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;

/**
 * Created by Yonas on 11.03.2016.
 */
public class FightView extends View {

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



    }

    @Override
    public void render() {
        this.repaint();
    }

}
