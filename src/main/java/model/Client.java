package model;

import java.time.LocalDate;

public class Client extends Person {

	private LocalDate registrationDate;
	private String cancellationReason;
	private LocalDate cancellationDate;
	
	
	public Client(int identification, String name, int priority) {
		super(identification, name, priority);
		this.registrationDate = LocalDate.now();
		this.cancellationReason = null;
		this.cancellationDate = null;
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
	
	
	
	
}
