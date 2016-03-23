package de.titanium.enterprise.View.SkillView;

import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Data.BinaryTreeMath;
import de.titanium.enterprise.Data.Datas.SkillEntry;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Skill.Skills;
import de.titanium.enterprise.Sprite.Textures;
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
    private Skill selected = Skills.NEU_00_OLYMPUS;

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

        //draw selected description
        List<String> description = this.selected.getDescription();
        for(int i = 0; i < description.size(); i++) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(description.get(i), 7), 990, 50 + i * 20, null);
        }

        //draw tree
        this.drawTree(g, this.skillBinarySearchTree, BinaryTreeMath.maxDepth(this.skillBinarySearchTree), 0, 1);
    }


    @Override
    public void update(int tick) {

        if(tick % 4 == 0) {

            BinarySearchTree<SkillEntry> current = this.skillBinarySearchTree.search(new SkillEntry(this.selected));
            BinarySearchTree<SkillEntry> parent = BinaryTreeMath.findParent(new SkillEntry(this.selected), this.skillBinarySearchTree);

            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_LEFT) && !(current.getLeftTree().isEmpty())) { //nach links gehen
                this.selected = current.getLeftTree().getContent().getSkill();
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_RIGHT) && !(current.getRightTree().isEmpty())) { //nach rechts gehen
                this.selected = current.getRightTree().getContent().getSkill();
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_UP) && !(parent == null)) { //nach oben gehen
                this.selected = parent.getContent().getSkill();
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
        Image image = Enterprise.getGame().getTextBuilder().toImage(text, (this.selected == binaryTree.getContent().getSkill() ? 10 : 8));

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
