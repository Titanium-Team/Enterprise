package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Animation.Animator;
import de.titanium.enterprise.Sprite.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DefaultMenu extends MenuView {

    private Animator rangerAnimator = Animations.RANGER_WALK.createAnimator();
    private Animator torchAnimator = Animations.TORCH.createAnimator();

    private int x = -120;

    private static final int[] torches = new int[7];
    private float currentAlpha = 1F;

    static {

        torches[0] = -140;

        torches[1] = 60;
        torches[2] = 326;
        torches[3] = 600;
        torches[4] = 890;
        torches[5] = 1190;

        torches[6] = 1400;

    }

    public DefaultMenu() {}

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        // draw background
        BufferedImage image = Textures.DEFAULT_BACKGROUND.getImage();
        g.drawImage(image, 0, 0, null, null);

        // draw torches
        g.drawImage(this.torchAnimator.getFrame(), 46, 17, null);
        g.drawImage(this.torchAnimator.getFrame(), 309, 10, null);
        g.drawImage(this.torchAnimator.getFrame(), 584, 11, null);
        g.drawImage(this.torchAnimator.getFrame(), 876, 12, null);
        g.drawImage(this.torchAnimator.getFrame(), 1176, 12, null);

        // calculate alpha
        int currentTorch = 1;
        for(int i = 0; i < torches.length; i++) {
            if(this.x < torches[i]) {
                currentTorch = i;
                break;
            }
        }

        int area = torches[currentTorch] - this.x;
        float totalArea = (torches[currentTorch] - torches[currentTorch - 1]);
        if(area > totalArea / 2) {
            this.currentAlpha -= (1F / totalArea);
            this.currentAlpha = Math.max(0, this.currentAlpha);
        } else {
            this.currentAlpha += (1F / totalArea);
            this.currentAlpha = Math.min(1, this.currentAlpha);
        }

        // draw hero
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.currentAlpha));
        g.drawImage(this.rangerAnimator.getFrame().getScaledInstance((int) (this.rangerAnimator.getType().getWidth() * 0.5), (int) (this.rangerAnimator.getType().getHeight() * 0.5), 0), this.x, 35, null);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

        //Border
        g.drawImage(Textures.BORDER_DOWN.getImage(), 0, 0, null, null);

    }

    @Override
    public void update(int tick) {
        this.x++;

        if(this.x > 1400) {
            this.x = -120;
        }

        this.rangerAnimator.next();
        this.torchAnimator.next();
    }

    @Override
    public void render() {
        this.repaint();
    }

}
