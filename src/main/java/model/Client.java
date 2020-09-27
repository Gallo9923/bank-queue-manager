package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import utils.sorting.Sorter;

public class Client extends Person {

	private LocalDate registrationDate;

	public Client(int identification, String name, int priority) {
		super(identification, name, priority);
		this.registrationDate = LocalDate.now();

	}

	@SuppressWarnings("unchecked")
	public boolean cancelProduct(int productId) {

		ArrayList<Product> products = this.getProducts();
		boolean operationStatus = false;

		Comparator<Product> comp = new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return p1.compareTo(p2);
			}
		};

		Sorter.mergeSort(this.getProducts(), comp);
		int index = Collections.binarySearch(this.getProducts(), new CreditCard(productId));

		if (index >= 0) {
			this.getOperations().push((ArrayList<Product>) products.clone());

			products.remove(index);
			operationStatus = true;
		}

		return operationStatus;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	
	
	
}
