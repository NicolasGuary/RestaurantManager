package controller;

import java.util.ArrayList;


import model.Consummable;
import model.Order;
import model.Table;
import facade.ConsummableFacade;
import facade.OrderFacade;
import facade.TableFacade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.Router;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UpdateOrderController {

	private Router router = Router.getInstance();
	private OrderFacade of = OrderFacade.getInstance();
	private TableFacade tf = TableFacade.getInstance();
	
	@FXML
	VBox ordersList;
	
	@FXML
	TextField discountInput;

	@FXML
	CheckBox isPaidInput;
	
	@FXML
	ChoiceBox<Integer> tableInput;
	
	@FXML
	TextField noteInput;
	
	Label test;
	
	private ArrayList<Table> tableList;
	
	
    public void initialize() {
    	this.tableList = tf.readAll();
    	for(Table table : tableList){
    		tableInput.getItems().add(table.getNumber());
    	}
    	// force the field to be numeric only
    	discountInput.textProperty().addListener(new ChangeListener<String>() {
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
    	        	discountInput.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    }
	
	public void create(){
		String discString = discountInput.getText();
		float discountValue = 0;
		if (!discString.isEmpty()){
			discountValue = Float.parseFloat(discountInput.getText());
		}
		boolean isPaidValue = isPaidInput.selectedProperty().get();
		int tableValue = tableInput.getValue();
		String noteValue = noteInput.getText();
		ArrayList<Consummable> consummablesOrder = new ArrayList<Consummable>();
		Table t = new Table(tableValue, 1, 0, 0, 0, false);
		//Read all fields from the view and create an Order object
    	Order order = new Order(discountValue, 0, isPaidValue, noteValue, consummablesOrder, t);
    	order.computePrice();
    	OrderFacade.getInstance().create(order);
    	router.activate("readAllOrders");
	}
	
	public void goToList(){
    	router.activate("readAllOrders");
	}
	
	public static void find() {
		//Get idOrder from view
		//OrderFacade.getInstance().find(idOrder));
	}
	public void update() {}
	public void delete() {}
}
