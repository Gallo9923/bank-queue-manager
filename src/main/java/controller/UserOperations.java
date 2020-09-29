package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Bank;
import model.Client;
import model.Person;
import ui.Main;

public class UserOperations extends AnchorPane implements Initializable {

	enum Operation {
		ACCOUNT_CANCELATION, WITHDRAWAL, DEPOSIT, CARD_PAYMENT, NONE
	}

	public static UserOperations up;

	@FXML
	private JFXButton withdrawalBtn;

	@FXML
	private JFXButton depositBtn;

	@FXML
	private JFXButton accountCancelationBtn;

	@FXML
	private JFXButton cardPaymentBtn;

	@FXML
	private JFXTextArea description;

	@FXML
	private JFXTextField operationTextField;

	@FXML
	private AnchorPane operationBox;

	@FXML
	private JFXButton undoBtn;

	@FXML
	private JFXButton submitBtn;

	@FXML
	private Label idLabel;

	@FXML
	private Label nameLabel;

	@FXML
	private Label accountLabel;

	@FXML
	private Label cashLabel;

	@FXML
	private Label debtLabel;

	@FXML
	private Label paymentDateLabel;

	@FXML
	private Label enrollmentDateLabel;

	private Operation currentOperation;

	private Bank bank;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bank = Main.bank;
		up = this;
		currentOperation = Operation.NONE;
		undoBtn.setDisable(true);
		setCurrentState(Operation.NONE);

		NumberValidator numVal = new NumberValidator("Must be a number");
		operationTextField.getValidators().add(numVal);

		operationTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue)
				operationTextField.validate();
		});

	}

	@FXML
	void accountCancelation(ActionEvent event) {
		setCurrentState(Operation.ACCOUNT_CANCELATION);
	}

	@FXML
	void cardPayment(ActionEvent event) {
		setCurrentState(Operation.CARD_PAYMENT, "Type the amount of your cardPayment");
	}

	@FXML
	void deposit(ActionEvent event) {
		setCurrentState(Operation.DEPOSIT, "Type the amount of your deposit");
	}

	@FXML
	void withdrawal(ActionEvent event) {
		setCurrentState(Operation.WITHDRAWAL, "Type the amount of your withdrawal");
	}

	@FXML
	void submit(ActionEvent event) {
	
		try {
			double amount = Double.parseDouble(operationTextField.getText());
			switch (currentOperation) {
			case NONE:
				break;
			case ACCOUNT_CANCELATION:
				bank.cancelAccount(bank.getCurrentPerson().getIdentification(), "No reason", LocalDate.now());
				break;
			case WITHDRAWAL:
				bank.withdraw(amount);
				break;
			case DEPOSIT:
				bank.deposit(amount);
				break;
			case CARD_PAYMENT:
				bank.payCreditCard(amount);
				break;
			}
			
			updatePersonInformation();
			undoBtn.setDisable(false);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		setCurrentState(Operation.NONE);
	}


	@FXML
	void undo(ActionEvent event) {
		bank.undoOperation();
		updatePersonInformation();
		undoBtn.setDisable(false);
	}

	private void setCurrentState(Operation op, String promptText) {
		currentOperation = op;
		if (currentOperation != Operation.NONE && currentOperation != Operation.ACCOUNT_CANCELATION) {
			enableTextField(promptText);
			submitBtn.setDisable(false);
		}
	}

	private void setCurrentState(Operation op) {
		this.currentOperation = op;

		if (currentOperation == Operation.ACCOUNT_CANCELATION) {
			operationTextField.setVisible(false);
			description.setVisible(true);
			submitBtn.setDisable(false);
		} else {
			operationTextField.setVisible(false);
			description.setVisible(false);
			submitBtn.setDisable(true);
		}
	}

	private void enableTextField(String promptText) {
		operationTextField.setVisible(true);
		description.setVisible(false);
		operationTextField.setPromptText(promptText);
		operationTextField.setText("");
	}

	public void updatePersonInformation() {
		Person currentPerson = bank.getCurrentPerson();

		if (currentPerson == null) {
			idLabel.setText("None");
			nameLabel.setText("None");
			accountLabel.setText("None");
			enrollmentDateLabel.setText("None");
			cashLabel.setText("None");
			debtLabel.setText("None");
			paymentDateLabel.setText("None");

			disableOperationButttons();

		} else {

			if (currentPerson instanceof Client) {

				Client currentClient = (Client) currentPerson;
				idLabel.setText(currentClient.getIdentification() + "");
				nameLabel.setText(currentClient.getName());
				accountLabel.setText(currentClient.getAccountNumber() + "");
				enrollmentDateLabel.setText(currentClient.getRegistrationDate().toString());
				cashLabel.setText(currentClient.getMoney() + "");
				debtLabel.setText(currentClient.getDebt() + "");
				paymentDateLabel.setText(currentClient.getPaymentDate() + "");

				enableOperationButtons();

			} else {
				idLabel.setText(currentPerson.getIdentification() + "");
				nameLabel.setText(currentPerson.getName());
				accountLabel.setText("None");
				enrollmentDateLabel.setText("None");
				cashLabel.setText("None");
				debtLabel.setText("None");
				paymentDateLabel.setText("None");

				disableOperationButttons();
			}

		}
	}

	private void enableOperationButtons() {
		withdrawalBtn.setDisable(false);
		depositBtn.setDisable(false);
		accountCancelationBtn.setDisable(false);
		cardPaymentBtn.setDisable(false);
		submitBtn.setDisable(false);
		undoBtn.setDisable(true);
	}

	private void disableOperationButttons() {
		withdrawalBtn.setDisable(true);
		depositBtn.setDisable(true);
		accountCancelationBtn.setDisable(true);
		cardPaymentBtn.setDisable(true);
		submitBtn.setDisable(true);
		undoBtn.setDisable(true);
	}

	public static UserOperations getInstance() {
		return up;
	}

}
