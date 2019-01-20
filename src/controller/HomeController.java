/*
 * 
 */
package controller;

import facade.UserFacade;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import ui.Router;

/**
 * The Class HomeController.
 */
public class HomeController {

	@FXML
	Pane paneConsummable;
	
	@FXML
	Pane paneOrder;
	
	@FXML
	Pane paneRoom;

	@FXML
	Button buttonOrder;
	
	@FXML
	Button buttonConsummable;

	@FXML
	Button buttonRoom;
	
	/** The router. */
	private Router router = Router.getInstance() ;
	
	/** The uf. */
	private UserFacade uf = UserFacade.getInstance();
	
	/**
     * Initialize.
     */
    public void initialize() {
    	if(!uf.getConnectedUser().isConnected()){
    		paneConsummable.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
    		paneRoom.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
    	}
    }

	/**
	 * Handle read consummable.
	 */
	public void handleReadConsummable(){
//    	if(uf.getConnectedUser().isConnected()){
//    		router.activate("readAllConsummables");
//    	} else {
//    		Alert alert = new Alert(AlertType.INFORMATION);
//    		alert.setTitle("Error");
//    		alert.setHeaderText(null);
//    		alert.setContentText("You may connect first.");
//    		alert.showAndWait();
//    	}
    	
		router.activate("readAllConsummables");
    	
	}

	/**
	 * Handle read order.
	 */
	public void handleReadOrder(){
		router.activate("readAllOrders");
	}
	
	/**
	 * Handle read room.
	 */
	public void handleReadRoom(){
//    	if(uf.getConnectedUser().isConnected()){
//    		router.activate("readAllRooms");
//    	} else {
//    		Alert alert = new Alert(AlertType.INFORMATION);
//    		alert.setTitle("Error");
//    		alert.setHeaderText(null);
//    		alert.setContentText("You may connect first.");
//    		alert.showAndWait();
//    	}
		router.activate("readAllRooms");
	}
}
