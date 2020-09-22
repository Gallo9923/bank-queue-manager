package utils.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

import datastructures.HashTable;

public class TestSorter {

	private List<Integer> list;
	private Sortable<Integer> sorter;
	private Comparator<Integer> comp = (n1, n2) -> n1 - n2;
	
	public List<Integer> arr2list(Integer... elements) {
		return Arrays.asList(elements);
	}
	
	/* SETUPS */
	public void setupQuickSort() {
		sorter = new QuickSorter<Integer>(comp);
	}

	public void setupMergeSort() {
		sorter = new MergeSorter<Integer>(comp);
	}

	public void setupTimSort() {
		sorter = new TimSorter<Integer>(comp);
	}
	
	public void setupEmtpyList() {
		this.list = arr2list();
	}
	
	public void setupList() {
		this.list = arr2list(1,2,3,3,4,5,65, -2343);
	}
	

	public void setupHomogeneousList() {
		this.list = arr2list(1,2,2,2,2,2,2,2);
	}

	@Test 
	public void testAlgorithms() {
		HashTable<Integer, String> dict = new HashTable<Integer, String>();
		dict.put(0, "MergeSort");
		dict.put(1, "TimSort");
		dict.put(2, "QuickgeSort");
		
	}
	
	@Test
	public void test( Consumer<Void> scenario, Consumer<Void> algorithmInitialization ) {
		
	}
}
