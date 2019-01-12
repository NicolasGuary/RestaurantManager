package controller;

import java.util.ArrayList;
import java.util.PrimitiveIterator.OfDouble;

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
	
	public void create(){}
	
	public static void main(String[] args) {
		ArrayList<Consummable> consummablesOrder = new ArrayList<>();
    	Consummable consummable = new Consummable(10, "Carottes", (float) 1.2);
    	Consummable c2 = new Consummable(11, "Chou", (float) 1.2);
    	consummablesOrder.add(consummable);
    	consummablesOrder.add(c2);
    	float discount = (float)1.2;
    	float price = (float)1;
    	boolean paid = false;
    	String note = "Test insert";
    	Table table = new Table(2, 2, 3, 3, true);
    	OrderFacade.getInstance().create(discount, price, paid, note, consummablesOrder, table);
    }
}
