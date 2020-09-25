package utils.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * @author Sebastián García Acosta
 *
 * @param <T>, any type.
 */
class QuickSorter<T> extends SortingAlgorithm<T> {
	
	public QuickSorter(Comparator<T> comp) {
		super(comp);
	}
	
	@Override
	public void sort(List<T> list) {
		sort(list, 0, list.size() - 1);		
	}
	
	/**
	 * Sorts the list using QuickSort from begin to end.<br>
	 * 
	 * <b>pre: </b> 0 <= begin < n
	 * <b>pre: </b> 0 <= end < n
	 * 
	 * @param list, {@link List<T>} list of type T and size n. 
	 * @param begin
	 * @param end
	 */
	public void sort(List<T> list, int begin, int end) {
		if (begin < end) {
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			int pi = partition(list, begin, end);

			// Recursively sort elements before
			// partition and after partition
			sort(list, begin, pi - 1);
			sort(list, pi + 1, end);
		}
	}

	/**
	 * Chooses the index based on which sorting will be made.<br>
	 * 
	 * <b>pre: </b> 0 <= begin < n
	 * <b>pre: </b> 0 <= end < n
	 * 
	 * @param list, {@link List<T>} list of type T and size n. 
	 * @param begin, int, the index from which the partition will be inferred. 
	 * @param end, int,  the index until which the patition will be inferred.
	 * @return
	 */
	private int partition(List<T> list, int begin, int end) {
		T pivot = list.get(end);
		int i = (begin - 1); // index of smaller element
		
		for (int j = begin; j < end; j++) {
			// If current element is smaller than the pivot
			if (comp.compare(list.get(j), pivot) < 0) {
				i++;

				// swap arr[i] and arr[j]
				T temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}

		// swap list[i+1] and list[high] (or pivot)
		T temp = list.get(i + 1);
		list.set(i+1, list.get(end));
		list.set(end, temp);
		return i + 1;
	}
	
}