package utils.sorting;

import java.util.Comparator;
import java.util.List;

class HeapSorter<T> implements Sortable<T>{
	
	private Comparator<T> comp;
	
	public HeapSorter(Comparator<T> comp) {
		this.comp = comp;
	}
	
	public void sort(List<T> arr) { 
        int n = arr.size(); 
        
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr, n, i); 
  
        for (int i=n-1; i>0; i--) { 
            T temp = arr.get(0); 
            arr.set(0, arr.get(i)); 
            arr.set(i, temp); 
            
            heapify(arr, i, 0); 
        }
    }
	
    void heapify(List<T> arr, int n, int i) { 
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;
        
        if (l < n && comp.compare(arr.get(l), arr.get(largest)) > 0) 
            largest = l; 
        
        if (r < n && comp.compare(arr.get(r), arr.get(largest)) > 0) 
            largest = r; 
        
        if (largest != i) {
            T swap = arr.get(i); 
            arr.set(i, arr.get(largest)); 
            arr.set(largest, swap); 

            heapify(arr, n, largest); 
        }
    }
}
