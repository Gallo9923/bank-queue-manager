package utils.sorting;

import java.util.Comparator;
import java.util.List;

public class InsertionSorter<E> implements Sortable<E> {
	
	private Comparator<E> comp;
	
	public InsertionSorter(Comparator<E> comp) {
		this.comp = comp;
	}
	
	@Override
	public void sort(List<E> list) {
		insertionSort(list, 0, list.size() - 1, comp);
	}
	
	private void insertionSort(List<E> list, int left, int right, Comparator<E> comp) {
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
