package model;

public class CreditCard extends Product{
	
	private double debt;
	private int dayOfPayment;
	
	public CreditCard(int id, int day) {
		super(id);
		this.debt = 0;
		this.dayOfPayment = day;
	}
	
	public CreditCard(int id, int day, double debt) {
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
		return new CreditCard(this.getId(), this.dayOfPayment, this.debt);
	}

	public double getDebt() {
		return debt;
	}

	public double getDayOfPayment() {
		return dayOfPayment;
	}
	
	
	
}
