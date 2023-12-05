package Model.Node;

public class GraphNode<K, E> {
	K key;
	E data;

	public GraphNode(K key , E data) {
		this.key = key;
		this.data = data;
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

	@Override
	public String toString() {
		return "(" + key + ":" + data + ")";
	}
}
