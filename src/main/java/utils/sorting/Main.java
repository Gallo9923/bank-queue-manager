package utils.sorting;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		MergeSorter<Integer> sorter = new MergeSorter<>(Integer::compare);
//		int[] nums = new int[] {-100, 1, 50, 200, 0, 1, 2, 3, 8, 50, 60};
////		System.out.println(Arrays.asList(nums).subList(0, 2));
//		int[] numsl = new int[] {-100, 1, 50, 200};
//		int[] numsr = new int[] {0, 1, 2, 3, 8, 50, 60};
//		int n = nums.length;
//		int mid = n / 2;
		
		List<Integer> list = Sorter.list(-100, 1, 50, 200, 0, 1, 2, 3, 8, 50, 60);
		System.out.println(Sorter.quickSorted(list, Integer::compare));
		
//		merge(nums, numsl, numsr, mid, n - mid);
//		System.out.println(Arrays.toString(nums));
	}
}