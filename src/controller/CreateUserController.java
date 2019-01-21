/*
 * @author Quentin FRANCE
 * 
 */
package controller;

import facade.UserFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import ui.Router;

/**
 * The Class UpdateTableController.
 */
public class CreateUserController {

	private UserFacade uf = UserFacade.getInstance();
	
	private Router router = Router.getInstance();

	@FXML
	TextField firstnameField;
	
	@FXML
	TextField lastnameField;
	
	@FXML
	TextField usernameField;
	
	@FXML
	TextField passwordField;
	
	@FXML
	TextField confirmPasswordField;

    /**
     * Initialize.
     */
    public void initialize() {}
    
    public void handleCancel(){
    	router.activate("readAllUsers");
    }
    
    public void confirm(){
    	String firstname = firstnameField.getText();
    	String username = usernameField.getText();
    	String lastname = lastnameField.getText();
    	String password = passwordField.getText();
    	String confirmPassword = confirmPasswordField.getText();
    	if (password.equals(confirmPassword)) {
    		if(firstname.length()>0 && username.length()>0 && lastname.length()>0 && password.length()>0){
        		uf.create(username, lastname, firstname, password);
            	router.activate("readAllUsers");
    		} else {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Error");
        		alert.setHeaderText(null);
        		alert.setContentText("Please fill all fields");

        		alert.showAndWait();
    		}
    	}
    	else{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("The passwords don't match !");

    		alert.showAndWait();
    	}
    	
    }
    
}
