package controller;

import java.util.ArrayList;


import model.Consummable;
import model.Order;
import model.Table;
import facade.ConsummableFacade;
import facade.OrderFacade;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.Router;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class UpdateOrderController {

	private Router router = Router.getInstance();
	private OrderFacade of = OrderFacade.getInstance();
	
	@FXML
	VBox ordersList;
	Label test;
    public void initialize() {
    	ArrayList<Order> currentSelection = of.readAll(false);
    	currentSelection.forEach((n) -> ordersList.getChildren().add(new Pane(new Label(n.getIdOrder()+" "+ n.getTable().getCapacity()+ new ImageView("ui/views/img/user.png")+ " "+ n.getPrice()+" € "+ " Table n° "+n.getTable().getNumber())))); 
    }
	
	public void create(){
		//Read all fields from the view and create an Order object
    	//Order order = new Order(discount, price, paid, note, consummablesOrder, table);
    	//OrderFacade.getInstance().create(order);
	}
	public static void find() {
		//Get idOrder from view
		//OrderFacade.getInstance().find(idOrder));
	}
	public void update() {}
	public void delete() {}
}
