package Model;

import Model.Node.TreeNode;

public class BinarySearchingTree<K extends Comparable<K>, E> {
    private TreeNode<K, E>  root;

    private int compare(TreeNode<K, E> node1 , TreeNode<K, E> node2){
        return node1.getKey().compareTo(node2.getKey());
    }

    public void insert(K key, E data){
        root = insert(root, key, data);
    }

    private TreeNode<K, E> insert(TreeNode<K, E>  root, K key, E data){
        if (root == null){
            root = new TreeNode<>(key, data);
            return root;
        }if (key.compareTo(root.getKey()) < 0) {
            root.setLeft(insert(root.getLeft(), key, data));
        } else if (key.compareTo(root.getKey()) > 0) {
            root.setRight(insert(root.getRight(), key, data));
        }
        return root;
    }

    public TreeNode<K, E> root(){
        return root;
    }

    public boolean search(TreeNode<K, E> node){
        return search(root, node);
    }

    private boolean search(TreeNode<K, E> root, TreeNode<K, E> node){
        if (root == null) {
            return false;
        }

        if (compare(root, node) == 0) {
            return true;
        }

        if (compare(root, node) < 0) {
            return search(root.getLeft(), node);
        }

        return search(root.getRight(), node);
    }

    public E findMin(){
        return findMin(root).getData();
    }

    private TreeNode<K, E> findMin(TreeNode<K, E> root){
        if (root.getLeft() != null){
            return findMin(root.getLeft());
        }return root;
    }

    public TreeNode<K, E> delete(TreeNode<K, E> node){
        return deleteInSubTree(root, node);
    }

    public TreeNode<K, E> delete(K key, E data){
        return deleteInSubTree(root, new TreeNode<>(key, data));
    }

    private TreeNode<K, E> deleteInSubTree(TreeNode<K, E> root, TreeNode<K, E> node) {
        if (root == null) {
            return null;
        }

        int compareResult = node.getKey().compareTo(root.getKey());

        if (compareResult < 0) {
            root.setLeft(deleteInSubTree(root.getLeft(), node));
        } else if (compareResult > 0) {
            root.setRight(deleteInSubTree(root.getRight(), node));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            root.setData(findMin(root.getRight()).getData());
            root.setRight(deleteInSubTree(root.getRight(), findMin(root.getRight())));
        }

        return root;
    }

    public void print(){
        printSubTree(root);
        System.out.println();
    }

    public void printSubTree(TreeNode<K, E> p){
        if (p != null){
            printSubTree(p.getLeft());
            System.out.print(p.getData() + " ");
            printSubTree(p.getRight());
        }
    }

    @Override
    public String toString(){
        return treeString(root);
    }

    private String treeString(TreeNode<K, E> p){
        if (p != null){
            return treeString(p.getLeft()) + " " + p + " " + treeString(p.getRight());
        }return "";
    }
}

