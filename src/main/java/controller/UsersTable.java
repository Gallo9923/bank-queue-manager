package controller;

import javafx.scene.control.Label;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Bank;
import model.Client;
import ui.Main;

public class UsersTable extends AnchorPane implements Initializable {

	private Bank bank;

	@FXML
	private TableView<Client> clientsTable;

	@FXML
	private TableColumn<Client, Integer> idColumn;

	@FXML
	private TableColumn<Client, String> nameColumn;

	@FXML
	private TableColumn<Client, String> timeColumn;

	@FXML
	private TableColumn<Client, Double> cashColumn;

	@FXML
	private JFXComboBox<String> sortByComboBox;

	@FXML
	private JFXComboBox<String> sortByOrder;

	@FXML
	private Label idLabell;

	@FXML
	private Label nameLabell;

	@FXML
	private Label accountLabell;

	@FXML
	private Label cashLabell;

	@FXML
	private Label debtLabell;

	@FXML
	private Label paymentDateLabell;

	@FXML
	private Label enrollmentDateLabell;

	@FXML
	private JFXTextField idToSearch;

	public UsersTable() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.bank = Main.bank;
		sortByComboBox.getItems().add("Id");
		sortByComboBox.getItems().add("Name");
		sortByComboBox.getItems().add("Date");
		sortByComboBox.getItems().add("Cash");
		sortByComboBox.getSelectionModel().selectFirst(); // Way 1

		sortByOrder.getItems().add("Ascending");
		sortByOrder.getItems().add("Descending");
		sortByOrder.setValue("Descending"); // Way 2

		updateTableData(bank.getClients());

	}
	
	@FXML
	void searchClient(ActionEvent event) {
		
		try {
			int id = Integer.parseInt(idToSearch.getText());
			
			if(id < 0) {
				errorMessage();
			}else {
				Client client = bank.searchClient(id);
				
				if(client != null) {
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
					
					idLabell.setText(client.getIdentification() + "");
					nameLabell.setText(client.getName());
					accountLabell.setText(client.getAccountNumber() + "");
					enrollmentDateLabell.setText(client.getRegistrationDate() + "");
					cashLabell.setText(formatter.format(client.getMoney()) + "");
					debtLabell.setText(formatter.format(client.getDebt()) + "");
					paymentDateLabell.setText(client.getPaymentDate() + "");
				}else {
					idLabell.setText("None");
					nameLabell.setText("None");
					accountLabell.setText("None");
					enrollmentDateLabell.setText("None");
					cashLabell.setText("None");
					debtLabell.setText("None");
					paymentDateLabell.setText("None");
				}
			}
			
		}catch (NumberFormatException e) {
			errorMessage();
		}
		
		
	}
	
	private void errorMessage() {
		ButtonType custom_OK_Button = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
		Alert alert = new Alert(AlertType.INFORMATION, "The id must be a positive Integer", custom_OK_Button);
		alert.setTitle("Alert!");
		alert.setHeaderText("Input format mismatch");
		alert.showAndWait();
	}

	@FXML
	void addNewRandomUser(ActionEvent event) {
		bank.createRandomClient();
		updateTableData(bank.getClients());
	}

	@FXML
	void sort(ActionEvent event) {
		String selectedCriteria = sortByComboBox.getValue();
		String sortOrder = sortByOrder.getValue();

		if (selectedCriteria != null && sortOrder != null) {

			boolean descending = false;
			if (sortOrder.equals("Descending")) {
				descending = true;
			}

			ArrayList<Client> clients = null;
			switch (selectedCriteria) {
			case "Id":
				clients = bank.sortByClientIdentification(descending);
				break;
			case "Name":
				clients = bank.sortByClientName(descending);
				break;
			case "Date":
				clients = bank.sortByTimeSinceRegistration(descending);
				break;
			case "Cash":
				clients = bank.sortByMoney(descending);
				break;
			}

			if (clients != null) {
				updateTableData(clients);
			}
		}

	}

	private void updateTableData(ArrayList<Client> clients) {
		ObservableList<Client> clientsOL = FXCollections.observableArrayList(clients);
		clientsTable.setItems(clientsOL);

		idColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("Identification"));
		idColumn.setStyle("-fx-alignment: CENTER");

		nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		nameColumn.setStyle("-fx-alignment: CENTER");

		timeColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("timeSinceRegistration"));
		timeColumn.setStyle("-fx-alignment: CENTER");

		cashColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("MoneyFormatted"));
		cashColumn.setStyle("-fx-alignment: CENTER");
	}

}
