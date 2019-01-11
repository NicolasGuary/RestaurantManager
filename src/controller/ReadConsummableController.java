package controller;

import java.util.ArrayList;

import model.Consummable;
import facade.ConsummableFacade;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.Router;
import javafx.scene.control.Label;

public class ReadConsummableController {

	private Router router = Router.getInstance();
	private ConsummableFacade cf = ConsummableFacade.getInstance();
	
	@FXML
	VBox consummablesList;
	
    public void initialize() {
    	ArrayList<Consummable> currentSelection = cf.readAll("");
    	System.out.println(consummablesList);
    	currentSelection.forEach((n) -> consummablesList.getChildren().add(new Label(n.getNameConsummable())));
    }
	
	public void readAll(String category){
		
	}
}
