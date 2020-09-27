package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class UsersTable extends AnchorPane implements Initializable {
	
    @FXML
    private JFXComboBox<String> sortByComboBox;
	
	public UsersTable() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sortByComboBox.getItems().add("Id");
		sortByComboBox.getItems().add("Name");
		sortByComboBox.getItems().add("Date");
		sortByComboBox.getItems().add("Cash");

	}
	
    @FXML
    void addNewUser(ActionEvent event) {
    	
    }


    @FXML
    void sort(ActionEvent event) {
    	String selecteCriteria = sortByComboBox.getValue();
    	System.out.println(selecteCriteria);
    }

}
