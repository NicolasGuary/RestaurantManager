/*
 * @author Quentin FRANCE
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Consummable;
import model.Order;
import facade.ConsummableFacade;
import facade.OrderFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.Router;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;

/**
 * The Class ReadOrderController.
 */
public class ReadOrderController {

	/** The scroll P. */
	@FXML
	ScrollPane scrollP;
	
	/** The router. */
	private Router router = Router.getInstance();
	
	/** The of. */
	private OrderFacade of = OrderFacade.getInstance();

	/** The test. */
	Label test;
    
    /**
     * Initialize.
     */
    public void initialize() {
    	readAll();
    	of.setUpdating(false);
    }

    /**
     * Create order.
     */
    public void createOrder(){
		router.activate("updateOrder");
    }
    
    /**
     * Update order.
     */
    public void updateOrder(Order order){
        Order res = of.read(order);
        of.setCurrentOrder(res);
		of.setUpdating(true);
		router.activate("updateOrder");
    }

	/**
	 * Read all.
	 */
	public void readAll(){
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
				Label labelPrice = (Label) bp.lookup("#price");
				labelPrice.setText(Float.toString(order.getPrice()));
				Label labelCapacity = (Label) bp.lookup("#capacity");
				labelCapacity.setText(Integer.toString(order.getTable().getCapacity()));
				Label labelIdTable = (Label) bp.lookup("#idTable");
				labelIdTable.setText(Integer.toString(order.getTable().getNumber()));
				Button updateButton = (Button) bp.lookup("#updateButton");
				updateButton.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	updateOrder(order);
		            }
		        });
				Button deleteBtn = (Button) bp.lookup("#deleteButton");
				deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		                of.delete(order);
		                readAll();
		            }
		        });
				vb.getChildren().add(bp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Go to homepage
	 */
	public void goToHome(){
		router.activate("home");
	}
}
