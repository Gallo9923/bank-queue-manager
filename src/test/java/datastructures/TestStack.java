package datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStack {

	private Stack<Integer> stack;

	public void notEmptySetup() {
		stack = new Stack<>(0, 1, 2);
	}

	public void emptySetup() {
		stack = new Stack<>();
	}

	@Test
	public void testPushWhenEmpty() {
		emptySetup();
		stack.push(0);
		assertTrue(!stack.isEmpty());
		assertTrue(stack.top() == 0);
	}

	@Test
	public void testPushWhenNotEmpty() {
		notEmptySetup();
		assertTrue(!stack.isEmpty());
		stack.push(3);
		assertTrue(stack.top() == 3);
	}

	@Test
	public void testPopWhenEmpty() {
		emptySetup();
		assertTrue(stack.size() == 0);
	}

	@Test
	public void testPopWhenNotEmpty() {
		notEmptySetup();
		assertTrue(!stack.isEmpty());
		assertTrue(stack.pop() == 2);
		assertTrue(stack.size() == 2);
	}

	@Test
	public void testTopWhenEmpty() {
		emptySetup();
		assertTrue(stack.top()== null);
	}
	
	@Test
	public void testTopWhenNotEmpty() {
		notEmptySetup();
		assertTrue(stack.top() == 2);
		assertTrue(stack.size() == 3);
	}

}
