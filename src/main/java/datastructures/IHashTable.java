package datastructures;

public interface IHashTable<K, V> {
	
	public V put(K key, V value);
	
	public V remove(K key);
	
	public V get(K key);
	
	public int size();
	
}