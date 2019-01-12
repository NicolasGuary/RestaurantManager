package controller;

import java.util.ArrayList;

import model.Consummable;
import model.Order;
import facade.ConsummableFacade;
import facade.OrderFacade;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.Router;
import javafx.scene.control.Label;

public class ReadOrderController {

	private Router router = Router.getInstance();
	private OrderFacade of = OrderFacade.getInstance();
	
	@FXML
	VBox ordersList;
	
    public void initialize() {
    	ArrayList<Order> currentSelection = of.readAll(false);
    	currentSelection.forEach((n) -> ordersList.getChildren().add(new Label(n.getIdOrder()+" "+ n.getTable().getCapacity()+ " "+ n.getPrice()+" € "+ " Table n° "+n.getTable().getNumber()))); 
    }
	
	public void readAll(String category){
		
	}
}
