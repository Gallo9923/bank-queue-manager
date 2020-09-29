package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserOperations extends AnchorPane implements Initializable {

	enum Operation {
		ACCOUNT_CANCELATION, WITHDRAWAL, DEPOSIT, CARD_PAYMENT, NONE
	}

	@FXML
	private Label confirmationLabel;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
		System.out.println(operationTextField.getText());
		undoBtn.setDisable(false);
		setCurrentState(Operation.NONE);
	}

	@FXML
	void undo(ActionEvent event) {

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
			confirmationLabel.setVisible(true);
			submitBtn.setDisable(false);
		} else {
			operationTextField.setVisible(false);
			confirmationLabel.setVisible(false);
			submitBtn.setDisable(true);
		}
	}

	private void enableTextField(String promptText) {
		operationTextField.setVisible(true);
		confirmationLabel.setVisible(false);
		operationTextField.setPromptText(promptText);
		operationTextField.setText("");
	}

}
