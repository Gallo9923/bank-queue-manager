package utils.sorting;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class TestSorter {

	private Comparator<Integer> comp = Integer::compare;
	private List<Integer> input;
	private List<Integer> expectedOutput;

	public void setupMerge1() {
		input = list(27, 98, 3, 43);
		expectedOutput = list(3, 27, 43, 98);
	}

	public void setupMerge2() {
		input = list(3, 27, 43, 98, 9, 10, 82);
		expectedOutput = list(3, 9, 10, 27, 43, 82, 90);
	}

	public void setupSorting1() {
		input = list(2, 3, 3, 1, 5, 84, 4, 23);
		expectedOutput = list(1, 2, 3, 3, 4, 5, 23, 84);
	}

	public void setupSorting2() {
		input = list(-2, -2, -2, -2, -2, -2, -2);
		expectedOutput = list(-2, -2, -2, -2, -2, -2, -2);
	}

	public void setupSorting3() {
		input = list(-10000, 10000, 0);
		expectedOutput = list(-10000, 0, 10000);
	}

	@Test
	public void testMerge1() {
		setupMerge1();
		int m = (input.size() - 1) / 2;
		Sorter.merge(input, 0, m, input.size() - 1, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testMerge2() {
		setupMerge2();
		int m = (input.size() - 1) / 2;
		Sorter.merge(input, 0, m, input.size() - 1, comp);
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

	@Test
	public void testTimSort1() {
		setupSorting1();
		Sorter.timSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testTimSort2() {
		setupSorting2();
		Sorter.timSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@Test
	public void testTimSort3() {
		setupSorting3();
		Sorter.timSort(input, comp);
		assertTrue(failMessage(expectedOutput, input), input.equals(expectedOutput));
	}

	@SafeVarargs
	public static <E> List<E> list(E... elements) {
		return Arrays.asList(elements);
	}

	public static <E> String failMessage(List<E> expectedList, List<E> actualList) {
		return "List is " + actualList+ " but should be " + expectedList;
	}
}
