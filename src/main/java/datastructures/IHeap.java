package datastructures;

public interface IHeap<T> {
	public int parent(int pos);
	public int leftChild(int pos);
	public int rightChild(int pos);
	public boolean isLeaf(int pos);
	public void swap(int fpos, int spos);
	public void maxHeapify(int pos);
	public void insert(T element);
	public T extractMax();
}
