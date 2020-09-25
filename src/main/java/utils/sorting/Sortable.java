package utils.sorting;

import java.util.List;

/**
 * Interface for defining the methods of all sorting algorithms.
 * 
 * @author Sebastián García Acosta.
 *
 * @param <T>, any type.
 */
public interface Sortable<T> {

	/**
	 * Sorts the list in-place.
	 * 
	 * @param list, {@link List<T>} list.
	 */
	public void sort(List<T> list);

	/**
	 * Sorts a copy of {@link List<T>} list and returns it.
	 * 
	 * @param list, {@link List<T>} list.
	 * @return a sorted {@link List<T>} copy of {@link List<T>} list.
	 */
	public List<T> sorted(List<T> list);
}