package Model.Node;

import Model.ArrayList;

/**
 * Represents a node in a binary tree.
 *
 * @param <K> The type of the key associated with the node, must extend Comparable.
 * @param <E> The type of the data stored in the node.
 */
public class TreeNode<K extends Comparable<K>, E> {
	private K key;
	private ArrayList<E> data = new ArrayList<>();
	private TreeNode<K, E> left;
	private TreeNode<K, E> right;

	/**
	 * Constructs a new tree node with the specified key and data.
	 *
	 * @param key  The key associated with the node.
	 * @param data The data stored in the node.
	 */
	public TreeNode(K key, ArrayList<E> data) {
		this.key = key;
		this.data = data;
	}

	/**
	 * Constructs a new tree node with the specified key and data.
	 * Initializes the data as a new ArrayList containing the provided data.
	 *
	 * @param key  The key associated with the node.
	 * @param data The data stored in the node.
	 */
	public TreeNode(K key, E data) {
		this.key = key;
		this.data.add(data);
	}

	/**
	 * Constructs a new tree node with the specified key, data, left child, and right child.
	 *
	 * @param key   The key associated with the node.
	 * @param data  The data stored in the node.
	 * @param left  The left child of the node.
	 * @param right The right child of the node.
	 */
	public TreeNode(K key, ArrayList<E> data, TreeNode<K, E> left, TreeNode<K, E> right) {
		this.key = key;
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * Constructs a new tree node with the specified key, data, and left child.
	 *
	 * @param key  The key associated with the node.
	 * @param data The data stored in the node.
	 * @param left The left child of the node.
	 */
	public TreeNode(K key, ArrayList<E> data, TreeNode<K, E> left) {
		this.key = key;
		this.data = data;
		this.left = left;
	}

	/**
	 * Constructs a new tree node with the specified right child, key, and data.
	 *
	 * @param right The right child of the node.
	 * @param key   The key associated with the node.
	 * @param data  The data stored in the node.
	 */
	public TreeNode(TreeNode<K, E> right, K key, ArrayList<E> data) {
		this.key = key;
		this.data = data;
		this.right = right;
	}

	/**
	 * Gets the key associated with the node.
	 *
	 * @return The key associated with the node.
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Sets the key associated with the node.
	 *
	 * @param key The new key to be associated with the node.
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * Gets the data stored in the node.
	 *
	 * @return The data stored in the node.
	 */
	public ArrayList<E> getData() {
		return data;
	}

	/**
	 * Sets the data to be stored in the node.
	 *
	 * @param data The new data to be stored in the node.
	 */
	public void setData(ArrayList<E> data) {
		this.data = data;
	}

	/**
	 * Gets the left child of the node.
	 *
	 * @return The left child of the node.
	 */
	public TreeNode<K, E> getLeft() {
		return left;
	}

	/**
	 * Sets the left child of the node.
	 *
	 * @param left The new left child of the node.
	 */
	public void setLeft(TreeNode<K, E> left) {
		this.left = left;
	}

	/**
	 * Gets the right child of the node.
	 *
	 * @return The right child of the node.
	 */
	public TreeNode<K, E> getRight() {
		return right;
	}

	/**
	 * Sets the right child of the node.
	 *
	 * @param right The new right child of the node.
	 */
	public void setRight(TreeNode<K, E> right) {
		this.right = right;
	}

	/**
	 * Returns a string representation of the tree node.
	 *
	 * @return A string representation of the tree node.
	 */
	@Override
	public String toString() {
		return "{Key: " + key + ", Data: " + data + "}";
	}
}
