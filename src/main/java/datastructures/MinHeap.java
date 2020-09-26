package datastructures;

public class MinHeap<T extends Comparable<T>> extends BinaryHeap<T> {

	public MinHeap(T[] array) {
		super(array);
	}
	
	public MinHeap(int initialCapacity) {
		super(initialCapacity);
	}
	
	@Override
	protected void bubbleUp() {
		int index = length;
		while (hasParent(index) && (parent(index).compareTo(heap[index]) > 0)) {
			swap(index, parentIndex(index));
			index = parentIndex(index);
		}
	}

	@Override
	protected void bubbleDown() {
		int index = 1;
		while (hasLeftChild(index)) {
		// find smaller of child values
		int smaller = leftIndex(index);
		if (hasRightChild(index) && heap[leftIndex(index)].compareTo(heap[rightIndex(index)]) > 0) {
			smaller = rightIndex(index);
		}
		if (heap[index].compareTo(heap[smaller]) > 0) {
			swap(index, smaller);
		} else
			break;

		index = smaller;
	}

	}

}
