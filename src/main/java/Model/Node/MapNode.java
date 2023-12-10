package Model.Node;

/**
 * Represents a node in a map.
 *
 * @param <K> The type of the key associated with the node.
 * @param <E> The type of the data stored in the node.
 */
public class MapNode<K, E> {
	private K key;
	private E data;

	/**
	 * Constructs a new map node with the specified key and data.
	 *
	 * @param key  The key associated with the node.
	 * @param data The data stored in the node.
	 */
	public MapNode(K key, E data) {
		this.key = key;
		this.data = data;
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
	public E getData() {
		return data;
	}

	/**
	 * Sets the data to be stored in the node.
	 *
	 * @param data The new data to be stored in the node.
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Returns a string representation of the map node.
	 *
	 * @return A string representation of the map node.
	 */
	@Override
	public String toString() {
		return key + ":" + data;
	}
}
