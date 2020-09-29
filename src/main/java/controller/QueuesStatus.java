package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class QueuesStatus extends AnchorPane {

    @FXML
    private ScrollPane priorityScrollPane;

    @FXML
    private ScrollPane regularScrollPane;
	
	public QueuesStatus() {
		
	}
	
    @FXML
    private ScrollPane queueDisplay;

    @FXML
    void generateArrival(ActionEvent event) {
    		
    }
}
