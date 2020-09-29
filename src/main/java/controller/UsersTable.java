package controller;

import java.net.URL;
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

	}

	@FXML
	void addNewRandomUser(ActionEvent event) {
		bank.createRandomClient();
		updateTableData();
	}

	@FXML
	void sort(ActionEvent event) {
		String selecteCriteria = sortByComboBox.getValue();
		System.out.println(selecteCriteria);
	}
	
	private void updateTableData() {
		ObservableList<Client> clientsOL = FXCollections.observableArrayList(bank.getClients());
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
