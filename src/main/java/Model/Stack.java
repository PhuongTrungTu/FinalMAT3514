package Model;

import Model.Node.StackNode;

public class Stack<E>{

    private StackNode<E> head = null;
    private int size = 0;

    public E getData(StackNode<E> stackNode){
        return stackNode.getElement();
    }

    public StackNode<E>  getNext(StackNode<E>  stackNode){
        return stackNode.getNext();
    }

    public void push(E element) {
        StackNode<E>  newStackNode = new StackNode<> (element);
        if (head != null){
            newStackNode.setNext(head);
        }
        head = newStackNode;
        size++;
    }


    public E pop() {
        if (head == null){
            throw new NullPointerException();
        }
        E data = head.getElement();
        head = head.getNext();
        size--;
        return data;
    }


    public boolean isEmpty() {
        return head == null;
    }


    public E top() {
        return head.getElement();
    }


    public String toString() {
        if (head == null){
            return "";
        }
        StringBuilder result    = new StringBuilder();
        StackNode<E>      stackNode = head;
        while (stackNode != null){
            result.insert(0, stackNode.getElement() + ", ");
            stackNode = stackNode.getNext();
        }
        return result.toString();
    }


    public int size() {
        return size;
    }

}