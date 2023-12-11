package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Iterator;

/**
 * An implementation of a dynamic array list.
 *
 * @param <E> the type of elements in the list
 */
public class ArrayList<E> implements Iterable<E> {
	private E[] containers = ((E[]) new Object[100]);
	private int size = 0;

	/**
	 * Constructs an empty ArrayList with an initial capacity of 100.
	 */
	public ArrayList() {
	}

	/**
	 * Constructs an ArrayList with the specified array of elements.
	 * Automatically expands the capacity if needed.
	 *
	 * @param containers the array of elements to initialize the list
	 */
	public ArrayList(E[] containers) {
		this.containers = containers;
		size = containers.length;
		expand();
	}

	/**
	 * Static method to copy elements from the specified index to the end of the
	 * list.
	 *
	 * @param array the source ArrayList
	 * @param start the index from which to start copying
	 * @return a new ArrayList containing the copied elements
	 */
	public static ArrayList<?> copy(ArrayList<?> array , int start) {
		ArrayList<?> result = new ArrayList<>();
		for (int i = start; i < array.size; i++){
			result.addPrivate(array.get(i));
		}

		return result;
	}

	/**
	 * Expands the capacity of the list by creating a new array with
	 * 5 times the current size and copying the elements.
	 */
	private void expand() {
		E[] temp = ((E[]) new Object[size * 5]);
		System.arraycopy(containers , 0 , temp , 0 , size);
		containers = temp;
	}

	/**
	 * Adds an element to the end of the list. Automatically expands
	 * the capacity if needed.
	 *
	 * @param data the element to be added
	 */
	public void add(E data) {
		containers[size] = data;
		if (size >= containers.length - 26){
			expand();
		}
		size++;
	}

	/**
	 * Inserts an element at the specified index. Shifts the subsequent
	 * elements to the right. Automatically expands the capacity if needed.
	 *
	 * @param data  the element to be inserted
	 * @param index the index at which the element is to be inserted
	 */
	public void insert(E data , int index) {
		for (int i = size; i > index; i--){
			containers[i] = containers[i - 1];
		}
		containers[index] = data;
		size++;

		if (size >= containers.length - 26){
			expand();
		}
	}

	/**
	 * Removes the first occurrence of the specified element from the list.
	 *
	 * @param value the element to be removed
	 */
	public void remove(E value) {
		for (int i = 0; i < size; i++){
			if (containers[i].equals(value)){
				remove(i);
				i--;
			}
		}
	}

	/**
	 * Removes the element at the specified index from the list.
	 *
	 * @param index the index of the element to be removed
	 */
	public void remove(int index) {
		if ((index >= size || index < 0) && getClassName().equalsIgnoreCase("Integer")){
			remove(index);
		} else if ((index >= size || index < 0)){
			throw new IndexOutOfBoundsException();
		}
		for (int i = index; i < size; i++){
			containers[i] = containers[i + 1];
		}
		size--;
	}

	/**
	 * Returns the number of elements in the list.
	 *
	 * @return the number of elements in the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns a string representation of the list.
	 *
	 * @return a string representation of the list
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		for (int i = 0; i < size; i++){
			result.append(containers[i]);
			if (i < size - 1){
				result.append(", ");
			}
		}
		return result.append("]").toString();
	}

	/**
	 * Returns the element at the specified index.
	 *
	 * @param index the index of the element to be retrieved
	 * @return the element at the specified index
	 */
	public E get(int index) {
		return containers[index];
	}

	/**
	 * Returns the class name of the elements in the list.
	 *
	 * @return the class name of the elements in the list
	 */
	public String getClassName() {
		if (containers[0] == null){
			return "Components";
		}
		return containers[0].getClass().getSimpleName();
	}

	/**
	 * Sets the element at the specified index to the specified value.
	 *
	 * @param index the index of the element to be set
	 * @param data  the value to set
	 */
	public void set(int index , E data) {
		containers[index] = data;
	}

	/**
	 * Converts the list to an array.
	 *
	 * @return an array containing all the elements in the list
	 */
	@JsonProperty("List")
	public E[] toArray() {
		E[] result = (E[]) new Object[size];
		System.arraycopy(containers , 0 , result , 0 , size);
		return result;
	}

	/**
	 * Checks if the list contains the specified element.
	 *
	 * @param data the element to be checked
	 * @return true if the list contains the element, false otherwise
	 */
	public boolean contain(E data) {
		for (int i = 0; i < size; i++){
			if (containers[i].equals(data)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Creates a shallow copy of the list.
	 *
	 * @return a new ArrayList with the same elements as this list
	 */
	public ArrayList<E> copy() {
		E[] container = (E[]) (new Object[size]);
		System.arraycopy(this.containers , 0 , container , 0 , size);
		return new ArrayList<>(container);
	}

	/**
	 * Private method to add an element to the list without exposing it publicly.
	 *
	 * @param data the element to be added
	 */
	private void addPrivate(Object data) {
		if (size >= containers.length - 5){
			expand();
		}
		containers[size] = (E) data;
		size++;
	}

	/**
	 * Returns an iterator over the elements in the list.
	 *
	 * @return an iterator over the elements in the list
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private final E[] array = toArray();
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public E next() {
				index++;
				return array[index - 1];
			}
		};
	}

	/**
	 * Checks if the list is empty.
	 *
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Adds all elements from the specified ArrayList to this list.
	 *
	 * @param array the ArrayList containing elements to be added
	 * @return this ArrayList after adding the elements
	 */
	public ArrayList<E> addAll(ArrayList<E> array) {
		for (int i = 0; i < array.size; i++){
			add(array.get(i));
		}
		return this;
	}
}
