package Model.Node;

public class TreeNode<K extends Comparable<K>, E> {
	K key;
	E data;
	TreeNode<K, E> left;
	TreeNode<K, E> right;

	public TreeNode(K key , E data) {
		this.key = key;
		this.data = data;
	}

	public TreeNode(K key , E data , TreeNode<K,E> left , TreeNode<K,E> right) {
		this.key = key;
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public TreeNode<K, E>  getLeft() {
		return left;
	}

	public void setLeft(TreeNode<K, E> left) {
		this.left = left;
	}

	public TreeNode<K, E>  getRight() {
		return right;
	}

	public void setRight(TreeNode<K, E>  right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "{Key: " + key + "Data: " + data + "}";
	}
}
