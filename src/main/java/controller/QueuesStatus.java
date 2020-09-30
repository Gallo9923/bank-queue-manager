package controller;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Bank;
import model.Person;
import ui.Main;

public class QueuesStatus extends AnchorPane {

	private Bank bank;
	public static QueuesStatus qs;
	@FXML
	private ScrollPane rScrollPane;

	@FXML
	private VBox priorityScrollPane;

	@FXML
	private VBox regularScrollPane;

	public QueuesStatus() {
		bank = Main.bank;
		qs = this;
	}

	@FXML
	private ScrollPane queueDisplay;

	@FXML
	void generateArrival(ActionEvent event) {
		bank.generateRandomArrival();
		updateQueueStatus();
		updateQueues();
	}

	private void updateQueueStatus() {
		MainMenu.getInstance().updateQueueStatus();
		
	}

	protected void updateQueues() {
		updateRegularQueue();
		updatePriorityQueue();
	}

	private void updatePriorityQueue() {
		priorityScrollPane.getChildren().clear();
		Person p = bank.peekPriorityQueue();
		
		if(p != null) {
			priorityScrollPane.getChildren().add(getPersonLabel(p));
		}
		
		
		
	}
	
	private void updateRegularQueue() {
		ArrayList<Person> persons = bank.getPeopleInRegularQueue();

		regularScrollPane.getChildren().clear();
		
		for (int i = persons.size()-1; i >= 0; i--) {
			regularScrollPane.getChildren().add(getPersonLabel(persons.get(i)));
	
		}

	}

	private Label getPersonLabel(Person p) {
		Label label = new Label(p.getName() + " " + p.getIdentification() + "\n");
		return label;
	}

	public static QueuesStatus getInstance() {
		return qs;
	}
	
}
