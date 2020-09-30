package datastructures;

import java.util.Arrays;

/**
 * @author Sebastián García Acosta
 *
 * @param <K>
 * @param <V>
 */
public class HashTable<K, V>  implements IHashTable<K, V>{

	private static class Entry<K, V> {
		private K key;
		private V value;
		private boolean isDeleted;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
			this.isDeleted = false;
		}
		
		@Override
		public String toString() {
			return "<" + key +"," + value +">";
		}
	}
	
	private final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	private Entry<?, ?> table[];
	private int threshold;
	private double loadFactor;
	private int size;

	public HashTable(int initialCapacity, double loadFactor) {
		this.size = 0;
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		if (loadFactor <= 0 || Double.isNaN(loadFactor))
			throw new IllegalArgumentException("Illegal Load: " + loadFactor);

		if (initialCapacity <= 1)
			initialCapacity = 2;

		this.loadFactor = loadFactor;
		this.table = new Entry<?, ?>[initialCapacity];
		threshold = (int) Math.min(initialCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
	}

	public HashTable(int initialCapacity) {
		this(initialCapacity, 0.75);
	}

	public HashTable() {
		this(11, 0.75);
	}
	
	private int hash2Index(int hashCode) {
		return (hashCode & 0x7FFFFFFF) % table.length;
	}

	private int getIndex(int hashCode, int i) {
		return hash2Index(hashCode + ((i << 5) - i));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public V put(K key, V value) {
		if(key == null)
			throw new NullPointerException("HashTable cannot have null keys");
		
		if (this.size >= threshold)
			rehash();

		Entry<K, V>[] tab = (Entry<K, V>[]) table;

		int i = 0;
		
		while (i < table.length) {
			int index = getIndex(key.hashCode(), i);

			if (tab[index] == null || tab[index].isDeleted ) {
				// Add a new key -value pair if not existed before
				tab[index] = new Entry<>(key, value);
				this.size++;
				break;
			} else if (tab[index].key.equals(key)) { 
				// Replace the value in case that the key was already set
				tab[index].value = value;
				break;
			} else {
				i++;
			}
		}

		return value;
	}

	@Override
	public V get(K key) {
		if(key == null) return null;
		else {
			Entry<K,V> found = searchEntry(key);
			return (found == null || found.isDeleted) ? null : found.value;
		}
	}

	@Override
	public V remove(K key) {
		Entry<K,V> found = searchEntry(key);
		V valueOfDeleted = null;
		
		if(found != null && !found.isDeleted ) {
			valueOfDeleted = found.value;
			found.value = null;
			found.key = null;
			found.isDeleted = true;
		}
		
		return valueOfDeleted;
	}
	
	public boolean containsKey(K key) {
		@SuppressWarnings("unchecked")
		Entry<K, V>[] tab = (Entry<K, V>[]) table;
		int i = 0;
		
		while(i < table.length) {
			int index = getIndex(key.hashCode(), i);
			if (tab[index] == null || tab[index].isDeleted ) {
				return false;
			} else if (tab[index].key.equals(key)) { 
				return true;
			} else {
				i++;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private Entry<K,V> searchEntry(K key){
		Entry<K, V>[] tab = (Entry<K, V>[]) table;
		int i = 0;
		
		while( i < tab.length ) {
			int index = getIndex(key.hashCode(), i);

			if (tab[index] == null || tab[index].isDeleted ) {
				return null;
			} else if (tab[index].key.equals(key)) { 
				return tab[index];
			} else {
				i++;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
        int oldCapacity = table.length;
        Entry<K,V>[] oldMap = (Entry<K,V>[]) table;

        // overflow-conscious code
        int newCapacity = (oldCapacity << 1) + 1; // Duplicate size of array plus one
        if (newCapacity > MAX_ARRAY_SIZE) {
            if (oldCapacity == MAX_ARRAY_SIZE) {
                // Keep running with MAX_ARRAY_SIZE buckets
                return;            	
            }
            newCapacity = MAX_ARRAY_SIZE;
        }
        
        this.table = new Entry<?,?>[newCapacity];
        
        threshold = (int) Math.min(newCapacity * loadFactor, MAX_ARRAY_SIZE + 1);
        
        this.size = 0;
        for(int i = 0; i < oldMap.length; i++) 
        	if (oldMap[i] != null) 
        		put( oldMap[i].key, oldMap[i].value);
	}
	
	public String watchTable() {
		return Arrays.toString(table);
	}
	
	@Override
	public String toString() {
		String res = "{";
		
		for(int i = 0; i < table.length; i++) {
			String sep = (  i == table.length - 1 ) ? "": ",";
			res += (table[i] == null || table[i].isDeleted) ? "": table[i].key + ":" + table[i].value + sep+ " ";			
		}
		
		res += "}";
		
		return res;
	}

	@Override
	public int size() {
		return size;
	}
}