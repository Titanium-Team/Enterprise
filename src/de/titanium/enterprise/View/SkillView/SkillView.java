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
import de.titanium.enterprise.View.HeroesView.HeroesView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by Yonas on 23.03.2016.
 */
public class SkillView extends View {

    private BinarySearchTree<SkillEntry> skillBinarySearchTree = Skills.defaultTree();
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

        // Hier wird die passende Beschreibung zu dem ausgewählten Skill gezeichnet.
        List<String> description = this.selectedSkill.getDescription();
        for(int i = 0; i < description.size(); i++) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(description.get(i), 7), 990, 50 + i * 20, null);
        }

        LivingEntity entity = Enterprise.getGame().getDataManager().get("game.heroes.skilling");

        if(this.selectedSkill.hasSkill(entity)) {

            // Falls das Entity diesen Skill bereits hat, dann kann dieser Skill als "Freigeschaltet" dargestellt
            // werden.

            g.drawImage(Textures.CHECKED_BUTTON.getImage().getScaledInstance(50, 50, 0), 990, 400, null);
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage("Freigeschaltet", 8), 1050, 415, null);

        } else {
            if(this.selectedSkill.isUnlockable(entity)) {

                // Falls der Skill noch nicht "Freigeschaltet" ist, es aber theoretisch möglich wäre (unabhängig von der
                // aktullen Anzahl an Skill-Punkten) wird der Skill als "Freischaltbar" eingestuft und dargestellt.

                g.drawImage(Enterprise.getGame().getTextBuilder().toImage("Freischaltbar", 8), 1050, 415, null);

            } else {

                // Falls der Skill noch nicht "Freigeschaltet" ist und auch aktuell noch nicht freischaltbar ist,
                // wird dieser als "Locked" dargestellt.
                g.drawImage(Enterprise.getGame().getTextBuilder().toImage("Locked", 8), 1050, 415, null);

            }

            // Beide Zustände werden aktuell mit dem selben Symbol gekennzeichnet.
            g.drawImage(Textures.FAILED_BUTTON.getImage().getScaledInstance(50, 50, 0), 990, 400, null);

        }

        // Das ausgewählte Entity wird dargestellt.
        Animator animator = entity.getAnimationQueue().element();
        g.drawImage(animator.getFrame(), 50, 270, animator.getType().getWidth(), animator.getType().getHeight(), null);

        // Der Name des Entitys wird dargestellt.
        Image text = Enterprise.getGame().getTextBuilder().toImage(entity.getName(), 10);
        g.drawImage(text, 50 - ((text.getWidth(null) - animator.getType().getWidth()) / 2), 245, null);

        // Die verfügbaren Skill-Punkte des Entitys werden oben, rechts dargestellt.
        Image points = Enterprise.getGame().getTextBuilder().toImage(String.format("SP: %d", entity.getSkillPoints()), 8);
        g.drawImage(points, 50, 40, null);

        // Hier wird die Methode aufgerufen die den Skill-Tree zeichnet.
        this.drawTree(g, this.skillBinarySearchTree, BinaryTreeMath.maxDepth(this.skillBinarySearchTree), 0, 1);

    }


    @Override
    public void update(int tick) {

        //update animation
        LivingEntity hero = Enterprise.getGame().getDataManager().get("game.heroes.skilling");
        hero.getAnimationQueue().element().next();


        // @Improve: Es wird nur alle paar Ticks geprüft, da es sonst vorkommt das der Button als "doppelt" Gedrückt erkannt wird
        // dies erschwert die Navigation, ist überall im Code aktuell ein Problem. Dies muss man entwender im KeyManager lösen
        // oder man findet einen besseren Wert als "4", da es sonst auch vorkommen kann das der Druck der Taste nicht erkannt wird.
        if(tick % 4 == 0) {

            BinarySearchTree<SkillEntry> current = this.skillBinarySearchTree.search(new SkillEntry(this.selectedSkill));
            BinarySearchTree<SkillEntry> parent = BinaryTreeMath.findParent(new SkillEntry(this.selectedSkill), this.skillBinarySearchTree);

            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) {

                // Wenn "ESC" gedrückt wird, dann soll man wieder in der HeroesView landen, um dann ggf. andere Helden
                // zu skillen o.ä.

                Enterprise.getGame().getViewManager().switchTo(HeroesView.class);

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_LEFT) && !(current.getLeftTree().isEmpty())) {

                // Wenn "Pfeiltaste-Links" gedrückt wird und es noch weiter nach links im Bau geht, dann soll man
                // sich im Baum in die linke Node bewegen.

                this.selectedSkill = current.getLeftTree().getContent().getSkill();

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_RIGHT) && !(current.getRightTree().isEmpty())) {

                // Wenn "Pfeiltaste-Rechts" gedrückt wird und es noch weiter nach rechts im Bau geht, dann soll man
                // sich im Baum in die rechte Node bewegen.

                this.selectedSkill = current.getRightTree().getContent().getSkill();

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_UP) && !(parent == null)) {

                // Wenn "Pfeiltaste-Oben" gedrückt wird und es noch weiter nach oben im Bau geht, dann soll man
                // sich im Baum in die obere also in die Parent Node des aktuellen bewegen.

                this.selectedSkill = parent.getContent().getSkill();

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ENTER)) {

                // Wenn "Enter" gedrückt wird, der Held diesen Skill theoretisch freischalten kann und er auch
                // genügend Skill-Punkte besitzt, dann wird der Skill freigeschaltet.

                if(this.selectedSkill.isUnlockable(hero) && hero.getSkillPoints() >= this.selectedSkill.getPrice() && !(this.selectedSkill.hasSkill(hero))) {
                    hero.setSkillPoints(hero.getSkillPoints() - this.selectedSkill.getPrice());
                    hero.addSkill(this.selectedSkill);
                    this.selectedSkill.apply(hero);
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
