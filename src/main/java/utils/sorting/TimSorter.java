package utils.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimSorter<T> extends SortingAlgorithm<T> {

	static int RUN = 32;

	public TimSorter(Comparator<T> comp) {
		super(comp);
	}

	@Override
	public void sort(List<T> list) {
		timSort(list, list.size());
	}

	// iterative Timsort function to sort the
	// array[0...n-1] (similar to merge sort)
	public void timSort(List<T> arr, int n) {

		// Sort individual subarrays of size RUN
		for (int i = 0; i < n; i += RUN) {
			insertionSort(arr, i, Math.min((i + 31), (n - 1)));
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
				merge(arr, left, mid, right);
			}
		}
	}

	// this function sorts array from left index to
	// to right index which is of size atmost RUN
	public void insertionSort(List<T> arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			T temp = arr.get(i);
			int j = i - 1;
			while (j >= left && comp.compare(arr.get(j), temp) > 0) {
				arr.set(j + 1, arr.get(j));
				j--;
			}
			arr.set(j + 1, temp);
		}
	}

	// merge function merges the sorted runs
	public void merge(List<T> arr, int l, int m, int r) {
		// original array is broken in two parts
		// left and right array
		int len1 = m - l + 1, len2 = r - m;

		List<T> left = new ArrayList<T>(len1);

		List<T> right = new ArrayList<T>(len2);

		arr.subList(0, len1).forEach(left::add);
		arr.subList(len1, len2).forEach(left::add);

		int i = 0;
		int j = 0;
		int k = l;

		// after comparing, we merge those two array
		// in larger sub array
		while (i < len1 && j < len2) {
			if (comp.compare(left.get(i), right.get(j)) <= 0) {
				arr.set(k++, left.get(i++));
			} else {
				arr.set(k++, right.get(j++));
			}
		}

		// copy remaining elements of left, if any
		while (i < len1) {
			arr.set(k++, left.get(i++));
		}

		// copy remaining element of right, if any
		while (j < len2) {
			arr.set(k++, right.get(j++));
		}
	}
}