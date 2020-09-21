package datastructures;

public interface IStack<E> {
	public E top();
	public void push(E data);
	public E pop();
	public boolean isEmpty();
	public int size();
}
