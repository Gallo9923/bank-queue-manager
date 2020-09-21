package datastructures;

import java.util.Iterator;

public class Stack<T> implements Iterable<T>, IStack<T> {
	
	@SuppressWarnings("hiding")
	protected class Node<T> {
		private T data;
		private Node<T> under;
		
		public Node(T data) {
			this.data = data;
		}
	}
	
	private Node<T> top;
	private int size;
	
	public Stack() {
		this.size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public Stack(T... elements) {
		this();
		for(T e : elements)
			push(e);
	}
	
	@Override
	public void push(T data) {
		size++;
		Node<T> newNode = new Node<>(data);
		if(top == null)
			top = newNode;
		else {
			newNode.under = top;
			top = newNode;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void pushAll(T... elements) {
		for(T e : elements) 
			push(e);
	}
	
	public void clear() {
		top = null;
	}
	
	@Override
	public T top() {
		return (top == null ) ? null: top.data;
	}

	@Override
	public T pop() {
		T returned = null;
		if(top != null) {
			returned = top.data;
			top = top.under;
			size--;
		}
		return returned;
	}
	
	
	
	public static <E> Stack<E> reverse(Stack<E> stack){
		Stack<E> solution = new Stack<>();
		for(E element: stack)
			solution.push(element);
		return solution;
	}


	@Override
	public Iterator<T> iterator() {
		return new StackIterator<>(top);
	}

	@SuppressWarnings("hiding")
	class StackIterator<T> implements Iterator<T> {

		private Node<T> curr;
		
		public StackIterator(Node<T> top) {
			this.curr = top;
		}
		
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public T next() {
			T next = curr.data;
			curr = curr.under;
			return next;
		}
		
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return top== null;
	}
}
