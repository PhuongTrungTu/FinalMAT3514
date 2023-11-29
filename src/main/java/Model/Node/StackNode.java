package Model.Node;

public class StackNode<E> {
	E element;
	StackNode<E> next;

	public StackNode(E element) {
		this.element = element;
	}

	public StackNode(E element , StackNode<E> next) {
		this.element = element;
		this.next = next;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public StackNode<E> getNext() {
		return next;
	}

	public void setNext(StackNode<E> next) {
		this.next = next;
	}
}
