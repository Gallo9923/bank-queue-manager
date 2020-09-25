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
	public void testGet() {
		emptySetup();
		table.put("one", 1);
		table.put("two", 2);
		table.put("three", 3);
		table.put("four", 4);
		
		assertTrue( "The table does not retrieves the value by the key", table.get("one").equals(1));
	}
	
	@Test
	public void testPutingWithDuplicateKeys() {
		emptySetup();
		table.put("one", 1);
		table.put("one", 2);
		table.put("one", 3);
		table.put("one", 4);
		
		assertTrue( "Table does not override values of duplicate keys", table.get("one").equals(1));
	}
	
	@Test
	public void testPutAfterRehashed() {
		emptySetup();
//		Add more than the objects in the threshold, so that the hash table size increases
		for(int i = 0; i < 11; i++)
			table.put("B"+i, i);

//		Put another value after the rehashing
		table.put("one", 1);	
		assertTrue("The HashTable does not count the first object added", table.size() == 12);
		assertTrue("The HashTable does not contain the new key added", table.containsKey("one"));
	}
	
	@Test
	public void testGetAfterRehashed() {
		emptySetup();
//		Add more than the objects in the threshold, so that the hash table size increases
		for(int i = 0; i < 11; i++)
			table.put("B"+i, i);

//		Put another value after the rehashing
		table.put("one", 1);	
		assertTrue("The HashTable does not count the first object added", table.size() == 12);
		assertTrue("The HashTable does not contain the new key added", table.containsKey("one"));
		assertTrue("The HashTable does not contain the new key added", table.get("one") == 1);
	}
}