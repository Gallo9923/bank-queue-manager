package utils.sorting;

import java.util.Comparator;
import java.util.List;

/**
 * Implementation of InsertionSort algorithm
 * 
 * @author Sebastián García Acosta
 * @param <E>, any type.
 */
public class InsertionSorter<E> extends SortingAlgorithm<E> {

	/* Constructor */
	public InsertionSorter(Comparator<E> comp) {
		super(comp);
	}

	@Override
	public void sort(List<E> list) {
		insertionSort(list);
	}

	/**
	 * Sorts {@link List<E>} list in-place using insertion sort algorithm. <br>
	 * 
	 * @param list,  {@link List<E>} list of type E and size n.
	 * @param left,  int, the index from which sorting is done.
	 * @param right, int, the index until which sorting is done.
	 */
	private void insertionSort(List<E> list) {
		for (int i = 1; i < list.size(); i++) {
			E temp = list.get(i);
			int j = i - 1;
			while (j >= 0 && comp.compare(list.get(j), temp) > 0) {
				list.set(j + 1, list.get(j));
				j--;
			}
			list.set(j + 1, temp);
		}
	}
}
