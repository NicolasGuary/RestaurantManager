package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Room;
import model.Table;
import facade.RoomFacade;

public class UpdateRoomController {

	@FXML
	TextField nameRoomInput;
	@FXML
	CheckBox withTableInput;
	private RoomFacade rf = RoomFacade.getInstance();
	private ArrayList<Table> tableRoom = new ArrayList<Table>();

	
    public void initialize() {}

    public void create(){
    	String nameRoom = nameRoomInput.getText();
		boolean withTable = withTableInput.selectedProperty().get();
		
    	Room room = new Room(0, nameRoom, tableRoom, withTable);
    	rf.create(room);
    }
    

	public void readAll(){}
}
