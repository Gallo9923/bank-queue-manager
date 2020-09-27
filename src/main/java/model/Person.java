package model;

import java.util.ArrayList;
import datastructures.Stack;

public class Person implements Comparable<Person> {

	// Priorities
	public static final int HIGH = 1;
	public static final int MEDIUM = 2;
	public static final int LOW = 3;
	public static final int NONE = 4;

	private String name;
	private int identification;
	private int priority;
	private Stack<ArrayList<Product>> operations;
	private ArrayList<Product> products;
	private boolean inLine;

	public Person(int identification, String name, int priority) {
		this.name = name;
		this.identification = identification;
		this.priority = priority;
		this.operations = new Stack<ArrayList<Product>>();
		this.products = new ArrayList<Product>();
		this.inLine = false;
	}

	public boolean clearOperations() {
		this.operations = new Stack<ArrayList<Product>>();
		return true;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public String getName() {
		return name;
	}

	public int getIdentification() {
		return identification;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isInLine() {
		return inLine;
	}

	protected Stack<ArrayList<Product>> getOperations() {
		return operations;
	}

	@Override
	public int compareTo(Person p2) {

		int priorityC2 = p2.getPriority();
		int result = 0;

		if (priority - priorityC2 < 0) {
			result = -1;
		} else if (priority - priorityC2 > 0) {
			result = 1;
		}

		return result;
	}

	@Override
	public int hashCode() {
		return this.identification;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof Person) {
			Person c2 = (Person) obj;

			if (this.identification - c2.identification == 0) {
				result = true;
			}
		}

		return result;
	}

	/**
	 * Clone the products of the client
	 * 
	 * @return
	 */
	private ArrayList<Product> cloneProducts() {
		ArrayList<Product> clonedProducts = new ArrayList<Product>();

		for (int i = 0; i < products.size(); i++) {
			clonedProducts.add(products.get(i).cloneProduct());
		}

		return clonedProducts;

	}

	/**
	 * Saves the current product status of the client
	 * @return
	 */
	public boolean saveOperation() {
		operations.push(cloneProducts());
		return true;
	}

	/**
	 * Undos the operation done to the client products.
	 * @return
	 */
	public boolean undoOperation() {

		boolean operationStatus = false;

		if (!operations.isEmpty()) {
			products = operations.pop();
			operationStatus = true;
		}

		return operationStatus;

	}
}
