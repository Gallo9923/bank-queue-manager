package datastructures;

public class Heap<T extends Comparable<T>> implements IHeap<T>{
	
	class Entry<E> implements Comparable<Entry>{
		private T value;
		public Entry(T value) {
			this.value = value;
		}
		public T getValue() {
			return value;
		}
		@Override
		public int compareTo(Entry o) {
			return value.compareTo((T) o.getValue());
		}
	}
	
	private Entry<T>[] Heap;
	private int size;
	private int maxsize;
	
	public Heap(int maxsize) {
		this.maxsize = maxsize;
		size = 0;
		Heap = new Entry[maxsize+1];
		Heap[0] = new Entry(Integer.MAX_VALUE);
	}
	
	public int parent(int pos) {
		return pos / 2;
	}
	
	public int leftChild(int pos) {
		return pos * 2;
	}
	
	public int rightChild(int pos) {
		return (pos * 2) + 1;
	}
	
	public boolean isLeaf(int pos) {
		if (pos >= (size/2) && pos <= size)
			return true;
		return false;
	}
	
	public void swap(int fpos, int spos) {
		Entry temp;
		temp = Heap[fpos];
		Heap[fpos] = Heap[spos];
		Heap[spos] = temp;
	}
	
	public void maxHeapify(int pos) {
		if (isLeaf(pos))
			return;
		
		if (Heap[pos].compareTo(Heap[leftChild(pos)]) < 0 || Heap[pos].compareTo(Heap[rightChild(pos)]) < 0) {
			
			if (Heap[leftChild(pos)].compareTo(Heap[rightChild(pos)]) > 0) { 
                swap(pos, leftChild(pos)); 
                maxHeapify(leftChild(pos)); 
            }
            else { 
                swap(pos, rightChild(pos)); 
                maxHeapify(rightChild(pos)); 
            }
			
		}
	}
	
	public void insert(T element) { 
        Heap[++size] = new Entry<T>(element); 

        int current = size; 
        while (Heap[current].compareTo(Heap[parent(current)]) > 0) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    }
	
	public void print() { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + Heap[i].getValue() + " LEFT CHILD : " + 
                      Heap[2 * i].getValue() + " RIGHT CHILD :" + Heap[2 * i + 1].getValue()); 
            System.out.println(); 
        } 
    }
	
	public T extractMax() { 
        T popped = (T) Heap[1].getValue(); 
        Heap[1] = Heap[size--]; 
        maxHeapify(1); 
        return popped; 
    }
}
