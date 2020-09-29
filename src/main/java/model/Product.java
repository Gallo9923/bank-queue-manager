package model;

public abstract class Product implements Comparable<Product>, Cloneable {

	private int id;

	public Product(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public int compareTo(Product p2) {

		int id2 = p2.getId();
		int result = 0;

		if (id - id2 < 0) {
			result = -1;
		} else if (id - id2 > 0) {
			result = 1;
		}

		return result;
	}

	public Product cloneProduct() {
		return null;
	}
}
