package utils.sorting;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class TestSorter {

	private Comparator<Integer> comp = Integer::compare;
	private List<Integer> input;
	private List<Integer> expectedOutput;

	public void setupMerge1() {
		input = Sorter.list(-100, 1, 50, 200, 0, 1, 2, 3, 8);
		expectedOutput = Sorter.list(-100, 0, 1, 1, 2, 3, 8, 50, 200);
	}

	public void setupMerge2() {
		input = Sorter.list(27, 98, 3, 43);
		expectedOutput = Sorter.list(3, 27, 43, 98);
	}

	public void setupMerge3() {
		input = Sorter.list(-100, 1, 50, 200, 0, 1, 2, 3, 8, 50, 60);
		expectedOutput = Sorter.list(-100, 0, 1, 1, 2, 3, 8, 50, 50, 60, 200);
	}
	
	public void setupSorting1() {
		input = Sorter.list(2, 3, 3, 1, 5, 84, 4, 23, 40);
		expectedOutput = Sorter.list(1, 2, 3, 3, 4, 5, 23, 40, 84);
	}

	public void setupSorting2() {
		Integer[] unsorted = new Integer[16];
		
		int i = 0;
		for(int j = unsorted.length; j >= 1; j--) {
			unsorted[i] = j;
			i++;
		}
		
		input = Arrays.asList(unsorted);
		
		Integer[] sorted =  Arrays.copyOf(unsorted, unsorted.length);
		Arrays.sort(sorted);
		
		expectedOutput = Arrays.asList(sorted);
	}

	public void setupSorting3() {
		input = Sorter.list(-10000, 10000, 0);
		expectedOutput = Sorter.list(-10000, 0, 10000);
	}

	@Test
	public void testMerge1() {
		setupMerge1();
		int n = input.size();
		int m = n / 2 ; 
		
		List<Integer> left = new ArrayList<>(m+1);
		input.subList(0, m).forEach(left::add);

		List<Integer> right = new ArrayList<>(m+1);
		input.subList(m, n).forEach(right::add);

		new MergeSorter<Integer>(comp).merge(input, right, left);
		System.out.println(input);
		
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));		
	}
	
	@Test
	public void testMerge2() {
		setupMerge2();
		int n = input.size();
		int m = n / 2;
		
		List<Integer> left = new ArrayList<>(m+1);
		input.subList(0, m).forEach(left::add);

		List<Integer> right = new ArrayList<>(m+1);
		input.subList(m, n).forEach(right::add);

		new MergeSorter<Integer>(comp).merge(input, right, left);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testMerge3() {
		setupMerge3();
		int n = input.size();
		
		List<Integer> left = new ArrayList<>();
		input.subList(0, 4).forEach(left::add); // -100, 1, 50, 200

		List<Integer> right = new ArrayList<>(); 
		input.subList(4, n).forEach(right::add); // 0, 1, 2, 3, 8, 50, 60
		
		new MergeSorter<Integer>(comp).merge(input, right, left);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}
	
	@Test
	public void testInsertionSort1() {
		setupSorting1();
		Sorter.insertionSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));		
	}
	
	@Test
	public void testInsertionSort2() {
		setupSorting2();
		Sorter.insertionSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));		
	}
	
	@Test
	public void testInsertionSort3() {
		setupSorting3();
		Sorter.insertionSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));		
	}

	
	@Test
	public void testHeapSort1() {
		setupSorting1();
		Sorter.heapSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testHeapSort2() {
		setupSorting2();
		Sorter.heapSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testHeapSort3() {
		setupSorting3();
		Sorter.heapSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testQuickSort1() {
		setupSorting1();
		Sorter.quickSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testQuickSort2() {
		setupSorting2();
		Sorter.quickSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testQuickSort3() {
		setupSorting3();
		Sorter.quickSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testMergeSort1() {
		setupSorting1();
		Sorter.mergeSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testMergeSort2() {
		setupSorting2();
		Sorter.mergeSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}
	
	@Test
	public void testMergeSort3() {
		setupSorting3();
		Sorter.mergeSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	public static <E> String failMessage(List<E> expectedList, List<E> actualList) {
		return "List is " + actualList+ " but should be " + expectedList;
	}
}
