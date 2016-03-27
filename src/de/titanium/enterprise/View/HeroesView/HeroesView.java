package de.titanium.enterprise.View.HeroesView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Sprite.Animation.Animator;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Comparator;

/**
 * Created by Yonas on 23.03.2016.
 */
public class HeroesView extends View {

    private final int[] xPos = new int[] { 50, 430, 740 };
    private final int[] yPos = new int[] { 270, 268, 270 };
    private LivingEntity[] livingEntities = null;

    public HeroesView(MenuView viewMenu) {
        super(viewMenu);
        this.livingEntities = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes").clone();
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //draw background and frame
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        //Draw heros
        for(int i = 0; i < this.livingEntities.length; i++) {
            Animator animator = this.livingEntities[i].getAnimationQueue().element();
            g.drawImage(animator.getFrame(), this.xPos[i], this.yPos[i], animator.getType().getWidth(), animator.getType().getHeight(), null);

            Image text = Enterprise.getGame().getTextBuilder().toImage(this.livingEntities[i].getName(), 10);
            g.drawImage(text, this.xPos[i] - ((text.getWidth(null) - animator.getType().getWidth()) / 2), this.yPos[i] - 25, null);
        }

    }

    @Override
    public void update(int tick) {

        //update animation
        for(int i = 0; i < this.livingEntities.length; i++) {
            this.livingEntities[i].getAnimationQueue().element().next();
        }

        if(tick % 4 == 0) {

            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) { //zurück zum hauptmenü
                Enterprise.getGame().getViewManager().switchTo(GameMenuView.class);
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_1)) {
                this.livingEntities = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes").clone();
                this.sort(this.livingEntities, 0, this.livingEntities.length - 1, new Comparator<LivingEntity>() {

                    @Override
                    public int compare(LivingEntity o1, LivingEntity o2) {

                        if(o1.getAttackValue() > o2.getAttackValue()) {
                            return 1;
                        }

                        if(o1.getAttackValue() < o2.getAttackValue()) {
                            return -1;
                        }

                        return 0;
                    }

                });
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_2)) {
                this.livingEntities = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes").clone();
                this.sort(this.livingEntities, 0, this.livingEntities.length - 1, new Comparator<LivingEntity>() {

                    @Override
                    public int compare(LivingEntity o1, LivingEntity o2) {

                        if (o1.getDexterity() > o2.getDexterity()) {
                            return 1;
                        }

                        if (o1.getDexterity() < o2.getDexterity()) {
                            return -1;
                        }

                        return 0;
                    }

                });
            }
        }

    }

    /**
     * Diese Methode sortiert die angegebenen LivingEntities abhängig vom übergebenen Comparator.
     * @param livingEntities
     * @param start
     * @param end
     * @param comparator
     */
    private void sort(LivingEntity[] livingEntities, int start, int end, Comparator<LivingEntity> comparator) {

        if(start < end) {

            int pivot = (start + end) / 2;
            int i = start;
            int j = end;

            while(i <= j) {

                while(comparator.compare(livingEntities[i], livingEntities[pivot]) == -1) {
                    i++;
                }

                while(comparator.compare(livingEntities[j], livingEntities[pivot]) == 1) {
                    j--;
                }

                if(i <= j) {
                    LivingEntity tmp = livingEntities[i];
                    livingEntities[i] = livingEntities[j];
                    livingEntities[j] = tmp;

                    i++;
                    j--;
                }

            }

            if(start < j) {
                sort(livingEntities, start, j, comparator);
            }

            if(end > i) {
                sort(livingEntities, i, end, comparator);
            }

        }

    }

    @Override
    public void render() {
        this.repaint();
    }

}
