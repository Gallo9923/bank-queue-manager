package utils.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementation of MergeSort algorithm
 * 
 * @author Sebastián García Acosta
 * @param <E>, any type.
 */
class MergeSorter<E> extends SortingAlgorithm<E> {

	/** Constructor */
	public MergeSorter(Comparator<E> comp) {
		super(comp);
	}

	/**
	 * Sorts {@link List<E>} list recursively in-place using merge sort algorithm.
	 * <br>
	 * 
	 * @param list, {@link List<E>} list of type E.
	 */
	@Override
	public void sort(List<E> list) {
		sort(list, list.size());
	}

	/**
	 * Sorts {@link List<E>} list recursively in-place using merge sort algorithm.
	 * <br>
	 * 
	 * @param list, {@link List<E>} list of type E and size n.
	 * @param n,    the size of {@link List<E>} list.
	 */
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

	/**
	 * Merges two already sorted lists into another that maintains the sorting
	 * property specified by {@link Comparator<E>} comp.<br>
	 * 
	 * <b>pre:</b> rightList and leftList are already sorted<br>
	 * 
	 * <b>post:</b> The elements of {@link List<E>} list will be sorted in-place.
	 * 
	 * @param leftList,  a sorted {@link List<E>} of size n and type E
	 * @param rightList, a sorted {@link List<E>} of size m and type E
	 * @param list,      an unsorted {@link List<E>} list of size (n+m) and type E
	 *                   such that its first n elements are the same as
	 *                   <b>leftList</b> elements and its m subsequent elements are
	 *                   the same as <b>rightList</b> elements.
	 */
	public void merge(List<E> list, List<E> leftList, List<E> rightList) {
		int leftPointer, rightPointer, resultPointer;
		leftPointer = rightPointer = resultPointer = 0;
		
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