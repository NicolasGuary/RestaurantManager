/*
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Consummable;
import model.Order;
import model.Room;
import model.Table;
import facade.ConsummableFacade;
import facade.OrderFacade;
import facade.RoomFacade;
import facade.TableFacade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;

/**
 * The Class UpdateOrderController.
 */
public class UpdateOrderController {

	/** The router. */
	private Router router = Router.getInstance();
	
	/** The cf. */
	private ConsummableFacade cf = ConsummableFacade.getInstance();
	
	/** The of. */
	private OrderFacade of = OrderFacade.getInstance();

	/** The rf. */
	private RoomFacade rf = RoomFacade.getInstance();
	
	/** The tf. */
	private TableFacade tf = TableFacade.getInstance();
	
	/** The consummables order. */
	private ArrayList<Consummable> consummablesOrder = new ArrayList<Consummable>();

	/** The orders list. */
	@FXML
	VBox ordersList;
	
	/** The discount input. */
	@FXML
	TextField discountInput;

	/** The is paid input. */
	@FXML
	CheckBox isPaidInput;
	
	/** The table input. */
	@FXML
	ChoiceBox<Integer> tableInput;

	/** The table input. */
	@FXML
	ChoiceBox<String> roomInput;
	
	/** The note input. */
	@FXML
	TextField noteInput;

	/** The scroll P. */
	@FXML
	ScrollPane scrollP; 
	
	/** The vb. */
	VBox vb;
	
	/** The test. */
	Label test;
	
	@FXML
	Label labelAction;

	/** The table list. */
	private ArrayList<Room> roomList;
	
	/** The table list. */
	private ArrayList<Table> tableList;
	
	
    /**
     * Initialize.
     */
    public void initialize() {
    	scrollP.setHbarPolicy(ScrollBarPolicy.NEVER);
        vb = new VBox();
        vb.setSpacing(10);
        scrollP.setContent(vb);
        vb.setPadding(new Insets(10, 10, 10, 10));
    	this.tableList = tf.readAll();
    	for(Table table : tableList){
    		tableInput.getItems().add(table.getNumber());
    	}
    	this.roomList = rf.readAll();
    	for(Room room : roomList){
    		roomInput.getItems().add(room.getName());
    	}
    	roomInput.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    	      @Override
    	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
    	    	tableInput.getItems().clear();
    	        tableList = tf.readAll(roomList.get((Integer) number2).getIdRoom());
    	    	for(Table table : tableList){
    	    		tableInput.getItems().add(table.getNumber());
    	    	}
    	      }
    	    });
    	// force the field to be numeric only
    	discountInput.textProperty().addListener(new ChangeListener<String>() {
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
    	        	discountInput.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    	if(of.isUpdating()){
    		labelAction.setText("Update Order");
    		Order order = of.getCurrentOrder();
    		if((order.getConsummablesOrder().size()>0) && (order.getConsummablesOrder().get(0) != null)){
        		for(Consummable consummable : order.getConsummablesOrder()){
            		addConsummable(consummable);
            	}
    		}
    		discountInput.setText(Float.toString(order.getDiscount()));
    		isPaidInput.selectedProperty().set(order.isPaid());
    		tableInput.setValue(order.getTable().getNumber());
    		noteInput.setText(order.getNote());
    	} else {
    		labelAction.setText("Create Order");
    	}
    }
    
    /**
     * Show add consummable dialog.
     */
    public void showAddConsummableDialog(){
    	List<Consummable> consummableChoice = new ArrayList<>();
    	ArrayList<Consummable> consummableList = cf.readAll();
    	for(Consummable consummable : consummableList){
    		consummableChoice.add(consummable);
    	}

    	ChoiceDialog<Consummable> dialog = new ChoiceDialog<>(null, consummableChoice);
    	dialog.setTitle("Add consummable");
    	dialog.setHeaderText("Add a consummable to your order");
    	dialog.setContentText("Choose :");

    	Optional<Consummable> result = dialog.showAndWait();
    	result.ifPresent(cons -> addConsummable(cons));
    }
    
    /**
     * Adds the consummable.
     *
     * @param cons the cons
     */
    public void addConsummable(Consummable cons){
		try {
			BorderPane bp = FXMLLoader.load(getClass().getResource("../ui/views/order/singleconsummableorder.fxml"));
			Label labelIdOrder = (Label) bp.lookup("#nameConsummable");
			labelIdOrder.setText(cons.getNameConsummable());
			Label labelPriceC = (Label) bp.lookup("#priceConsummable");
			labelPriceC.setText("(" +cons.getPrice() + "e)");
			vb.getChildren().add(bp);
			consummablesOrder.add(cons);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    /**
     *  Confirms the update or create
     */
    public void confirm(){
    	if(this.tableInput.getValue() != null){
        	if(of.isUpdating()){
            	update();
        	} else {
        		create();
        	}
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please fill the table input");

    		alert.showAndWait();
    	}
    }
    
	/**
	 * Creates the update
	 */
	public void create(){
		String discString = discountInput.getText();
		float discountValue = 0;
		if (!discString.isEmpty()){
			discountValue = Float.parseFloat(discountInput.getText());
		}
		boolean isPaidValue = isPaidInput.selectedProperty().get();
		int tableValue = tableInput.getValue();
		String noteValue = noteInput.getText();
		Table t = new Table(tableValue, 1, 0, 0, 0, false);
		//Read all fields from the view and create an Order object
    	Order order = new Order(discountValue, 0, isPaidValue, noteValue, consummablesOrder, t);
    	order.computePrice();
    	OrderFacade.getInstance().create(order);
    	router.activate("readAllOrders");
	}
	
	/**
	 * Go to list.
	 */
	public void goToList(){
    	router.activate("readAllOrders");
	}
	
	/**
	 * Find.
	 */
	public static void find() {
		//Get idOrder from view
		//OrderFacade.getInstance().find(idOrder));
	}
	
	/**
	 * Update.
	 */
	public void update() {
		String discString = discountInput.getText();
		float discountValue = 0;
		if (!discString.isEmpty()){
			discountValue = Float.parseFloat(discountInput.getText());
		}
		boolean isPaidValue = isPaidInput.selectedProperty().get();
		int tableValue = tableInput.getValue();
		String noteValue = noteInput.getText();
		Table t = new Table(tableValue, 1, 0, 0, 0, false);
		//Read all fields from the view and create an Order object
		Order order = new Order(of.getCurrentOrder().getIdOrder(), discountValue, 0, isPaidValue, noteValue, consummablesOrder, t);
		order.computePrice();
		OrderFacade.getInstance().update(order);
		router.activate("readAllOrders");
	}
	
	/**
	 * Delete.
	 */
	public void delete() {}
}
