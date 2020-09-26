package model;

import datastructures.HashTable;
import datastructures.Heap;
import datastructures.Queue;


public class Bank {
	
	private String name;
	private int productsCounter;
	private Queue<Client> regular;
	private Heap<Client> priority;
	private HashTable<Integer, Client> clients;
	
	public Bank(String name){
		this.name = name;
		productsCounter = 0;	
	}
	
	public boolean generateRandomArrival() {
		
		boolean operationStatus = false;
		
		
		return operationStatus;
	}
	
}
