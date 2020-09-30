package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

	public UsersTable() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.bank = Main.bank;
		sortByComboBox.getItems().add("Id");
		sortByComboBox.getItems().add("Name");
		sortByComboBox.getItems().add("Date");
		sortByComboBox.getItems().add("Cash");
		
		
		
		updateTableData(bank.getClients());
		
	}

	@FXML
	void addNewRandomUser(ActionEvent event) {
		bank.createRandomClient();
		updateTableData(bank.getClients());
	}

	@FXML
	void sort(ActionEvent event) {
		String selectedCriteria = sortByComboBox.getValue();
		
		if(selectedCriteria != null) {
			ArrayList<Client> clients = null;
			switch(selectedCriteria) {
				case "Id":
					clients = bank.sortByClientIdentification();
					break;
				case "Name":
					clients = bank.sortByClientName();
					break;
				case "Date":
					clients = bank.sortByTimeSinceRegistration();
					break;
				case "Cash":
					clients = bank.sortByMoney();
					break;
			}
			
			if(clients != null) {
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
		
		cashColumn.setCellValueFactory(new PropertyValueFactory<Client, Double>("money"));
		cashColumn.setStyle("-fx-alignment: CENTER");
	}
	

}
