package utils.sorting;

import java.util.Comparator;
import java.util.List;

class QuickSorter<T> implements Sortable<T>{

	private Comparator<T> comp;
	
	public QuickSorter(Comparator<T> comp) {
		this.comp = comp;
	}
	
	public void sort(List<T> list) {
		sort(list, 0, list.size() - 1);		
	}

	public void sort(List<T> list, int low, int high) {
		if (low < high) {
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			int pi = partition(list, low, high);

			// Recursively sort elements before
			// partition and after partition
			sort(list, low, pi - 1);
		}
	}

	private int partition(List<T> list, int low, int high) {
		T pivot = list.get(high);
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than the pivot
			if (comp.compare(list.get(j), pivot) < 0) {
				i++;

				// swap arr[i] and arr[j]
				Sorter.swap(list, i, j);
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		Sorter.swap(list, i + 1, high);

		return i + 1;
	}	
}