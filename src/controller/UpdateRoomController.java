/*
 * 
 */
package controller;

import java.util.ArrayList;

import ui.Router;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Room;
import model.Table;
import facade.RoomFacade;

/**
 * The Class UpdateRoomController.
 */
public class UpdateRoomController {

	/** The router. */
	private Router router = Router.getInstance();
	
	/** The name room input. */
	@FXML
	TextField nameRoomInput;
	
	/** The with table input. */
	@FXML
	CheckBox withTableInput;
	
	@FXML
	Label labelAction;
	
	/** The rf. */
	private RoomFacade rf = RoomFacade.getInstance();
	
	/** The table room. */
	private ArrayList<Table> tableRoom = new ArrayList<Table>();

	
    /**
     * Initialize.
     */
    public void initialize() {
    	if(rf.isUpdating()){
    		labelAction.setText("Update room");
        	nameRoomInput.setText(rf.getCurrentRoom().getName());
        	withTableInput.selectedProperty().set(rf.getCurrentRoom().isWithTables());
    	} else {
    		labelAction.setText("Create a new room");
    	}
    }

    /**
     * Creates the.
     */
    public void confirm(){
    	if(rf.isUpdating()){
        	String nameRoom = nameRoomInput.getText();
    		boolean withTable = withTableInput.selectedProperty().get();
        	Room room = new Room(rf.getCurrentRoom().getIdRoom(), nameRoom, tableRoom, withTable);
        	rf.update(room);
        	router.activate("readAllRooms");
    	} else {
        	String nameRoom = nameRoomInput.getText();
    		boolean withTable = withTableInput.selectedProperty().get();
    		
        	Room room = new Room(0, nameRoom, tableRoom, withTable);
        	rf.create(room);
        	router.activate("readAllRooms");
    	}
    }
    
    /**
	 * Go to list.
	 */
	public void goToList(){
    	router.activate("readAllRooms");
	}

	/**
	 * Read all.
	 */
	public void readAll(){}
}
