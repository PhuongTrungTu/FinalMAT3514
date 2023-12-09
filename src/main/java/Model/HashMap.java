package Model;

import Model.Node.MapNode;

/**
 * An implementation of a simple HashMap using an ArrayList of MapNode.
 *
 * @param <K> the type of keys in the map
 * @param <E> the type of values in the map
 */
public class HashMap<K, E> {
	private ArrayList<MapNode<K, E>> container = new ArrayList<>();

	/**
	 * Constructs an empty HashMap.
	 */
	public HashMap() {

	}

	/**
	 * Returns the number of key-value pairs in the map.
	 *
	 * @return the number of key-value pairs in the map
	 */
	public int size() {
		return container.size();
	}

	/**
	 * Retrieves the value associated with the specified index in the map.
	 *
	 * @param index the index of the key-value pair to retrieve
	 * @return the value associated with the specified index
	 */
	public E get(int index) {
		return container.get(index).getData();
	}

	public E get(K key){
		for (MapNode<K, E> node: container){
			if (node.getKey().equals(key)){
				return node.getData();
			}
		}

		throw new NullPointerException(key + " not in map");
	}

	/**
	 * Adds a key-value pair to the map.
	 *
	 * @param field the key for the key-value pair
	 * @param data  the value for the key-value pair
	 */
	public void put(K field, E data) {
		container.add(new MapNode<>(field, data));
	}

	/**
	 * Returns a string representation of the HashMap.
	 *
	 * @return a string representation of the HashMap
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("{");
		for (int i = 0; i < container.size(); i++) {
			result.append(container.get(i).toString());
			if (i < container.size() - 1) {
				result.append(",");
			}
		}
		return result + "}";
	}

	public boolean containsKey(K task) {
		for (MapNode<K, E> node: container){
			if (node.getKey().equals(task)){
				return true;
			}
		}
		return false;
	}
}
