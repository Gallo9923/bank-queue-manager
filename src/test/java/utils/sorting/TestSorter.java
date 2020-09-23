package utils.sorting;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

public class TestSorter {

	private final String PACKAGE_NAME = this.getClass().getPackage().getName();
	private final int MAX_SIZE_LIMIT = 2000;

	private List<Integer> list;
	private Comparator<Integer> comp = Integer::compare;
	private String[] sortingAlgorithmsClassNames = new String[] { "QuickSorter", 
			"MergeSorter", "QuickSort", "HeapSort" };

	public TestSorter() {
		System.out.println("Existo");
	}
	
	@Test
	public void testSortingAlgorithms() {
		for (String sortingAlgorithmClassName : sortingAlgorithmsClassNames) {
			String classLocation = PACKAGE_NAME + "." + sortingAlgorithmClassName;
			Sortable<Integer> sortingAlgorithm = getInstanceOf(classLocation);
			if(sortingAlgorithm == null) {
				
			}
			setupRandomList();

			sortingAlgorithm.sort(list);
			boolean isSorted = isSorted(list);
			String output = list.toString();

			list.sort(comp);
			String sorted = list.toString();

			String message = "";
			if (!isSorted) {
				message += sortingAlgorithmClassName + 
						" is not sorting properly. \nList should be " 
						+ sorted + ", not "
						+ output;
			}

			assertTrue(message, !isSorted);
		}
	}

	@SuppressWarnings("unchecked")
	public Sortable<Integer> getInstanceOf(String className) {
		Sortable<Integer> sortingAlgorithmInstance = null;
		try {
			Class<?> sortingAlgorithmClass;
			sortingAlgorithmClass = Class.forName(className);
			Class<?>[] type = { Comparator.class };
			Constructor<?> cons = sortingAlgorithmClass.getConstructor(type);
			sortingAlgorithmInstance = (Sortable<Integer>) cons.newInstance(comp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return sortingAlgorithmInstance;
	}

	public void setupRandomList() {
		int randomSize = ThreadLocalRandom.current().nextInt(2, MAX_SIZE_LIMIT + 1);
		int randomMaxNumber = ThreadLocalRandom.current().nextInt(2, 10);
		Integer[] randomArray = new Integer[randomSize];

		for (int i = 0; i < randomArray.length; i++)
			randomArray[i] = ThreadLocalRandom.current().nextInt(2, randomMaxNumber + 1);

		this.list = Arrays.asList(randomArray);
	}

	public static <T extends Comparable<T>> boolean isSorted(List<T> list) {
		T previous = null;
		for (T t : list) {
			if (previous != null && t.compareTo(previous) <= 0)
				return false;
			previous = t;
		}
		return true;
	}
}
