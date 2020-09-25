package utils.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Sorting API with four different sorting algorithms implementations.
 * 
 * @authors Sebastián García Acosta, Juan Fernando Angulo. 
 *
 */
public class Sorter {

//	This class cannot be instantiated
	private Sorter() {
	}

	public static <E> void mergeSort(List<E> list, Comparator<E> comp) {
		new MergeSorter<E>(comp).sort(list);
	}

	public static <E> List<E> mergeSorted(List<E> list, Comparator<E> comp) {
		return new MergeSorter<E>(comp).sorted(list);
	}

	public static <E> void heapSort(List<E> list, Comparator<E> comp) {
		new HeapSorter<>(comp).sort(list);
	}

	public static <E> List<E> heapSorted(List<E> list, Comparator<E> comp) {
		return new HeapSorter<>(comp).sorted(list);
	}

	public static <E> void quickSort(List<E> list, Comparator<E> comp) {
		new QuickSorter<>(comp).sort(list);
	}

	public static <E> List<E> quickSorted(List<E> list, Comparator<E> comp) {
		return new QuickSorter<>(comp).sorted(list);
	}

	public static <E> void insertionSort(List<E> list, Comparator<E> comp) {
		new InsertionSorter<E>(comp).sort(list);
	}

	public static <E> List<E> insertionSorted(List<E> list, Comparator<E> comp) {
		return new InsertionSorter<E>(comp).sorted(list);
	}

	@SafeVarargs
	static <E> List<E> list(E... elements) {
		return Arrays.asList(elements);
	}
}
