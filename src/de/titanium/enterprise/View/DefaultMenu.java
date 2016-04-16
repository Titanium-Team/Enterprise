package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Animation.Animator;
import de.titanium.enterprise.Sprite.Textures;

import java.awt.*;

public class DefaultMenu extends MenuView {

    private Animator animator = Animations.RANGER_WALK.createAnimator();
    private int x = -120;

    public DefaultMenu() {}

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        g.drawImage(Textures.DEFAULT_BACKGROUND.getImage(), 0, 0, null, null);

        //draw him walking
        g.drawImage(this.animator.getFrame(), this.x, 3, (int) (this.animator.getType().getWidth() * 0.85), (int) (this.animator.getType().getHeight() * 0.85), null);

        //Border
        g.drawImage(Textures.BORDER_DOWN.getImage(), 0, 0, null, null);

    }

    @Override
    public void update(int tick) {
        this.x++;

        if(this.x > 1400) {
            this.x = -120;
        }
        this.animator.next();
    }

    @Override
    public void render() {
        this.repaint();
    }

}
