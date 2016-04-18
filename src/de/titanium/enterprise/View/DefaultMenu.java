package de.titanium.enterprise.View;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Animation.Animator;
import de.titanium.enterprise.Sprite.Textures;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DefaultMenu extends MenuView {

    private Animator rangerAnimator = Animations.RANGER_WALK.createAnimator();

    // Torches
    private Random random = new Random();

    private Animator torchOneAnimator = Animations.TORCH.createAnimator();
    private Animator torchTwoAnimator = Animations.TORCH.createAnimator();
    private Animator torchThreeAnimator = Animations.TORCH.createAnimator();
    private Animator torchFourAnimator = Animations.TORCH.createAnimator();
    private Animator torchFiveAnimator = Animations.TORCH.createAnimator();

    private int x = -120;

    private final int[] torches = new int[7];
    private float currentAlpha = 1F;

    {

        this.torches[0] = -140;

        this.torches[1] = 60;
        this.torches[2] = 326;
        this.torches[3] = 600;
        this.torches[4] = 890;
        this.torches[5] = 1190;

        this.torches[6] = 1400;

    }

    public DefaultMenu() {

        // Startet ab einem Random Wert, damit die Fackeln mehr Random aussehen.

        this.torchOneAnimator.setIndex(this.random.nextInt(this.torchOneAnimator.getAmount()));
        this.torchTwoAnimator.setIndex(this.random.nextInt(this.torchOneAnimator.getAmount()));
        this.torchThreeAnimator.setIndex(this.random.nextInt(this.torchOneAnimator.getAmount()));
        this.torchFourAnimator.setIndex(this.random.nextInt(this.torchOneAnimator.getAmount()));
        this.torchFiveAnimator.setIndex(this.random.nextInt(this.torchOneAnimator.getAmount()));

    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        // draw background
        BufferedImage image = Textures.DEFAULT_BACKGROUND.getImage();
        g.drawImage(image, 0, 0, null, null);

        // draw torches
        g.drawImage(this.torchOneAnimator.getFrame(), 46, 12, null);
        g.drawImage(this.torchTwoAnimator.getFrame(), 309, 10, null);
        g.drawImage(this.torchThreeAnimator.getFrame(), 584, 11, null);
        g.drawImage(this.torchFourAnimator.getFrame(), 876, 12, null);
        g.drawImage(this.torchFiveAnimator.getFrame(), 1176, 12, null);

        // Damit der Held je nach Abstand zu den Fackeln "dunkler" bzw. "heller" wird, wird der Abstand zwischen der
        // aktuellen Position mit der Postion des Spielers verrechnet, damit man bis zur halben Strecke das Bild um
        // 50% zu verdunkeln und dann wieder hell machen.
        int currentTorch = 1;
        for(int i = 0; i < this.torches.length; i++) {
            if(this.x < this.torches[i]) {
                currentTorch = i;
                break;
            }
        }

        int areaX = this.torches[currentTorch] - this.x;
        float areaWidth = (this.torches[currentTorch] - this.torches[currentTorch - 1]);
        if(areaX > areaWidth / 2) {
            this.currentAlpha -= (1F / areaWidth);
            this.currentAlpha = Math.max(0, this.currentAlpha);
        } else {
            this.currentAlpha += (1F / areaWidth);
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
        this.torchOneAnimator.next();
        this.torchTwoAnimator.next();
        this.torchThreeAnimator.next();
        this.torchFourAnimator.next();
        this.torchFiveAnimator.next();
    }

    @Override
    public void render() {
        this.repaint();
    }

}
