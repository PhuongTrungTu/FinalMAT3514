package Model.Node;

import Model.ArrayList;

public class TreeNode<K extends Comparable<K>, E> {
	private K key;
	private ArrayList<E> data = new ArrayList<>();
	private TreeNode<K, E> left;
	private TreeNode<K, E> right;

	public TreeNode(K key, ArrayList<E> data) {
		this.key = key;
		this.data = data;
	}

	public TreeNode(K key, E data) {
		this.key = key;
		this.data.add(data);
	}

	public TreeNode(K key, ArrayList<E> data, TreeNode<K, E> left, TreeNode<K, E> right) {
		this.key = key;
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public TreeNode(K key, ArrayList<E> data, TreeNode<K, E> left) {
		this.key = key;
		this.data = data;
		this.left = left;
	}

	public TreeNode(TreeNode<K, E> right, K key, ArrayList<E> data) {
		this.key = key;
		this.data = data;
		this.right = right;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public ArrayList<E> getData() {
		return data;
	}

	public void setData(ArrayList<E> data) {
		this.data = data;
	}

	public TreeNode<K, E> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<K, E> left) {
		this.left = left;
	}

	public TreeNode<K, E> getRight() {
		return right;
	}

	public void setRight(TreeNode<K, E> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "{Key: " + key + ", Data: " + data + "}";
	}
}
