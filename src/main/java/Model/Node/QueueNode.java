package Model.Node;

public class QueueNode<K, E> {
	private K key;
	private E element;
	private QueueNode<K,E> next;

	public QueueNode(K k , E e) {
		key = k;
		element = e;
		next = null;
	}

	public QueueNode(K key , E element , QueueNode<K,E> next) {
		this.key = key;
		this.element = element;
		this.next = next;
	}

	public QueueNode<K,E> getNext() {
		return next;
	}

	public void setNext(QueueNode<K,E> next) {
		this.next = next;
	}


	public K getKey() {
		return key;
	}


	public E getValue() {
		return element;
	}

	@Override
	public String toString() {
		return key + ":" + element;
	}
}
