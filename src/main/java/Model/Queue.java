package Model;


import Model.Node.QueueNode;

public class Queue<K extends Comparable<K> , E >{
	private QueueNode<K ,E > head;
	private QueueNode<K ,E > tail;
	int size = 0;

	public int size() {
		return size++;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private int compare(K o1, K o2){
		return o1.compareTo(o2);
	}

	public void insert(K k , E e) {
		QueueNode<K, E> newNode = new QueueNode<>(k, e);
		if (head == null || compare(newNode.getKey(), head.getKey()) < 0) {
			newNode.setNext(head);
			head = newNode;
			if (tail == null) {
				tail = newNode;
			}
		} else if (compare(newNode.getKey(),tail.getKey()) >= 0) {
			tail.setNext(newNode);
			tail = newNode;
		} else {
			QueueNode<K, E> current = head;
			while (compare(newNode.getKey(),current.getNext().getKey()) >= 0) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}
		size++;
	}

	public QueueNode<K,E> removeMin() {
		QueueNode<K, E> min = head;
		head = head.getNext();
		size--;
		return min;
	}

	public QueueNode<K,E> min() {
		return head;
	}

	@Override
	public String toString() {
		StringBuilder   result = new StringBuilder();
		QueueNode<K, E> node   = head;
		while (node != null){
			result.append("(").append(node).append(")").append(", ");
			node = node.getNext();
		}
		return result.toString();
	}
}
