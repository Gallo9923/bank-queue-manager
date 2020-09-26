package datastructures;

public interface PriorityQueue<T extends Comparable<T>> {
	
	public int size();
	
	public T peek();
	
	public T poll();
	
	public boolean isEmpty();
	
	public void add(T element);
}
