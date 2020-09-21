package datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestQueue {

	private Queue<Integer> queue;

	public void notEmptySetup() {
		queue = new Queue<>(0, 1, 2);
	}

	public void emptySetup() {
		queue = new Queue<>();
	}

	@Test
	public void testEnqueueWhenEmpty() {
		emptySetup();
		queue.enqueue(0);
		assertTrue(!queue.isEmpty());
		assertTrue(queue.front() == 0);
	}

	@Test
	public void testEnqueueWhenNotEmpty() {
		notEmptySetup();
		assertTrue(!queue.isEmpty());
		queue.enqueue(-1);
		assertTrue(queue.size() == 4);
	}

	@Test
	public void testDequeueWhenEmpty() {
		emptySetup();
		assertTrue(queue.dequeue() == null);
		assertTrue(queue.size() == 0);
	}

	@Test
	public void testDequeueWhenNotEmpty() {
		notEmptySetup();
		assertTrue(!queue.isEmpty());
		assertTrue(queue.dequeue() == 0);
		assertTrue(queue.size() == 2);
	}

	@Test
	public void testFrontWhenEmpty() {
		emptySetup();
		assertTrue(queue.front == null);
	}
	
	@Test
	public void testFrontWhenNotEmpty() {
		notEmptySetup();
		assertTrue(queue.front() == 0);
		assertTrue(queue.size() == 3);
	}
	
}
