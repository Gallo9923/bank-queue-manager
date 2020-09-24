package utils.sorting;

import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		MergeSorter<Integer> sorter = new MergeSorter<>(Integer::compare);
		int[] nums = new int[] {-100, 1, 50, 200, 0, 1, 2, 3, 8, 50, 60};
//		System.out.println(Arrays.asList(nums).subList(0, 2));
		int[] numsl = new int[] {-100, 1, 50, 200};
		int[] numsr = new int[] {0, 1, 2, 3, 8, 50, 60};
		int n = nums.length;
		int mid = n / 2;
		
//		List<Integer> list = Sorter.list(3, 27, 43, 9, 10, 82, 98);
//		int n = list.size();
//		int mid = n / 2;
//		sorter.merge(list, Sorter.list(3, 27, 43), Sorter.list(9, 10, 82, 98), mid, n -  mid);
//		System.out.println(list);
		
		merge(nums, numsl, numsr, mid, n - mid);
		System.out.println(Arrays.toString(nums));
	}

	public static void merge(int[] a, int[] l, int[] r, int left, int right) {
		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			if (l[i] <= r[j]) {
				a[k++] = l[i++];
			} else {
				a[k++] = r[j++];
			}
		}
		while (i < left) {
			a[k++] = l[i++];
		}
		while (j < right) {
			a[k++] = r[j++];
		}
	}
}