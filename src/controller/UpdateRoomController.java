/*
 * 
 */
package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Room;
import model.Table;
import facade.RoomFacade;

/**
 * The Class UpdateRoomController.
 */
public class UpdateRoomController {

	/** The name room input. */
	@FXML
	TextField nameRoomInput;
	
	/** The with table input. */
	@FXML
	CheckBox withTableInput;
	
	/** The rf. */
	private RoomFacade rf = RoomFacade.getInstance();
	
	/** The table room. */
	private ArrayList<Table> tableRoom = new ArrayList<Table>();

	
    /**
     * Initialize.
     */
    public void initialize() {}

    /**
     * Creates the.
     */
    public void create(){
    	String nameRoom = nameRoomInput.getText();
		boolean withTable = withTableInput.selectedProperty().get();
		
    	Room room = new Room(0, nameRoom, tableRoom, withTable);
    	rf.create(room);
    }
    

	/**
	 * Read all.
	 */
	public void readAll(){}
}
