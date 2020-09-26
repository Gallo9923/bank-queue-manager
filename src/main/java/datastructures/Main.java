package datastructures;

import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		int k = 3;
		int[] nums = { 4, 5, 8, 2 };
		KthLargest solution = new KthLargest(3, nums);

		System.out.println(solution.add(3)); // returns 4
		System.out.println(solution.add(5)); // returns 5
		System.out.println(solution.add(10)); // returns 5
		System.out.println(solution.add(9)); // returns 8
		System.out.println(solution.add(4)); // returns 8
	}
}

class KthLargest {
	private PriorityQueue<Integer> pQueue;
	private int k;

	public KthLargest(int k, int[] nums) {
		this.k = k;
		this.pQueue = new PriorityQueue<Integer>(nums.length + 10);
		for (Integer num : nums) {
			add(num);
		}
	}

	public int add(int val) {
		if (pQueue.size() < k) {
			pQueue.add(val);
		} else {
			if (val > pQueue.peek()) {
				pQueue.poll();
				pQueue.add(val);
			}
		}

		return pQueue.peek();
	}
}
