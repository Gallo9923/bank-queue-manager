package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import datastructures.HashTable;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu implements Initializable {

	@FXML
	private AnchorPane backgroundAnchorPane;

	@FXML
	private StackPane parentContainer;

	@FXML
	private AnchorPane currentSection;

	@FXML
	private VBox mainMenu;

	@FXML
	private JFXButton nextTurnPriority;

	@FXML
	private JFXButton nextTurnRegular;

	@FXML
	private Label currentTurnPriorityQueue;

	@FXML
	private Label currentTurnRegularQueue;

	@FXML
	private JFXButton visualizeUserTable;

	@FXML
	private JFXButton visualizeQueueStatus;

	@FXML
	private JFXButton userOperations;

	@FXML
	private JFXButton exitButton;

	private Pane usersTableScene;
	private Pane usersOperationScene;
	private Pane queuesStatusScene;
	private Pane currentScene;
	private HashTable<Pane, Button> scene2Button;

	public MainMenu() {
		try {
			this.usersTableScene = (Pane) FXMLLoader.load(getClass().getResource("/fxml/UsersTable.fxml"));

			this.usersOperationScene = (Pane) FXMLLoader.load(getClass().getResource("/fxml/UserOperations.fxml"));

			this.queuesStatusScene = (Pane) FXMLLoader.load(getClass().getResource("/fxml/QueuesStatus.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.currentScene = usersTableScene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scene2Button = new HashTable<>(4, 1);
		scene2Button.put(usersTableScene, visualizeUserTable);
		scene2Button.put(usersOperationScene, userOperations);
		scene2Button.put(queuesStatusScene, visualizeQueueStatus);

		this.parentContainer.getChildren().add(currentScene);
		scene2Button.get(currentScene).setStyle("-fx-background-color:grey;");
	}

	@FXML
	void switch2QueuesStatus(ActionEvent event) {
		switch2(visualizeQueueStatus, queuesStatusScene);
	}

	@FXML
	void switch2UserOperations(ActionEvent event) {
		switch2(userOperations, usersOperationScene);
	}

	@FXML
	void switch2UsersTable(ActionEvent event) {
		switch2(visualizeUserTable, usersTableScene);
	}

	private void switch2(Button btn, Pane root) {
		ObservableList<Node> children = parentContainer.getChildren();

		if (!children.contains(root) && currentScene != root) {
			Scene scene = btn.getScene();
			scene2Button.get(currentScene).setStyle("-fx-background-color:transparent;");
			slide(currentScene, 0, -(scene.getWidth()) + 200, 0.25, e1 -> { // Slides the current scene from right to
																			// left
				children.add(root);
				double xPropertyValue = -scene.getWidth(); // From left to right
				// Slides the next scene from left to right
				slide(root, xPropertyValue, 0, 0.25, e2 -> children.remove(1));
				scene2Button.get(root).setStyle("-fx-background-color:grey;");
			});

			currentScene = root;
		}
	}

	private void slide(Pane root, double xPropertyValue, double endValue, double durationSecs,
			EventHandler<ActionEvent> e) {
		root.translateXProperty().set(xPropertyValue);

		Timeline timeline = new Timeline();
		KeyValue kv1 = new KeyValue(root.translateXProperty(), endValue, Interpolator.EASE_IN);
		KeyFrame kf2 = new KeyFrame(Duration.seconds(durationSecs), kv1);
		timeline.getKeyFrames().add(kf2);
		timeline.setOnFinished(e);
		timeline.play();
	}

	@FXML
	void maximizeWindow(ActionEvent event) {
		Stage stage = (Stage) backgroundAnchorPane.getScene().getWindow();
		if (stage.isFullScreen())
			stage.setFullScreen(false);
		else
			stage.setFullScreen(true);

	}

	@FXML
	void minimizeWindow(ActionEvent event) {
		Stage stage = (Stage) backgroundAnchorPane.getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	void exit(ActionEvent event) {
		System.exit(0);
	}

}
