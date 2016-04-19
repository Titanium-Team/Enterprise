package de.titanium.enterprise.Data;

import de.titanium.enterprise.Data.Datas.SkillEntry;

import java.awt.geom.Point2D;

public class BinaryTreeMath {


    public static Point2D calculate(int width, int height, int index, int treeDepth, int depth) {

        int x = (int) (index * width / (Math.pow(2, depth) + 1));
        int y = (height / treeDepth * depth) + 20;

        return new Point2D.Double(x, y);

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