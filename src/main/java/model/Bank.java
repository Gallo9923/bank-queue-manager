package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import datastructures.HashTable;
import datastructures.MinHeap;
import datastructures.PriorityQueue;
import datastructures.Queue;
import datastructures.Stack;
import utils.sorting.Sorter;

public class Bank {

	private String name;
	private Queue<Person> regular;
	private ArrayList<Person> peopleInRegular;
	private PriorityQueue<Person> priority;
	private HashTable<Integer, Client> oldClients;
	private HashTable<Integer, Client> clients;
	private HashSet<Integer> clientsInBank;
	private HashSet<Integer> allClients;
	private Stack<Person> operations;
	private Person currentPerson;
	private int accountNumberCounter;

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
		accountNumberCounter = 0;
		peopleInRegular = new ArrayList<Person>();
	}

	public ArrayList<Client> getClients(){
		
		ArrayList<Client> cl = new ArrayList<Client>(this.clients.size());
		
		Iterator<Integer> iter = allClients.iterator();
		
		while(iter.hasNext()) {
			cl.add(clients.get(iter.next()));
		}
		
		return cl;
		
	}
	
	public ArrayList<Client> sortByClientName() {
		
		ArrayList<Client> cl = getClients();
		
		Sorter.mergeSort(cl, new Comparator<Client>() {
			@Override
			public int compare(Client c1, Client c2) {
				return c1.getName().compareTo(c2.getName());
			}
			
		});
		
		return cl;
	}
	
	public ArrayList<Client> sortByClientIdentification() {
		
		ArrayList<Client> cl = getClients();
		
		Sorter.mergeSort(cl, new Comparator<Client>() {
			@Override
			public int compare(Client c1, Client c2) {
				int id1 = c1.getIdentification();
				int id2 = c2.getIdentification();
				int result = 0;
				
				if(id1 - id2 < 0) {
					result = -1;
				}else if(id1 - id2 > 0) {
					result = 1;
				}
				
				return result;
			}
			
		});
		
		return cl;
	}
	
	public ArrayList<Client> sortByTimeSinceRegistration() {
		
		ArrayList<Client> cl = getClients();
		Sorter.insertionSort(cl, (c1, c2) -> {
			return c1.getRegistrationDate().compareTo(c2.getRegistrationDate());
		} ); 
		
		return cl;
	}
	
	public ArrayList<Client> sortByMoney(){
		
		ArrayList<Client> cl = getClients();
		Sorter.heapSort(cl, (c1, c2) -> {
			return Double.compare(c1.getMoney(), c2.getMoney());
		});
		
		return cl;
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
				peopleInRegular.add(0, p);
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
		return new Person(Math.abs(r.nextInt()), "Carlos", generateRandomPriority());

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
		
		if(difference.size() > 0) {
			Iterator<Integer> iter = difference.iterator();
			int element = new Random().nextInt(difference.size());

			for (int i = 0; i < element - 1; i++) {
				iter.next();
			}

			int key = iter.next();
			client = clients.get(key);
			client.clearOperations();
		}
		
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
		int identification = Math.abs(r.nextInt());
		while (clients.containsKey(identification)) {
			identification = Math.abs(r.nextInt());
		}

		Client client = new Client(accountNumberCounter, identification, "Chris", generateRandomPriority());
		accountNumberCounter++;
		// Generate Random Products

		CreditCard cc = new CreditCard(identification, 15,  Math.abs(r.nextInt(2000000)));
		DebitCard dc = new DebitCard(identification, Math.abs(r.nextInt(3000000)));
		
		client.addProduct(cc);
		client.addProduct(dc);
		
		allClients.add(identification);
		clients.put(identification, client);
		
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
		
		if(priority.size() > 0 ) {
			
			if(currentPerson != null) {
				currentPerson.setIsInLine(false);
			}
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
			if(currentPerson != null) {
				currentPerson.setIsInLine(false);
			}
			this.operations = new Stack<Person>();
			currentPerson = regular.dequeue();
			operationStatus = true;
			peopleInRegular.remove(peopleInRegular.size()-1);
		}
		
		return operationStatus;
	}
	
	public int getPriorityQueueSize() {
		return priority.size();
	}
	
	public int getRegularQueueSize() {
		return regular.size();
	}
	
	public ArrayList<Person> getPeopleInRegularQueue(){
		return peopleInRegular;
	}
	
	public Person peekPriorityQueue() {
		return priority.peek();
	}
}
