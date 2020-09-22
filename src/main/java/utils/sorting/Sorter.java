package utils.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter {

//	This class cannot be instantiated
	private Sorter() {

	}

	public static <E> void mergeSort(List<E> list, int l, int r, Comparator<E> comp) {
		new MergeSorter<E>(comp).sort(list);
	}

	public static <E> void heapSort(List<E> list, Comparator<E> comp) {
		// TODO: Nando, please implement heapSort in the same way as the other algorithms
	}

	public static <E> void quickSort(List<E> list, Comparator<E> comp) {
		new QuickSorter<>(comp).sort(list);
	}

	public static <E> void timSort(List<E> list, Comparator<E> comp) {
		new TimSorter<E>(comp, 32).sort(list);
	}

	public static <E> void swap(List<E> list, int i, int j) {
		E temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	// merge function merges the sorted runs
	public static <E> void merge(List<E> list, int l, int m, int r, Comparator<E> comp) {
		// original array is broken in two parts
		// left and right array
		int len1 = m - l + 1, len2 = r - m;
		List<E> left = new ArrayList<>(len1);
		List<E> right = new ArrayList<>(len2);

		for (int x = 0; x < len1; x++) {
			left.set(x, list.get(l + x));
		}

		for (int x = 0; x < len2; x++) {
			right.set(x, list.get(m + 1 + x));
		}

		int i = 0;
		int j = 0;
		int k = l;

		// after comparing, we merge those two array
		// in larger sub array
		while (i < len1 && j < len2) {
			if (comp.compare(left.get(i), right.get(j)) <= 0) {
				list.set(k, left.get(i));
				i++;
			} else {
				list.set(k, right.get(i));
				j++;
			}
			k++;
		}

		// copy remaining elements of left, if any
		while (i < len1) {
			list.set(k, left.get(i));
			k++;
			i++;
		}

		// copy remaining element of right, if any
		while (j < len2) {
			list.set(k, right.get(j));
			k++;
			j++;
		}
	}

}
