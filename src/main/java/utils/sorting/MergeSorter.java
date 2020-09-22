package utils.sorting;

import java.util.Comparator;
import java.util.List;

class MergeSorter<E> implements Sortable<E> {

	private Comparator<E> comp;
	
 	public MergeSorter(Comparator<E> comp) {
		this.comp = comp;
	}
	
	@Override
	public void sort(List<E> list) {
		mergeSort(list, 0, list.size() - 1);
	}
	
	private void mergeSort(List<E> list, int l, int r) {
		if (l < r) {
			// Same as (l+r)/2, but avoids overflow for
			// large l and h
			int m = l + (r - l) / 2;

			// Sort first and second halves
			mergeSort(list, l, m);
			mergeSort(list, m + 1, r);

			Sorter.merge(list, l, m, r, comp);
		}
	}
}
