package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import utils.sorting.Sorter;
import datastructures.Stack;

public class Client implements Comparable<Client>{

	// Priorities
	public static final int HIGH = 1;
	public static final int MEDIUM = 2;
	public static final int LOW = 3;
	public static final int NONE = 4;

	private ArrayList<Product> products;

	private String name;
	private String identification;
	private int priority;
	private LocalDate registrationDate;
	private Stack<ArrayList<Product>> operations;

	public Client(String name, String identification, int priority) {

		this.name = name;
		this.identification = identification;
		this.priority = priority;
		this.registrationDate = LocalDate.now();
		this.products = new ArrayList<Product>();
		this.operations = new Stack<ArrayList<Product>>();

	}
	
	public boolean undoOperation() {
		
		boolean operationStatus = false;
		
		if (!operations.isEmpty()) {
			products = operations.pop();
			operationStatus = true;
		}
		
		return operationStatus;
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean cancelProduct(int productId) {

		boolean operationStatus = false;

		Comparator<Product> comp = new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return p1.compareTo(p2);
			}
		};

		Sorter.mergeSort(products, comp);
		int index = Collections.binarySearch(products, new CreditCard(productId));

		if (index >= 0) {
			operations.push((ArrayList<Product>) products.clone());
			
			products.remove(index);
			operationStatus = true;
		}

		return operationStatus;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public String getName() {
		return name;
	}

	public String getIdentification() {
		return identification;
	}

	public int getPriority() {
		return priority;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	@Override
	public int compareTo(Client c2) {
		
		int priorityC2 = c2.getPriority();
		int result = 0;
		
		if(priority - priorityC2 < 0) {
			result = -1;
		}else if(priority - priorityC2 > 0) {
			result = 1;
		}
		
		return result;
		
		
	}
	
}
