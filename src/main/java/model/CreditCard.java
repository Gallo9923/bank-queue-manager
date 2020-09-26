package model;

public class CreditCard extends Product{
	
	private double debt;
	
	public CreditCard(int id) {
		super(id);
		this.debt = 0;
	}
	
	public boolean pay(double amount) {
		
		boolean operationStatus = false;
		
		if(amount <= debt) {
			this.debt -= amount;
		}
		
		return operationStatus;
	}
}
