package datastructures;

public class MaxHeap<T extends Comparable<T>> extends BinaryHeap<T> {
	
	public MaxHeap(T[] array) {
		super(array);
	}
	
	public MaxHeap(int initialCapacity) {
		super(initialCapacity);
	}
	
	@Override
	protected void bubbleUp() {
		int index = length;
		while (hasParent(index) && (parent(index).compareTo(heap[index]) < 0)) {
			swap(index, parentIndex(index));
			index = parentIndex(index);
		}
	}

	@Override
	protected void bubbleDown() {
		int index = 1;
		while (hasLeftChild(index)) {
			// find larger of child values
			int larger = leftIndex(index);
			if (hasRightChild(index) && heap[leftIndex(index)].compareTo(heap[rightIndex(index)]) < 0) {
				larger = rightIndex(index);
			}
			if (heap[index].compareTo(heap[larger]) < 0) {
				swap(index, larger);
			} else
				break;

			index = larger;
		}
	}

}
