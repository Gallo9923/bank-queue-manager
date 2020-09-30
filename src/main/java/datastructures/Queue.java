package datastructures;

import java.util.Iterator;

/**
 * @author Sebastián García Acosta
 *
 * @param <E>, any type
 */
public class Queue<E> implements IQueue<E>, Iterable<E> {

	protected class Node<T> {

		private T data;
		private Node<T> prev;

		public Node(T data) {
			this.data = data;
		}
	}

	public Node<E> back;
	public Node<E> front;
	private int size;

	public Queue() {
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public Queue(E... elements) {
		for (E e : elements)
			enqueue(e);
	}

	public void enqueue(E data) {
		Node<E> newNode = new Node<E>(data);
		if (size == 0) {
			front = newNode;
			back = newNode;
		} else {
			back.prev = newNode;
			back = newNode;
		}
		size++;
	}

	@SuppressWarnings("unchecked")
	public void enqueue(E... data) {
		for(E e : data)
			enqueue(e);
	}
	
	public E front() {
		return (front == null) ? null : front.data;
	}

	public E dequeue() {
		Node<E> firstin = front;
		if (firstin != null) {
			size--;
			this.front = front.prev;
			return firstin.data;
		} else
			return null;
	}

	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public Iterator<E> iterator() {
		return new QueueIterator<E>(front);
	}

	@SuppressWarnings("hiding")
	protected class QueueIterator<E> implements Iterator<E> {

		private Node<E> currFront;

		public QueueIterator(Node<E> front) {
			this.currFront = front;
		}

		@Override
		public boolean hasNext() {
			return currFront != null;
		}

		@Override
		public E next() {
			E next = currFront.data;
			currFront = currFront.prev;
			return next;
		}
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		String repr = "";
		int i = 0;
		for(E e : this) {
			repr += e + ((i == size -1 ) ? "": " ");
			i++;
		}
		return repr;
	}
}