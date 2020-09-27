package model;

public class DebitCard extends Product{
	
	private double cash;
	
	public DebitCard(int id, double cash) {
		super(id);
		this.cash = cash;
	}
	
	public DebitCard(int id) {
		super(id);
		this.cash = 0;
	}
	
	public boolean withdrawal(double amount) {
		
		boolean operationStatus = false;
		
		if(amount <= cash) {
			this.cash -= amount;
			operationStatus = true;
		}
		
		return operationStatus;
	}
	
	public boolean deposit(double amount) {
		
		this.cash += amount;
		return true;
		
	}
	
	@Override
	public DebitCard cloneProduct() {
		return new DebitCard(this.getId(), this.cash);
	}
 
}
