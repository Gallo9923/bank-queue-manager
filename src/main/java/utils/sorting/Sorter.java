package utils.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sorter {

//	This class cannot be instantiated
	private Sorter() {

	}

	public static <E> void mergeSort(List<E> list, Comparator<E> comp) {
		new MergeSorter<E>(comp).sort(list);
	}

	public static <E> void heapSort(List<E> list, Comparator<E> comp) {
		new HeapSorter<>(comp).sort(list);
	}

	public static <E> void quickSort(List<E> list, Comparator<E> comp) {
		new QuickSorter<>(comp).sort(list);
	}

	public static <E> void swap(List<E> list, int i, int j) {
		E temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public static <E> void insertionSort(List<E> list, Comparator<E> comp) {
		new InsertionSorter<E>(comp).sort(list);
	}

	@SafeVarargs
	public static <E> List<E> list(E... elements) {
		return Arrays.asList(elements);
	}
}
