package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter {

//	Cannot be instantiated
	private Sorter() {

	}

	public static <E> void mergeSort(List<E> list, Comparator<E> comp) {
		mergeSort(list, 0, list.size() - 1, comp);
	}

	private static <E> void mergeSort(List<E> list, int l, int r, Comparator<E> comp) {
		if (l < r) {
			// Same as (l+r)/2, but avoids overflow for
			// large l and h
			int m = l + (r - l) / 2;

			// Sort first and second halves
			mergeSort(list, l, m, comp);
			mergeSort(list, m + 1, r, comp);

			merge(list, l, m, r, comp);
		}
	}

	public static <E> void heapSort(List<E> list, Comparator<E> comp) {

	}

	public static <E> void quickSort(List<E> list, Comparator<E> comp) {
		sort(list, 0, list.size() - 1, comp);
	}

	private static <E> void sort(List<E> list, int low, int high, Comparator<E> comp) {
		if (low < high) {
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			int pi = partition(list, low, high, comp);

			// Recursively sort elements before
			// partition and after partition
			sort(list, low, pi - 1, comp);
		}
	}

	private static <E> int partition(List<E> list, int low, int high, Comparator<E> comp) {
		E pivot = list.get(high);
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than the pivot
			if (comp.compare(list.get(j), pivot) < 0) {
				i++;

				// swap arr[i] and arr[j]
				swap(list, i, j);
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		swap(list, i + 1, high);

		return i + 1;
	}

	private static <E> void swap(List<E> list, int i, int j) {
		E temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	public static <E> void timSort(List<E> list, Comparator<E> comp) {
		int RUN = 32;
		int n = list.size();

		// Sort individual subarrays of size RUN
		for (int i = 0; i < n; i += RUN) {
			insertionSort(list, i, Math.min((i + 31), (n - 1)), comp);
		}

		// start merging from size RUN (or 32). It will merge
		// to form size 64, then 128, 256 and so on ....
		for (int size = RUN; size < n; size = 2 * size) {

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
				merge(list, left, mid, right, comp);
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

	// merge function merges the sorted runs
	private static <E> void merge(List<E> list, int l, int m, int r, Comparator<E> comp) {
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
