package de.titanium.enterprise.Data;

import de.titanium.enterprise.Data.Datas.SkillEntry;

import java.awt.geom.Point2D;

/**
 * Created by Yonas on 23.03.2016.
 */
public class BinaryTreeMath {


    public static Point2D calculate(int width, int height, int index, int treeDepth, int depth) {

        int x = (int) (index * width / (Math.pow(2, depth) + 1));
        int y = (height / treeDepth * depth) + 20;

        return new Point2D.Double(x, y);

    }

    public static Point2D[] linePosition(int fontWidth, int width, int height, BinarySearchTree binaryTree, int index, int treeDepth, int rootDepth) {

        Point2D start = BinaryTreeMath.calculate(width, height, index, treeDepth, rootDepth);
        Point2D left = null;
        Point2D right = null;

        int startX = (int) start.getX() + (fontWidth / 2);
        int startY = (int) start.getY() + 4;

        if(!(binaryTree.getLeftTree().isEmpty())) {

            left = BinaryTreeMath.calculate(width, height, index * 2 - 1, treeDepth, rootDepth + 1);

            int leftX = (int) left.getX() + (fontWidth / 2);
            int leftY = (int) left.getY() - 12;

            left = new Point2D.Double(leftX, leftY);

        }

        if(!(binaryTree.getRightTree().isEmpty())) {

            right = BinaryTreeMath.calculate(width, height, index * 2, treeDepth, rootDepth + 1);

            int rightX = (int) right.getX() + (fontWidth / 2);
            int rightY = (int) right.getY() - 12;

            right = new Point2D.Double(rightX, rightY);

        }

        return new Point2D[] {
                new Point2D.Double(startX, startY),
                left,
                right
        };

    }


    public static int maxDepth(BinarySearchTree binaryTree) {

        if(binaryTree == null || binaryTree.isEmpty()) {
            return 0;
        }

        int left = maxDepth(binaryTree.getLeftTree());
        int right = maxDepth(binaryTree.getRightTree());
        return Math.max(left, right) + 1;

    }

    public static BinarySearchTree findMaximum(BinarySearchTree current, BinarySearchTree max) {

        if(current.isEmpty()) {
            return max;
        }

        if(current.getContent().compareTo(max.getContent()) == 1) {
            max = current;
        }

        return findMaximum(current.getRightTree(), max);

    }

    public static BinarySearchTree<SkillEntry> findParent(SkillEntry search, BinarySearchTree<SkillEntry> current) {

        if (!(current.getLeftTree().isEmpty()) && current.getLeftTree().getContent().getSkill() == search.getSkill()) {
            return current;
        } else if (!(current.getRightTree().isEmpty()) && current.getRightTree().getContent().getSkill() == search.getSkill()) {
            return current;
        }

        if(search.compareTo(current.getContent()) == -1 && !(current.getLeftTree() == null)) {
            return BinaryTreeMath.findParent(search, current.getLeftTree());
        }

        if(search.compareTo(current.getContent()) == 1 && !(current.getRightTree() == null)) {
            return BinaryTreeMath.findParent(search, current.getRightTree());
        }

        return null;

    }

}