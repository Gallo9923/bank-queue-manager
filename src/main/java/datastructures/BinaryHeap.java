package datastructures;

/* Generic Min/Max Binary Heap
* for /r/javaexamples
* 
*/
import java.util.Arrays;

@SuppressWarnings("unchecked")

/**
 * Creates an array-based binary heap. 
 *
 */
public abstract class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
	private static final int DEFAULT_CAPACITY = 10;
	protected T[] heap;
	protected int length;

	/**
	 * Default Constructor
	 * <p>
	 * default capacity of 9 (0 index is not used)
	 * <p>
	 */
	public BinaryHeap() {
		heap = (T[]) new Comparable[DEFAULT_CAPACITY];
		length = 0;
	}
	
	/**
	 * Constructor - takes an array of type T and a boolean for min/max
	 * 
	 * @param array T[] array
	 * @param min   boolean true = min heap, false = max heap
	 */
	public BinaryHeap(T[] array) {
		heap = (T[]) new Comparable[DEFAULT_CAPACITY];
		length = 0;

		// add each element to the heap
		for (T each : array) {
			add(each);
		}
	}

	public BinaryHeap(int initialCapacity) {
		heap = (T[]) new Comparable[initialCapacity];
		length = 0;
	}
	
	abstract protected void bubbleUp();
	abstract protected void bubbleDown();
	
	/**
	 * get the heap array note: can cause casting issues
	 * 
	 * @return Array of type T[]
	 */
	public T[] getHeap() {
		return Arrays.copyOfRange(heap, 1, length + 1);
	}

	/**
	 * adds a generic type T to heap
	 * <p>
	 * percolates down the tree
	 * 
	 * @param value type T value
	 */
	public void add(T value) {
		// resize if needed
		if (this.length >= heap.length - 1) {
			heap = this.resize();
		}

		length++;
		heap[length] = value;
		bubbleUp();
	}

	/**
	 * Removes min or max item from heap
	 * <p>
	 * re-heapifies
	 * 
	 * @return value of T that is minimum or maximum value in heap
	 */
	public T remove() {
		T result = peek();

		swap(1, length);
		heap[length] = null;
		length--;

		bubbleDown();

		return result;
	}

	/**
	 * Remove specific object from heap
	 * 
	 * @param value type T
	 * @return true if found and removed
	 */
	public boolean remove(T value) {
		for (int i = 0; i < heap.length; i++) {
			if (value.equals(heap[i])) {
				System.out.println(i);
				swap(i, length);
				heap[length] = null;
				length--;
				bubbleDown();
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes min or max item from heap same as <code>remove()</code> but does not
	 * throw exception on empty
	 * <p>
	 * re-heapifies
	 * 
	 * @return value of T that is minimum or maximum value in heap; or
	 *         <code>null</code> if empty
	 */
	public T poll() {
		if (isEmpty())
			return null;

		T result = peek();

		swap(1, length);
		heap[length] = null;
		length--;

		bubbleDown();

		return result;
	}

	/**
	 * Checks if heap is empty
	 * 
	 * @return <code>true</code> if empty
	 */
	public boolean isEmpty() {
		return length == 0;
	}

	/**
	 * returns min/max value without removing it
	 * 
	 * @return value type T
	 * @throws IllegalStateException if empty
	 */
	public T peek() {
		if (isEmpty())
			throw new IllegalStateException();
		return heap[1];
	}

	/**
	 * Length/size of heap
	 * 
	 * @return int size of heap
	 */
	public int size() {
		return length;
	}

	/**
	 * Add DEFAULT_CAPACITY to length of <code>heap</code> array
	 * 
	 * @return new array of type T
	 */
	private T[] resize() {
		// add 10 to array capacity
		return Arrays.copyOf(heap, heap.length + DEFAULT_CAPACITY);
	}
	
	/**
	 * if child has a parent
	 * 
	 * @param i integer - index
	 * @return true if index > 1
	 */
	protected boolean hasParent(int i) {
		return i > 1;
	}

	/**
	 * Get left index mathematically
	 * 
	 * @param i index
	 * @return index of left node from index i
	 */
	protected int leftIndex(int i) {
		return i * 2;
	}

	/**
	 * Get right index mathematically
	 * 
	 * @param i index
	 * @return index of right node from index i
	 */
	protected int rightIndex(int i) {
		return i * 2 + 1;
	}

	/**
	 * Test to see if node has left child
	 * 
	 * @param i index
	 * @return true if it does
	 */
	protected boolean hasLeftChild(int i) {
		return leftIndex(i) <= length;
	}

	/**
	 * Test to see if node has right child
	 * 
	 * @param i index
	 * @return true if it does
	 */
	protected boolean hasRightChild(int i) {
		return rightIndex(i) <= length;
	}

	/**
	 * get index of parent from child node
	 * 
	 * @param i index
	 * @return index of parent
	 */
	protected int parentIndex(int i) {
		return i / 2;
	}

	/**
	 * get parent value
	 * 
	 * @param i index
	 * @return value of type T
	 */
	protected T parent(int i) {
		return heap[parentIndex(i)];
	}

	/**
	 * Swap two values in heap
	 * 
	 * @param index1 int first index
	 * @param index2 int second index
	 */
	protected void swap(int index1, int index2) {
		T temp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = temp;
	}

	/**
	 * Overridden toString method
	 * 
	 * @return String all values in heap without null values
	 */
	@Override
	public String toString() {
		String retval = "";
		for (T each : heap) {
			if (each != null)
				retval += each + " : ";
		}
		return retval + "\n";

	}
}