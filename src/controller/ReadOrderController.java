package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Consummable;
import model.Order;
import facade.ConsummableFacade;
import facade.OrderFacade;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.Router;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;

public class ReadOrderController {

	@FXML
	ScrollPane scrollP;
	private Router router = Router.getInstance();
	private OrderFacade of = OrderFacade.getInstance();
	
	Label test;
    public void initialize() {
    	ArrayList<Order> currentSelection = of.readAll(false);
    	scrollP.setHbarPolicy(ScrollBarPolicy.NEVER);
        VBox vb = new VBox();
        vb.setSpacing(10);
        scrollP.setContent(vb);
        vb.setPadding(new Insets(10, 10, 10, 10));
		for (Order order: currentSelection) {
			try {
				BorderPane bp = FXMLLoader.load(getClass().getResource("../ui/views/order/singleorder.fxml"));
				Label labelIdOrder = (Label) bp.lookup("#idOrder");
				labelIdOrder.setText(Integer.toString(order.getIdOrder()));
				Label labelCapacity = (Label) bp.lookup("#capacity");
				labelCapacity.setText(Integer.toString(order.getTable().getCapacity()));
				Label labelIdTable = (Label) bp.lookup("#idTable");
				labelIdTable.setText(Integer.toString(order.getTable().getNumber()));
				vb.getChildren().add(bp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//        currentSelection.forEach((n) -> 
//        	vb.getChildren().add(new Pane(new Label(n.getIdOrder()+" "+ n.getTable().getCapacity()+ new ImageView("ui/views/img/user.png")+ " "+ n.getPrice()+" € "+ " Table n° "+n.getTable().getNumber()))));
    }
    
    public void addOrder(){
    	
    }
	
	public void readAll(String category){
		
	}
}
