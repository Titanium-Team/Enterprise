package de.titanium.enterprise.View.SkillView;

import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Data.BinaryTreeMath;
import de.titanium.enterprise.Data.Datas.SkillEntry;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Skill.Skills;
import de.titanium.enterprise.Sprite.Animation.Animator;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

/**
 * Created by Yonas on 23.03.2016.
 */
public class SkillView extends View {

    private BinarySearchTree<SkillEntry> skillBinarySearchTree = Skills.defaultTree();
    private int selectedEntity = 0;
    private Skill selectedSkill = Skills.NEU_00_OLYMPUS;

    public SkillView(MenuView viewMenu) {
        super(viewMenu);
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //draw background and frame
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        //draw selected skill description
        List<String> description = this.selectedSkill.getDescription();
        for(int i = 0; i < description.size(); i++) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(description.get(i), 7), 990, 50 + i * 20, null);
        }

        //draw if he unlocked it, yet
        LivingEntity entity = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes")[this.selectedEntity];

        if(this.selectedSkill.hasSkill(entity)) {
            g.drawImage(Textures.CHECKED_BUTTON.getImage().getScaledInstance(50, 50, 0), 990, 400, null);
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage("Freigeschaltet", 8), 1050, 415, null);
        } else {
            if(this.selectedSkill.isUnlockable(entity)) {
                g.drawImage(Enterprise.getGame().getTextBuilder().toImage("Freischaltbar", 8), 1050, 415, null);
            } else {
                g.drawImage(Enterprise.getGame().getTextBuilder().toImage("Locked", 8), 1050, 415, null);
            }
            g.drawImage(Textures.FAILED_BUTTON.getImage().getScaledInstance(50, 50, 0), 990, 400, null);
        }

        //draw selected entity
        Animator animator = entity.getAnimationQueue().element();
        g.drawImage(animator.getFrame(), 50, 270, animator.getType().getWidth(), animator.getType().getHeight(), null);

        Image text = Enterprise.getGame().getTextBuilder().toImage(entity.getName(), 10);
        g.drawImage(text, 50 - ((text.getWidth(null) - animator.getType().getWidth()) / 2), 245, null);

        //draw skill points
        Image points = Enterprise.getGame().getTextBuilder().toImage(String.format("Points: %d", entity.getSkillPoints()), 8);
        g.drawImage(points, 40, 40, null);

        //draw tree
        this.drawTree(g, this.skillBinarySearchTree, BinaryTreeMath.maxDepth(this.skillBinarySearchTree), 0, 1);
    }


    @Override
    public void update(int tick) {

        //update animation
        Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes")[this.selectedEntity].getAnimationQueue().element().next();


        // @Improve: Es wird nur alle paar Ticks geprüft, da es sonst vorkommt das der Button als "doppelt" Gedrückt erkannt wird
        // dies erschwert die Navigation, ist überall im Code aktuell ein Problem. Dies muss man entwender im KeyManager lösen
        // oder man findet einen besseren Wert als "4", da es sonst auch vorkommen kann das der Druck der Taste nicht erkannt wird.
        if(tick % 4 == 0) {

            BinarySearchTree<SkillEntry> current = this.skillBinarySearchTree.search(new SkillEntry(this.selectedSkill));
            BinarySearchTree<SkillEntry> parent = BinaryTreeMath.findParent(new SkillEntry(this.selectedSkill), this.skillBinarySearchTree);
            LivingEntity[] entities = Enterprise.getGame().getDataManager().getOne("game.heroes");

            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) { //zurück zum hauptmenü
                Enterprise.getGame().getViewManager().switchTo(GameMenuView.class);
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_A) && !(current.getLeftTree().isEmpty())) { //nach links gehen
                this.selectedSkill = current.getLeftTree().getContent().getSkill();
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_D) && !(current.getRightTree().isEmpty())) { //nach rechts gehen
                this.selectedSkill = current.getRightTree().getContent().getSkill();
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_W) && !(parent == null)) { //nach oben gehen
                this.selectedSkill = parent.getContent().getSkill();
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_LEFT)) { //helden wechsel
                this.selectedEntity--;

                if(selectedEntity < 0) {
                    this.selectedEntity = entities.length - 1;
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_RIGHT)) { //helden wechsel
                this.selectedEntity++;

                if(selectedEntity >= entities.length) {
                    selectedEntity = 0;
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ENTER)) { //freischalten

                LivingEntity entity = entities[this.selectedEntity];

                if(this.selectedSkill.isUnlockable(entity) && entity.getSkillPoints() >= this.selectedSkill.getPrice() && !(this.selectedSkill.hasSkill(entity))) {
                    entity.setSkillPoints(entity.getSkillPoints() - this.selectedSkill.getPrice());
                    entity.addSkill(this.selectedSkill);
                    this.selectedSkill.apply(entity);
                }

            }

        }

    }

    @Override
    public void render() {
        this.repaint();
    }

    private void drawTree(Graphics2D graphic, BinarySearchTree<SkillEntry> binaryTree, int treeDepth, int currentDepth, int index) {

        //draw box w/ value
        int yOffset = 50;
        String text = binaryTree.getContent().getSkill().getName() + " (" + binaryTree.getContent().getSkill().getPrice() + ")";
        Image image = Enterprise.getGame().getTextBuilder().toImage(text, (this.selectedSkill == binaryTree.getContent().getSkill() ? 10 : 8));

        Point2D position = BinaryTreeMath.calculate(960, this.getHeight() / 2, index, treeDepth, currentDepth);

        graphic.drawImage(image, (int) position.getX() - (image.getWidth(null) / 2), (int) position.getY() - image.getHeight(null) + yOffset , null);

        if(!(binaryTree.getLeftTree().isEmpty())) {
            drawTree(graphic, binaryTree.getLeftTree(), treeDepth, currentDepth + 1, index * 2 - 1);
        }

        if(!(binaryTree.getRightTree().isEmpty())) {
            drawTree(graphic, binaryTree.getRightTree(), treeDepth, currentDepth + 1, index * 2);
        }

    }

}
