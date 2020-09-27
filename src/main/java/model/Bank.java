package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import datastructures.HashTable;
import datastructures.MinHeap;
import datastructures.PriorityQueue;
import datastructures.Queue;
import datastructures.Stack;

public class Bank {

	private String name;
	private Queue<Person> regular;
	private PriorityQueue<Person> priority;
	private HashTable<Integer, Client> oldClients;
	private HashTable<Integer, Client> clients;
	private HashSet<Integer> clientsInBank;
	private HashSet<Integer> allClients;
	private Stack<Person> operations;
	private Person currentPerson;

	/**
	 * Creates an instance of a Bank
	 * 
	 * @param name Name of the bank
	 */
	public Bank(String name) {
		this.name = name;
		clientsInBank = new HashSet<Integer>();
		allClients = new HashSet<Integer>();

		clients = new HashTable<Integer, Client>();
		oldClients = new HashTable<Integer, Client>();
		priority = new MinHeap<Person>(100);
		regular = new Queue<Person>();
		operations = new Stack<Person>();
		currentPerson = null;
	}

	/**
	 * Cancels the account of a client
	 * 
	 * @param identification
	 * @return
	 */
	public boolean cancelAccount(int identification, String cancellationReason, LocalDate date) {

		Client client = clients.get(identification);
		client.setCancellationReason(cancellationReason);
		client.setCancellationDate(LocalDate.now());
		operations.push(client);

		clients.remove(identification);
		oldClients.put(identification, client);

		return true;
	}

	/**
	 * Generates a random arrival of a person or client to the bank
	 * 
	 * @return boolean True if it generated a random arrival
	 */
	public boolean generateRandomArrival() {

		boolean operationStatus = false;

		double random = Math.random();
		Person p = null;

		if (random < 0.5) {
			p = getRandomClient();

		} else {
			p = createRandomPerson();
		}

		// If all clients are inside the bank
		if (p == null) {
			p = createRandomPerson();
		}

		// Add person to the queue
		if (p != null) {
			p.setIsInLine(true);
			if (p.getPriority() == 1) {
				regular.enqueue(p);
			} else {
				priority.add(p);
			}
			operationStatus = true;
		}

		operations = new Stack<Person>();

		return operationStatus;
	}

	/**
	 * Returns a random person
	 * 
	 * @return Person
	 */
	private Person createRandomPerson() {
		Random r = new Random();
		return new Person(r.nextInt(), "Carlos", generateRandomPriority());

	}

	/**
	 * Returns a random client that is not present in the bank. Null if all clients
	 * are inside the bank
	 * 
	 * @return Client Random client
	 */
	private Client getRandomClient() {

		Client client = null;

		HashSet<Integer> difference = new HashSet<Integer>(allClients);
		difference.removeAll(clientsInBank);

		Iterator<Integer> iter = difference.iterator();
		int element = new Random().nextInt(difference.size());

		for (int i = 0; i < element - 1; i++) {
			iter.next();
		}

		int key = iter.next();
		client = clients.get(key);
		client.clearOperations();

		return client;

	}

	public int generateRandomPriority() {
		Random r = new Random();
		return r.nextInt(4) + 1;
	}

	/**
	 * Creates a random Client with random products
	 * 
	 * @return Client
	 */
	public Client createRandomClient() {

		// Generate Random ID
		Random r = new Random();
		int identification = r.nextInt();
		while (clients.containsKey(identification)) {
			identification = r.nextInt();
		}

		Client client = new Client(identification, "Chris", generateRandomPriority());

		// Generate Random Products

		CreditCard cc = new CreditCard(identification, 15,  r.nextDouble());
		DebitCard dc = new DebitCard(identification, r.nextDouble());
		
		client.addProduct(cc);
		client.addProduct(dc);

		return client;
	}
	
	/**
	 * Withdraws an amount of money from the DebitCard product
	 * 
	 * @param amount
	 * @return boolean True if the operation was successful
	 */
	public boolean withdraw(double amount) {
		boolean operationStatus = false;
		
		if(currentPerson instanceof Client) {
			Client client = (Client)currentPerson;
			operationStatus = client.withdraw(amount);
		}
		
		return operationStatus;
	}
	
	/**
	 * Deposits an amount of money to the current person
	 * @param amount
	 * @return boolean True if the operation was successful
	 */
	public boolean deposit(double amount) {
		
		boolean operationStatus = false;
		
		if(currentPerson instanceof Client) {
			Client client = (Client)currentPerson;
			operationStatus = client.deposit(amount);
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
		
		boolean operationStatus = false;
		
		if(currentPerson instanceof Client) {
			Client client = (Client)currentPerson;
			operationStatus = client.payCreditCard(amount);
		}
		
		return operationStatus;
	}
	
	/**
	 * Returns the name of the bank
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Undoes the latest operation done to the client
	 * @return
	 */
	public boolean undoOperation() {
		boolean operationStatus = false;
		
		if (operations.size() > 0) {
			Client client = (Client)operations.pop();
			client.setCancellationDate(null);
			client.setCancellationReason(null);
			
			oldClients.remove(client.getIdentification());
			clients.put(client.getIdentification(), client);
			
			operationStatus = true;
		}else {
			if(currentPerson instanceof Client) {
				Client client = (Client)this.currentPerson;
				operationStatus = client.undoOperation();
			}
		}
		
		return operationStatus;
	}
	
	/**
	 * Attends the next person in the priority Queue
	 * 
	 * @return boolean True if the operation was successful
	 */
	public boolean nextInPriorityQueue() {
		boolean operationStatus = false; 
		
		if(priority.size() > 1 ) {
			currentPerson.setIsInLine(false);
			this.operations = new Stack<Person>();
			currentPerson = priority.poll();
			operationStatus = true;
		}
		
		return operationStatus;
	}
	
	/**
	 * Attends the next person in the priority Queue
	 * 
	 * @return boolean True if the operation was successful
	 */
	public boolean nextInRegularQueue() {
		boolean operationStatus = false; 
		
		if(regular.size() > 0) {
			
			this.operations = new Stack<Person>();
			currentPerson = regular.dequeue();
			operationStatus = true;
		}
		
		return operationStatus;
	}
	
}
