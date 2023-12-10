package Model;

import Model.Node.TreeNode;

public class BinarySearchingTree<K extends Comparable<K>, E> {
    private TreeNode<K, E> root;

    private int height(TreeNode<K, E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    private int getBalance(TreeNode<K, E> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    // Cân bằng cây tại một nút cụ thể
    private TreeNode<K, E> balanceNode(TreeNode<K, E> node) {
        int balance = getBalance(node);

        // Nếu cây bị mất cân bằng về phải
        if (balance > 1) {
            // Nếu là trường hợp Left-Left
            if (getBalance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        // Nếu cây bị mất cân bằng về trái
        else if (balance < -1) {
            // Nếu là trường hợp Right-Right
            if (getBalance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }

        return node;
    }

    // Quay phải (Right Rotation)
    private TreeNode<K, E> rotateRight(TreeNode<K, E> y) {
        TreeNode<K, E> x = y.getLeft();
        TreeNode<K, E> T2 = x.getRight();

        // Thực hiện quay phải
        x.setRight(y);
        y.setLeft(T2);

        return x;
    }

    // Quay trái (Left Rotation)
    private TreeNode<K, E> rotateLeft(TreeNode<K, E> x) {
        TreeNode<K, E> y = x.getRight();
        TreeNode<K, E> T2 = y.getLeft();

        // Thực hiện quay trái
        y.setLeft(x);
        x.setRight(T2);

        return y;
    }

    public void insert(K key, E data) {
        root = insert(root, key, data);
    }

    private TreeNode<K, E> insert(TreeNode<K, E> root, K key, E data) {
        if (root == null) {
            root = new TreeNode<>(key, data);
            return root;
        }
        int compare = key.compareTo(root.getKey());
        if (compare < 0) {
            root.setLeft(insert(root.getLeft(), key, data));
        } else if (compare > 0) {
            root.setRight(insert(root.getRight(), key, data));
        } else {
            root.getData().add(data);
        }

        return balanceNode(root);
    }

    public TreeNode<K, E> root() {
        return root;
    }

    public ArrayList<E> search(K key) {
        return search(key, root);
    }

    public ArrayList<E> search(K key, TreeNode<K, E> root) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.getKey()) < 0) {
            return search(key, root.getLeft());
        } else if (key.compareTo(root.getKey()) > 0) {
            return search(key, root.getRight());
        }
        return root.getData();
    }

    public E findMin() {
        return findMin(root).getData().get(0);
    }

    private TreeNode<K, E> findMin(TreeNode<K, E> root) {
        if (root.getLeft() != null) {
            return findMin(root.getLeft());
        }
        return root;
    }

    public TreeNode<K, E> delete(TreeNode<K, E> node) {
        return deleteInSubTree(root, node);
    }

    public TreeNode<K, E> delete(K key, E data) {
        return deleteInSubTree(root, new TreeNode<>(key, data));
    }

    public TreeNode<K, E> delete(K key){
        return deleteInSubTree(root, new TreeNode<>(key, null));
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

    public void print() {
        printSubTree(root);
        System.out.println();
    }

    public void printSubTree(TreeNode<K, E> p) {
        if (p != null) {
            printSubTree(p.getLeft());
            System.out.print(p.getData() + " ");
            printSubTree(p.getRight());
        }
    }

    @Override
    public String toString() {
        return treeString(root);
    }

    private String treeString(TreeNode<K, E> p) {
        if (p != null) {
            return treeString(p.getLeft()) + " " + p + " " + treeString(p.getRight());
        }
        return "";
    }
}
