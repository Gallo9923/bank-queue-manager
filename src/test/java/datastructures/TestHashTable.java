package datastructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHashTable {

	private HashTable<String, Integer> table;
	
	public void emptySetup() {
		this.table = new HashTable<>(11, 0.75);
	}
	
	@Test
	public void testPut() {
		emptySetup();
		table.put("one", 1);	
		assertTrue("The HashTable does not count the first object added", table.size() == 1);
		assertTrue("The HashTable does not contain the new key added", table.containsKey("one"));
	}

	@Test
	public void testPutAfterRehashed() {
		emptySetup();
//		Add more than the objects in the threshold, so that the hashtable has to increase its size
		for(int i = 0; i < 11; i++)
			table.put("B"+i, i);
		
		table.put("one", 1);	
		assertTrue("The HashTable does not count the first object added", table.size() == 12);
		assertTrue("The HashTable does not contain the new key added", table.containsKey("one"));
	}
	
	
	@Test
	public void testGet() {

	}
}