/*
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;

import ui.Router;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Consummable;
import model.Room;
import model.Table;
import facade.RoomFacade;
import facade.TableFacade;

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
	
	@FXML
	ScrollPane scrollP;

	@FXML
	HBox tableManag1;
	
	@FXML
	HBox tableManag2;
	
	/** The vb. */
	VBox vb;
	
	@FXML
	TextField numberTable;

	@FXML
	TextField capacityTable;

	@FXML
	TextField capacityMaxTable;
	
	/** The rf. */
	private RoomFacade rf = RoomFacade.getInstance();

	/** The tf. */
	private TableFacade tf = TableFacade.getInstance();
	
	/** The table room. */
	private ArrayList<Table> tableRoom = new ArrayList<Table>();

	/** The table list. */
	private ArrayList<Table> tableList = new ArrayList<Table>();
	
    /**
     * Initialize.
     */
    public void initialize() {
    	scrollP.setHbarPolicy(ScrollBarPolicy.NEVER);
        vb = new VBox();
        vb.setSpacing(10);
        scrollP.setContent(vb);
        vb.setPadding(new Insets(10, 10, 10, 10));
    	
    	if(rf.isUpdating()) {
    		tableList = rf.getCurrentRoom().getTables();
    		labelAction.setText("Update room");
        	nameRoomInput.setText(rf.getCurrentRoom().getName());
        	withTableInput.selectedProperty().set(rf.getCurrentRoom().isWithTables());
        	ArrayList<Table> listTable = rf.getCurrentRoom().getTables();
        	for(Table table : listTable){
        		if(table.getNumber() != 0){
            		displayTable(table);
        		}
        	}
    	} else {
    		labelAction.setText("Create a new room");
    	}
    	toggleTable();
    	
    	// force the field to be numeric only
    	capacityMaxTable.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	        	capacityMaxTable.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    	// force the field to be numeric only
    	capacityTable.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	        	capacityTable.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    	// force the field to be numeric only
    	numberTable.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	        	numberTable.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    }

    /**
     * Confirms the update or create
     */
    public void confirm(){
    	if(rf.isUpdating()){
        	String nameRoom = nameRoomInput.getText();
    		boolean withTable = withTableInput.selectedProperty().get();
    		System.out.println(tableRoom);
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
     * Display the table.
     *
     * @param table
     */
    public void displayTable(Table table){
		try {
			BorderPane bp = FXMLLoader.load(getClass().getResource("../ui/views/rooms/singletable.fxml"));
			Label labelNum = (Label) bp.lookup("#numeroTable");
			labelNum.setText(Integer.toString(table.getNumber()));
			Label labelCapacity = (Label) bp.lookup("#capacityTable");
			labelCapacity.setText(Integer.toString(table.getCapacity()));
			Label labelCapacityMax = (Label) bp.lookup("#capacityMaxTable");
			labelCapacityMax.setText(Integer.toString(table.getMaxCapacity()));
			Button removeButton = (Button) bp.lookup("#removeButton");
			removeButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
					ArrayList<Table> tableList = rf.getCurrentRoom().getTables();
					ArrayList<Table> deleteTable = new ArrayList<>();
	            	for (Table table2 : tableList) {
	            	    if (table2.getIdTable() == table.getIdTable()) {
	            	    	deleteTable.add(table2);
	            	    }
	            	 }
	            	for (Table tableToDelete : deleteTable) {
	            		tableList.remove(tableToDelete);
	            		tf.delete(tableToDelete);
	            	 }
	            	vb.getChildren().clear();
	            	for(Table tableToDisplay : tableList){
	            		if(tableToDisplay.getNumber()!=0){
		            		displayTable(tableToDisplay);
	            		}
	            	}
	            	tableRoom = tableList;
	            }
	        });
			vb.getChildren().add(bp);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Adds the table.
     *
     * @param table
     */
    public void addTable(){
    	int numberTableValue = Integer.parseInt(numberTable.getText());
    	int capacityTableValue = Integer.parseInt(capacityTable.getText());
    	int capacityMaxTableValue = Integer.parseInt(capacityMaxTable.getText());
    	if(numberTableValue != 0){
        	Table t = new Table(1, rf.getCurrentRoom().getIdRoom(), numberTableValue, capacityTableValue, capacityMaxTableValue, true);
        	Table tNew = tf.create(t);
        	displayTable(tNew);
        	this.tableList.add(tNew);
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Table number can't be 0");
    		alert.showAndWait();
    	}
    }
    
    /**
	 * Hide or show Table
	 */
	public void toggleTable(){
		if(withTableInput.selectedProperty().get()){
			tableManag1.setVisible(true);
			tableManag2.setVisible(true);
		} else {
			tableManag1.setVisible(false);
			tableManag2.setVisible(false);
		}
	}
}
