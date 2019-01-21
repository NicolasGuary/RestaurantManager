/*
 * @author Quentin FRANCE
 * 
 */
package controller;

import java.util.ArrayList;

import facade.ConsummableFacade;
import facade.UserFacade;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Category;
import model.Consummable;
import model.Room;
import model.Table;
import model.Type;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import ui.Router;

public class CreateConsummableController {
	
	private Router router = Router.getInstance();

	private ConsummableFacade cf = ConsummableFacade.getInstance();

	@FXML
	TextField nameConsummable;
	
	@FXML
	CheckBox existingBool;
	
	@FXML
	ChoiceBox typeChoice1;
	
	@FXML
	ChoiceBox categoryChoice1;
	
	@FXML
	ChoiceBox typeChoice2;
	
	@FXML
	CheckBox newCategBool;
	
	@FXML
	TextField categNameField;
	
	@FXML
	TextField priceField;

	/** The type list. */
	private ArrayList<Type> typeList;
	
	/** The category list. */
	private ArrayList<Category> categoryList;
	
	private Category selectedCategory;
	
	private Type selectedType;
	
    /**
     * Initialize.
     */
    public void initialize() {
    	this.typeList = cf.readAllType();
    	for(Type type : typeList){
    		typeChoice1.getItems().add(type.getNameType());
    	}

    	typeChoice1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    	      @Override
    	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
    	    	  categoryChoice1.getItems().clear();
    	    	  categoryList = cf.readAllCategory(typeList.get((Integer) number2).getIdType());
    	    	for(Category cat : categoryList){
    	    		categoryChoice1.getItems().add(cat.getNameCategory());
    	    	}
    	    	categoryChoice1.getSelectionModel().selectFirst();
    	      }
    	    });
    	
    	categoryChoice1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
  	      @Override
  	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
  	    	selectedCategory = categoryList.get((Integer) number2);
  	      }
  	    });
    	existingBool.selectedProperty().set(true);
    	
    	typeChoice2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
    	      @Override
    	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
    	    	selectedType = typeList.get((Integer) number2);
    	      }
    	    });
    	
    	for(Type type : typeList){
    		typeChoice2.getItems().add(type.getNameType());
    	}
    	
    	priceField.textProperty().addListener(new ChangeListener<String>() {
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
    	        	priceField.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    }
    
    public void handleCancel(){
    	router.activate("readAllConsummables");
    }
    
    public void confirm(){
    	String name = nameConsummable.getText();
    	if(priceField.getText().length()>0){
        	float price =  Float.parseFloat(priceField.getText());
        	Consummable cons;
        	if(existingBool.selectedProperty().get()){
        		if(!categoryChoice1.getSelectionModel().isEmpty() && name.length() > 0 && priceField.getText().length() > 0){
                	cons = new Consummable(1, selectedCategory.getIdCategory(), name, price);
                	this.cf.create(cons);
                	router.activate("readAllConsummables");
        		} else {
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Error");
            		alert.setHeaderText(null);
            		alert.setContentText("Please fill all fields");

            		alert.showAndWait();
        		}
        	} else {
        		String nameCat = categNameField.getText();
        		if(nameCat.length()>0 && selectedType != null){
            		Category cat = new Category(1, selectedType.getIdType(), nameCat);
            		Category catCreated = this.cf.createCategory(cat);
            		cons = new Consummable(1,
            				catCreated.getIdCategory(),
            				name,
            				price);
                	this.cf.create(cons);
                	router.activate("readAllConsummables");
        		} else {
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Error");
            		alert.setHeaderText(null);
            		alert.setContentText("Please fill all fields");

            		alert.showAndWait();
        		}
        	}
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please fill all fields");

    		alert.showAndWait();
    	}
   
    }
    
    public void checkExisting(){
    	existingBool.selectedProperty().set(true);
    	newCategBool.selectedProperty().set(false);
    	}
    
    public void checkNewCat(){
    	existingBool.selectedProperty().set(false);
    	newCategBool.selectedProperty().set(true);
    	}
    	   
 }
