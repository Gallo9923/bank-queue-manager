package datastructures;

public interface IQueue<E> {
	public boolean isEmpty();
	public E front();
	public E dequeue();
	public void enqueue(E data);
	public int size();
}
