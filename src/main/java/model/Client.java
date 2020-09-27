package model;

import java.time.LocalDate;
import java.util.ArrayList;

import datastructures.Stack;

public class Client extends Person {

	private LocalDate registrationDate;
	private String cancellationReason;
	private LocalDate cancellationDate;
	private Stack<ArrayList<Product>> operations;
	private ArrayList<Product> products;
	
	public Client(int identification, String name, int priority) {
		super(identification, name, priority);
		this.registrationDate = LocalDate.now();
		this.cancellationReason = null;
		this.cancellationDate = null;
		this.operations = new Stack<ArrayList<Product>>();
		this.products = new ArrayList<Product>();
	}
	
	/**
	 * Clone the products of the client
	 * 
	 * @return ArrayList<Product> cloned products
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
	 * 
	 * @return boolean True if the operation was successful
	 */
	public boolean saveOperation() {
		operations.push(cloneProducts());
		return true;
	}

	/**
	 * Undos the operation done to the client products.
	 * 
	 * @return boolean True if the operation was successful
	 */
	public boolean undoOperation() {

		boolean operationStatus = false;

		if (!operations.isEmpty()) {
			this.products = operations.pop();
			operationStatus = true;
		}

		return operationStatus;

	}

	/**
	 * Withdraws an amount of money from the DebitCard product
	 * 
	 * @param amount
	 * @return boolean True if the operation was successful
	 */
	public boolean withdraw(double amount) {

		boolean found = false;
		boolean operationStatus = false;

		for (int i = 0; i < products.size() && !found; i++) {
			if (products.get(i) != null && products.get(i) instanceof DebitCard) {
				saveOperation();
				found = true;
				DebitCard dc = (DebitCard) products.get(i);
				operationStatus = dc.withdraw(amount);

			}
		}

		return operationStatus;
	}

	/**
	 * Deposits an amount of money to the DebitCard Product
	 * 
	 * @param amount
	 * @return boolean True if the operation was successful
	 */
	public boolean deposit(double amount) {
		boolean found = false;
		boolean operationStatus = false;

		for (int i = 0; i < products.size() && !found; i++) {
			if (products.get(i) != null && products.get(i) instanceof DebitCard) {
				saveOperation();
				found = true;
				DebitCard dc = (DebitCard) products.get(i);
				operationStatus = dc.deposit(amount);

			}
		}

		return operationStatus;

	}

	/**
	 * Pays an amount of money to the debt of a credit card
	 * 
	 * @param amount
	 * @return boolean True if the operation was successful
	 */
	public boolean payCreditCard(double amount) {

		boolean found = false;
		boolean operationStatus = false;

		for (int i = 0; i < products.size() && !found; i++) {
			if (products.get(i) != null && products.get(i) instanceof CreditCard) {
				saveOperation();
				found = true;
				CreditCard cc = (CreditCard) products.get(i);
				operationStatus = cc.pay(amount);

			}
		}

		return operationStatus;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}


	public String getCancellationReason() {
		return cancellationReason;
	}


	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}


	public LocalDate getCancellationDate() {
		return cancellationDate;
	}


	public void setCancellationDate(LocalDate cancellationDate) {
		this.cancellationDate = cancellationDate;
	}
	
	public boolean addProduct(Product product) {
		return products.add(product);
	}

	public boolean clearOperations() {
		this.operations = new Stack<ArrayList<Product>>();
		return true;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	protected Stack<ArrayList<Product>> getOperations() {
		return operations;
	}
	
	
}
