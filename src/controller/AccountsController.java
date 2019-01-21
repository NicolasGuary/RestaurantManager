/*
 * @author Quentin FRANCE
 * 
 */
package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.User;
import ui.Router;
import facade.UserFacade;

/**
 * The Class AccountsController.
 */
public class AccountsController {
	
	/** The router. */
	private Router router = Router.getInstance();
	
	/** The cf. */
	private UserFacade cf = UserFacade.getInstance();
	
	/** The users list. */
	@FXML
	VBox usersList;
	
    /**
     * Initialize.
     */
    public void initialize() {
    	ArrayList<User> userList = cf.readAllUsers();
    	userList.forEach((n) -> usersList.getChildren().add(new Label(n.getUsername())));
    }
	

}
