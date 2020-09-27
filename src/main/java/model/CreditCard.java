package model;

public class CreditCard extends Product{
	
	private double debt;
	
	public CreditCard(int id) {
		super(id);
		this.debt = 0;
	}
	
	public CreditCard(int id, double debt) {
		super(id);
		this.debt = debt;
	}
	
	public boolean pay(double amount) {
		
		boolean operationStatus = false;
		
		if(amount <= debt) {
			this.debt -= amount;
		}
		
		return operationStatus;
	}
	
	@Override
	public CreditCard cloneProduct() {
		return new CreditCard(this.getId(), this.debt);
	}
}
