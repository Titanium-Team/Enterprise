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


    }

    @Override
    public void render() {
        this.repaint();
    }

}
