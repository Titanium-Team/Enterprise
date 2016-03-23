package de.titanium.enterprise.Data;


/**
 * Created by 204g01 on 16.03.2016.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> node;

    public BinarySearchTree() {}

    private class Node<T extends Comparable<T>> {

        private T content;
        private int height;

        private BinarySearchTree<T> left;
        private BinarySearchTree<T> right;

        public Node(T content) {
            this.content = content;
            this.height = 0;
            this.left = new BinarySearchTree<T>();
            this.right = new BinarySearchTree<T>();
        }

    }

    public int getHeight() {
        return this.node.height;
    }

    public T getContent() {
        return (this.isEmpty() ? null : this.node.content);
    }

    public BinarySearchTree<T> getLeftTree() {
        return this.isEmpty() ? null : this.node.left;
    }

    public BinarySearchTree<T> getRightTree() {
        return this.isEmpty() ? null : this.node.right;
    }

    public boolean isEmpty() {
        return this.node == null;
    }

    public void insert(T insert) {
        this.insert(insert, this);
    }

    private void insert(T insert, BinarySearchTree<T> current) {

        if(!(insert == null)) {
            BinarySearchTree tree = new BinarySearchTree();
            tree.node = new Node(insert);

            if (current.isEmpty()) {
                current.node = tree.node;
            } else if (insert.compareTo(current.getContent()) == -1) {
                if (current.getLeftTree().isEmpty()) {
                    current.node.left = tree;
                } else {
                    this.insert(insert, current.getLeftTree());
                }
            } else if (insert.compareTo(current.getContent()) == 1) {
                if (current.getRightTree().isEmpty()) {
                    current.node.right = tree;
                } else {
                    this.insert(insert, current.getRightTree());
                }
            }
            this.fixHeight(this);
        }

    }

    private void fixBalance(BinarySearchTree<T> current) {

        int i = current.getHeight() - current.getHeight();

        if(i == 1) {

        }

    }

    private void fixHeight(BinarySearchTree<T> current) {

        if(!(current.getLeftTree().isEmpty())) {
            this.fixHeight(current.getLeftTree());
        }

        if(!(current.getRightTree().isEmpty())) {
            this.fixHeight(current.getRightTree());
        }

        current.node.height = Math.max(BinaryTreeMath.maxDepth(current.getLeftTree()), BinaryTreeMath.maxDepth(current.getRightTree())) + 1;

    }

    public void remove(BinarySearchTree<T> value) {
        this.remove(value, this, null);
    }

    private BinarySearchTree<T> remove(BinarySearchTree<T> value, BinarySearchTree<T> current, BinarySearchTree<T> parent) {

        if(value == null) {
            return null;
        }

        if(value.getContent().compareTo(current.getContent()) == -1) {
            remove(value, current.getLeftTree(), current);
        } else if(value.getContent().compareTo(current.getContent()) == 1) {
            remove(value, current.getRightTree(), current);
        } else {
            if(!(current.getLeftTree().isEmpty()) && !(current.getRightTree().isEmpty())) {

                BinarySearchTree rightMax = BinaryTreeMath.findMaximum(current.getLeftTree(), current.getLeftTree());

                parent.remove(rightMax);

                rightMax.node.left = current.getLeftTree();
                rightMax.node.right = current.getRightTree();

                if(parent.getLeftTree() == current) {
                    parent.node.left = rightMax;
                } else {
                    parent.node.right = rightMax;
                }

            } else if(!(current.getRightTree().isEmpty())) {
                if(parent.getLeftTree() == current) {
                    parent.node.left = current.getRightTree();
                }else {
                    parent.node.right = current.getRightTree();
                }
            } else if(!(current.getLeftTree().isEmpty())) {
                if(parent.getLeftTree() == current) {
                    parent.node.left = current.getLeftTree();
                }else {
                    parent.node.right = current.getLeftTree();
                }
            } else {
                if(parent.getLeftTree() == current) {
                    parent.node.left = new BinarySearchTree<T>();
                } else {
                    parent.node.right = new BinarySearchTree<T>();
                }
            }

            this.fixHeight(this);
        }

        return null;

    }

    public BinarySearchTree<T> search(T search) {
        return this.search(search, this);
    }

    private BinarySearchTree<T> search(T search, BinarySearchTree<T> current) {

        if(current.getContent().compareTo(search) == 0) {
            return current;
        }

        if(search.compareTo(current.getContent()) == -1 && !(current.getLeftTree() == null)) {
            return this.search(search, current.getLeftTree());
        }

        if(search.compareTo(current.getContent()) == 1 && !(current.getRightTree() == null)) {
            return this.search(search, current.getRightTree());
        }

        return null;

    }

    public BinarySearchTree<T> max() {
        return this.max(this);
    }

    private BinarySearchTree<T> max(BinarySearchTree<T> current) {
        if(current.getRightTree().isEmpty()) {
            return current;
        }

        return this.max(current.getRightTree());
    }

}