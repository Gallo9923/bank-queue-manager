package utils.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Sebastián García Acosta
 *
 * @param <T>, any type.
 */
abstract class SortingAlgorithm<T> implements Sortable<T> {
	
	/** Comparator that defines the sorting property */
	protected Comparator<T> comp;

	/** Constructor */
	public SortingAlgorithm(Comparator<T> comp) {
		this.comp = comp;
	}

	/**
	 * Returns a sorted cloned list of {@link List<T>} list.
	 * 
	 * @param list, a {@link List<T>}.
	 * @return a sorted copy of {@link List<T>} list.
	 */
	public List<T> sorted(List<T> list) {
		List<T> clonedList = new ArrayList<>(list);
		sort(clonedList);
		return clonedList;
	}
}