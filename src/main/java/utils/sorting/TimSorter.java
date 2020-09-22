package utils.sorting;

import java.util.Comparator;
import java.util.List;

class TimSorter<E> implements Sortable<E> {
	
	private Comparator<E> comp;
	private int run;
	
	public TimSorter(Comparator<E> comp, int run) {
		this.comp = comp;
		this.run = run;
	}
	
	public TimSorter(Comparator<E> comp) {
		this(comp, 32);
	}
	
	public void sort(List<E> list) {
		int n = list.size();

		// Sort individual subarrays of size RUN
		for (int i = 0; i < n; i += run) {
			insertionSort(list, i, Math.min((i + 31), (n - 1)), comp);
		}

		// start merging from size RUN (or 32). It will merge
		// to form size 64, then 128, 256 and so on ....
		for (int size = run; size < n; size = 2 * size) {

			// pick starting point of left sub array. We
			// are going to merge arr[left..left+size-1]
			// and arr[left+size, left+2*size-1]
			// After every merge, we increase left by 2*size
			for (int left = 0; left < n; left += 2 * size) {

				// find ending point of left sub array
				// mid+1 is starting point of right sub array
				int mid = left + size - 1;
				int right = Math.min((left + 2 * size - 1), (n - 1));

				// merge sub array arr[left.....mid] &
				// arr[mid+1....right]
				Sorter.merge(list, left, mid, right, comp);
			}
		}

	}
	
	private static <E> void insertionSort(List<E> list, int left, int right, Comparator<E> comp) {
		for (int i = left + 1; i <= right; i++) {
			E temp = list.get(i);
			int j = i - 1;
			while (j >= left && comp.compare(list.get(j), temp) > 0) {
				list.set(j + 1, list.get(j));
				j--;
			}
			list.set(j + 1, temp);
		}
	}
}