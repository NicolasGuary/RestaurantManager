/*
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Order;
import model.Room;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.Router;
import facade.OrderFacade;
import facade.RoomFacade;

/**
 * The Class ReadRoomController.
 */
public class ReadRoomController {

	/** The scroll P. */
	@FXML
	ScrollPane scrollP;
	
	/** The router. */
	private Router router = Router.getInstance();
	
	/** The rf. */
	private RoomFacade rf = RoomFacade.getInstance();

    /**
     * Initialize.
     */
    public void initialize() {
    	readAll();
    	rf.setUpdating(false);
    	}

	/**
	 * Read all.
	 */
	public void readAll(){
    	ArrayList<Room> currentSelection = rf.readAll();
    	scrollP.setHbarPolicy(ScrollBarPolicy.NEVER);
        VBox vb = new VBox();
        vb.setSpacing(10);
        scrollP.setContent(vb);
        vb.setPadding(new Insets(10, 10, 10, 10));
		for (Room room: currentSelection) {
			try {
				BorderPane bp = FXMLLoader.load(getClass().getResource("../ui/views/rooms/singleroom.fxml"));
				Label labelNameRoom = (Label) bp.lookup("#nameRoom");
				labelNameRoom.setText(room.getName());
				if(room.isWithTables()){
					String t = "tables";
					if(room.getNbTables() <2){
						t = "table";
					}
					Label labelTable = (Label) bp.lookup("#nbTable");
					labelTable.setText(Integer.toString(room.getNbTables()) + " " + t);
				}
				Button updateBtn = (Button) bp.lookup("#updateButton");
				updateBtn.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		        		Room res = rf.read(room);
		                rf.setCurrentRoom(res);
		        		rf.setUpdating(true);
		        		router.activate("updateRoom");
		            }
		        });
				Button deleteBtn = (Button) bp.lookup("#deleteButton");
				deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		                rf.delete(room);
		                readAll();
		            }
		        });
				vb.getChildren().add(bp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Update room.
	 */
	public void updateRoom(){
		router.activate("updateRoom");
    }
	
	/**
	 * Go to homepage
	 */
	public void goToHome(){
		router.activate("home");
	}
}
