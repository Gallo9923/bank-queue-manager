package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainController implements Initializable {

	@FXML
	private JFXTextField inpName, inpEmail;

	@FXML
	private JFXPasswordField inpPassword;

	@FXML
	private JFXButton btnSignIn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void handleSignIn(ActionEvent actionEvent) {
		System.out.println(inpName.getText());
		System.out.println(inpEmail.getText());
		System.out.println(inpPassword.getText());
	}

}
