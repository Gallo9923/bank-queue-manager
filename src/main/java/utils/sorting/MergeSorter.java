package utils.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSorter<E> implements Sortable<E> {

	private Comparator<E> comp;

	public MergeSorter(Comparator<E> comp) {
		this.comp = comp;
	}

	@Override
	public void sort(List<E> list) {
		sort(list, list.size());
	}

	private void sort(List<E> list, int n) {
		if (n <= 1)
			return;

		int mid = n / 2;
		List<E> left = new ArrayList<>(mid + 1);
		List<E> right = new ArrayList<>(mid + 1);

		list.subList(0, mid).forEach(left::add);
		list.subList(mid, n).forEach(right::add);

		sort(left, mid);
		sort(right, n - mid);

		merge(list, left, right);
	}

	public void merge(List<E> list, List<E> leftList, List<E> rightList) {
		int leftPointer = 0, rightPointer = 0, resultPointer = 0;

		while (leftPointer < leftList.size() || rightPointer < rightList.size()) {

			if (leftPointer < leftList.size() && rightPointer < rightList.size()) {

				if (comp.compare(leftList.get(leftPointer), rightList.get(rightPointer)) <= 0)
					list.set(resultPointer++, leftList.get(leftPointer++));

				else
					list.set(resultPointer++, rightList.get(rightPointer++));

			} else if (leftPointer < leftList.size()) {

				list.set(resultPointer++, leftList.get(leftPointer++));

			} else {

				list.set(resultPointer++, rightList.get(rightPointer++));
			}
		}
	}
}