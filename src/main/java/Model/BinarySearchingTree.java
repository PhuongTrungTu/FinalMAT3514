package Model;

import Model.Node.TreeNode;

/**
 * Represents a Binary Search Tree.
 *
 * @param <K> The type of the key associated with the tree nodes, must extend Comparable.
 * @param <E> The type of the data stored in the tree nodes.
 */
public class BinarySearchingTree<K extends Comparable<K>, E> {
	private TreeNode<K,E> root;

	/**
	 * Gets the height of a node in the tree.
	 *
	 * @param node The node for which to calculate the height.
	 * @return The height of the node.
	 */
	private int height(TreeNode<K,E> node) {
		if (node == null){
			return 0;
		}
		return 1 + Math.max(height(node.getLeft()) , height(node.getRight()));
	}

	/**
	 * Gets the balance factor of a node in the tree.
	 *
	 * @param node The node for which to calculate the balance factor.
	 * @return The balance factor of the node.
	 */
	private int getBalance(TreeNode<K,E> node) {
		if (node == null){
			return 0;
		}
		return height(node.getLeft()) - height(node.getRight());
	}

	/**
	 * Balances a specific node in the tree.
	 *
	 * @param node The node to balance.
	 * @return The balanced node.
	 */
	private TreeNode<K,E> balanceNode(TreeNode<K,E> node) {
		int balance = getBalance(node);

		// If the tree is unbalanced to the right
		if (balance > 1){
			// If it's a Left-Left case
			if (getBalance(node.getLeft()) < 0){
				node.setLeft(rotateLeft(node.getLeft()));
			}
			return rotateRight(node);
		}
		// If the tree is unbalanced to the left
		else if (balance < - 1){
			// If it's a Right-Right case
			if (getBalance(node.getRight()) > 0){
				node.setRight(rotateRight(node.getRight()));
			}
			return rotateLeft(node);
		}

		return node;
	}

	/**
	 * Performs a right rotation on a node.
	 *
	 * @param y The node to rotate.
	 * @return The new root after rotation.
	 */
	private TreeNode<K,E> rotateRight(TreeNode<K,E> y) {
		TreeNode<K,E> x  = y.getLeft();
		TreeNode<K,E> T2 = x.getRight();

		// Perform the right rotation
		x.setRight(y);
		y.setLeft(T2);

		return x;
	}

	/**
	 * Performs a left rotation on a node.
	 *
	 * @param x The node to rotate.
	 * @return The new root after rotation.
	 */
	private TreeNode<K,E> rotateLeft(TreeNode<K,E> x) {
		TreeNode<K,E> y  = x.getRight();
		TreeNode<K,E> T2 = y.getLeft();

		// Perform the left rotation
		y.setLeft(x);
		x.setRight(T2);

		return y;
	}

	/**
	 * Inserts a key-value pair into the tree.
	 *
	 * @param key  The key to insert.
	 * @param data The data associated with the key.
	 */
	public void insert(K key , E data) {
		root = insert(root , key , data);
	}

	/**
	 * Inserts a key-value pair into a specific subtree.
	 *
	 * @param root The root of the subtree.
	 * @param key  The key to insert.
	 * @param data The data associated with the key.
	 * @return The root of the updated subtree.
	 */
	private TreeNode<K,E> insert(TreeNode<K,E> root , K key , E data) {
		if (root == null){
			root = new TreeNode<>(key , data);
			return root;
		}
		int compare = key.compareTo(root.getKey());
		if (compare < 0){
			root.setLeft(insert(root.getLeft() , key , data));
		} else if (compare > 0){
			root.setRight(insert(root.getRight() , key , data));
		} else{
			root.getData().add(data);
		}

		return balanceNode(root);
	}

	/**
	 * Gets the root of the tree.
	 *
	 * @return The root of the tree.
	 */
	public TreeNode<K,E> root() {
		return root;
	}

	/**
	 * Searches for data associated with a specific key in the tree.
	 *
	 * @param key The key to search for.
	 * @return The data associated with the key, or null if the key is not found.
	 */
	public ArrayList<E> search(K key) {
		return search(key , root);
	}

	/**
	 * Searches for data associated with a specific key in a specific subtree.
	 *
	 * @param key  The key to search for.
	 * @param root The root of the subtree.
	 * @return The data associated with the key, or null if the key is not found.
	 */
	public ArrayList<E> search(K key , TreeNode<K,E> root) {
		if (root == null){
			return null;
		}
		if (key.compareTo(root.getKey()) < 0){
			return search(key , root.getLeft());
		} else if (key.compareTo(root.getKey()) > 0){
			return search(key , root.getRight());
		}
		return root.getData();
	}

	/**
	 * Finds the minimum data in the tree.
	 *
	 * @return The minimum data in the tree.
	 */
	public E findMin() {
		return findMin(root).getData().get(0);
	}

	/**
	 * Finds the node with the minimum key in a specific subtree.
	 *
	 * @param root The root of the subtree.
	 * @return The node with the minimum key.
	 */
	private TreeNode<K,E> findMin(TreeNode<K,E> root) {
		if (root.getLeft() != null){
			return findMin(root.getLeft());
		}
		return root;
	}

	/**
	 * Deletes a node from the tree.
	 *
	 * @param node The node to delete.
	 * @return The root of the updated tree.
	 */
	public TreeNode<K,E> delete(TreeNode<K,E> node) {
		return deleteInSubTree(root , node);
	}

	/**
	 * Deletes a key-value pair from the tree.
	 *
	 * @param key  The key to delete.
	 * @param data The data associated with the key to delete.
	 * @return The root of the updated tree.
	 */
	public TreeNode<K,E> delete(K key , E data) {
		ArrayList<E> container = search(key);
		if (container.size() == 0){
			return null;
		} else if (container.size() == 1){
			return delete(key);
		}
		for (E temp : container){
			if (temp.equals(data)){
				container.remove(data);
				return new TreeNode<>(key , data);
			}
		}
		return null;
	}

	/**
	 * Deletes a key from the tree.
	 *
	 * @param key The key to delete.
	 * @return The root of the updated tree.
	 */
	public TreeNode<K,E> delete(K key) {
		return deleteInSubTree(root , new TreeNode<>(key , null));
	}

	/**
	 * Deletes a node from a specific subtree.
	 *
	 * @param root The root of the subtree.
	 * @param node The node to delete.
	 * @return The root of the updated subtree.
	 */
	private TreeNode<K,E> deleteInSubTree(TreeNode<K,E> root , TreeNode<K,E> node) {
		if (root == null){
			return null;
		}

		int compareResult = node.getKey().compareTo(root.getKey());

		if (compareResult < 0){
			root.setLeft(deleteInSubTree(root.getLeft() , node));
		} else if (compareResult > 0){
			root.setRight(deleteInSubTree(root.getRight() , node));
		} else{
			if (root.getLeft() == null){
				return root.getRight();
			} else if (root.getRight() == null){
				return root.getLeft();
			}

			root.setData(findMin(root.getRight()).getData());
			root.setRight(deleteInSubTree(root.getRight() , findMin(root.getRight())));
		}

		return balanceNode(root);
	}

	/**
	 * Generates a string representation of the tree.
	 *
	 * @return A string representation of the tree.
	 */
	@Override
	public String toString() {
		return treeString(root);
	}

	/**
	 * Generates a string representation of a specific subtree.
	 *
	 * @param p The root of the subtree.
	 * @return A string representation of the subtree.
	 */
	private String treeString(TreeNode<K,E> p) {
		if (p != null){
			return treeString(p.getLeft()) + " " + p + " " + treeString(p.getRight());
		}
		return "";
	}
}
