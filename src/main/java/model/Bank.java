package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import datastructures.HashTable;
import datastructures.MinHeap;
import datastructures.PriorityQueue;
import datastructures.Queue;

public class Bank {

	private String name;
	private int productsCounter;
	private Queue<Person> regular;
	private PriorityQueue<Person> priority;
	private HashTable<Integer, Client> oldClients;
	private HashTable<Integer, Client> clients;
	private HashSet<Integer> clientsInBank;
	private HashSet<Integer> allClients;

	/**
	 * Creates an instance of a Bank
	 * 
	 * @param name Name of the bank
	 */
	public Bank(String name) {
		this.name = name;
		productsCounter = 0;
		clientsInBank = new HashSet<Integer>();
		allClients = new HashSet<Integer>();
		
		//TODO add queues and clients
		clients = new HashTable<Integer, Client>();
		oldClients = new HashTable<Integer, Client>();
		priority = new MinHeap<Person>(100);
		regular = new Queue<Person>();
	}

	public boolean cancelAccount(int identification) {
		
		Client client = clients.get(identification);
		clients.remove(identification);
		
		return false; // TODO
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
			p = getRandomPerson();
		}

		// If all clients are inside the bank
		if (p == null) {
			p = getRandomPerson();
		}

		// Add person to the queue
		if (p != null) {
			if (p.getPriority() == 1) {
				regular.enqueue(p);
			} else {
				priority.add(p);
			}
			operationStatus = true;
		}

		return operationStatus;
	}

	/**
	 * Returns a random person
	 * 
	 * @return Person
	 */
	private Person getRandomPerson() {
		Random r = new Random();
		return new Person(r.nextInt(), "Carlos", r.nextInt(4) + 1);

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

	/**
	 * Returns the name of the bank
	 * 
	 * @return String
	 */
	public String getName() {
		return this.name;
	}

}
