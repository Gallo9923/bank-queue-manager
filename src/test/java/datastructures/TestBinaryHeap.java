package datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBinaryHeap {
	
	private BinaryHeap<Integer> maxHeap;
	
	private BinaryHeap<Integer> minHeap;
	
	public void emptySetup() {
		maxHeap = new MaxHeap<Integer>(10);
		minHeap = new MinHeap<Integer>(10);
	}
	
	public void notEmptySetup() {
		maxHeap = new MaxHeap<Integer>(new Integer[] {2, 4, 6, 8, 10});
		minHeap = new MinHeap<Integer>(new Integer[] {2, 4, 6, 8, 10});
	}

	@Test
	public void testAddMaxHeap() {
		emptySetup();
		assertTrue(maxHeap.isEmpty());
		maxHeap.add(4);
		maxHeap.add(8);
		maxHeap.add(12);
		assertTrue(maxHeap.peek() == 12);
		assertTrue(maxHeap.parent(2) == 12 && maxHeap.parent(3) == 12);
		assertTrue(maxHeap.size() == 3);
		maxHeap.add(2);
		assertTrue(maxHeap.parent(4) == 4);
		assertTrue(maxHeap.size() == 4);
	}

	@Test
	public void testRemoveMaxHeap() {
		notEmptySetup();
		assertTrue(!maxHeap.isEmpty());
		assertTrue(maxHeap.peek() == 10);
		maxHeap.remove();
		assertTrue(maxHeap.peek() == 8);
		assertTrue(maxHeap.size() == 4);
		assertTrue(!maxHeap.remove(12));
		maxHeap.remove(6);
		maxHeap.remove();
		assertTrue(maxHeap.peek() == 4);
	}
	
	@Test
	public void testAddMinHeap() {
		emptySetup();
		minHeap.add(12);
		minHeap.add(8);
		minHeap.add(4);
		assertTrue(minHeap.peek() == 4);
		assertTrue(minHeap.size() == 3);
		minHeap.add(2);
		assertTrue(minHeap.peek() == 2);
		assertTrue(minHeap.parent(2) == 2);
	}
	
	@Test
	public void testRemoveMinHeap() {
		notEmptySetup();
		assertTrue(minHeap.peek() == 2);
		assertTrue(minHeap.size() == 5);
		minHeap.remove();
		assertTrue(minHeap.peek() == 4);
		assertTrue(minHeap.size() == 4);
		assertTrue(minHeap.hasLeftChild(2));
		minHeap.remove(10);
		assertTrue(!minHeap.hasLeftChild(2));
	}
}
