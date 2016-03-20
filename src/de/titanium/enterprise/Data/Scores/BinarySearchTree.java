package de.titanium.enterprise.Data.Scores;

/**
 * Created by 204g01 on 16.03.2016.
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> node;

    public BinarySearchTree(){}

    public void insert(T content) {
        this.insert(content, this);
    }

    public void insert(T content, BinarySearchTree<T> current){

        BinarySearchTree tree = new BinarySearchTree();
        tree.node = new Node(content);

        if(content != null) {
            if (current.isEmpty()) {
                current.node = tree.node;
            } else if (content.isLess(current.getContent())) {
                if (current.getLeftTree().isEmpty()) {
                    current.node.left = tree;
                } else {
                    insert(content, current.getLeftTree());
                }
            } else if (content.isGreater(current.getContent())) {
                if (current.getRightTree().isEmpty()) {
                    current.node.right = tree;
                } else {
                    insert(content, current.getRightTree());
                }
            }
        }

    }

    private void rebalancing(){
        if (node.left.getHeight() - node.right.getHeight() == 2){
            //if (contentType.isLess(getNodeOfLeftSuccessor().content)){
            if (!node.left.node.left.isEmpty()){
                rotateRight();
            }else {
                node.left.rotateLeft();
                rotateRight();
            }
        }

        if (node.left.getHeight() - node.right.getHeight() == -2){
            //if (contentType.isGreater(getNodeOfRightSuccessor().content)){
            if (!node.right.node.right.isEmpty()){
                rotateLeft();
            }else {
                node.right.rotateRight();
                rotateLeft();
            }
        }
    }

    private void rotateRight(){
        T contentType = node.content;
        Node<T> node2 = node.right.node;
        node.content = node.left.getContent();
        node.right.node = new Node<T>(contentType);
        if (!node.right.isEmpty()) {
            node.right.node.right.node = node2;
            node.right.node.left.node = node.left.node.right.node;
        }
        node.left.node = node.left.node.left.node;

        calculateHeight(this);
    }

    private void rotateLeft(){
        T contentType = node.content;
        Node<T> node2 = node.left.node;
        node.content = node.right.getContent();
        node.left.node = new Node<T>(contentType);
        if (!node.left.isEmpty()) {
            node.left.node.left.node = node2;
            node.left.node.right.node = node.right.node.left.node;
        }
        node.right.node = node.right.node.right.node;

        calculateHeight(this);
    }

    public int calculateHeight(BinarySearchTree<T> current){

        if(!current.isEmpty()){
            if(calculateHeight(current.getLeftTree()) >= calculateHeight(current.getRightTree())){
                return current.node.height = calculateHeight(current.getLeftTree()) + 1;
            }else {
                return current.node.height = calculateHeight(current.getRightTree()) + 1;
            }
        }else {
            return 0;
        }

    }


    public BinarySearchTree<T> search(T content, BinarySearchTree<T> current){

        if(!current.isEmpty() && content != null){

            if(content.isEqual(current.getContent())){
                return current;
            }
            if(content.isLess(current.getContent())){
                return search(content, current.getLeftTree());
            }
            if(content.isGreater(current.getContent())){
                return search(content, current.getRightTree());
            }
        }

        return null;
    }

    public BinarySearchTree<T> getRightTree() {
        if (!isEmpty()) {
            return this.node.right;
        }
        return null;
    }


    public BinarySearchTree<T> getLeftTree() {
        if (!isEmpty()) {
            return this.node.left;
        }
        return null;
    }

    public T getContent() {
        if (!isEmpty()) {
            return this.node.content;
        }
        return null;
    }

    public int getHeight(){
        if(!isEmpty()){
            return this.node.height;
        }
        return 0;
    }

    public boolean isEmpty(){
        return(this.node == null);
    }


    private class Node <T extends Comparable<T>>{

        private int height;
        private T content;
        private BinarySearchTree<T> left;
        private BinarySearchTree<T> right;

        public Node(T content){
            this.content = content;
            this.left = new BinarySearchTree<T>();
            this.right = new BinarySearchTree<T>();
        }
    }
}
