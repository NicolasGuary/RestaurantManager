package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.User;
import ui.Router;
import facade.UserFacade;

public class AccountsController {
	
	private Router router = Router.getInstance();
	private UserFacade cf = UserFacade.getInstance();
	
	@FXML
	VBox usersList;
	
    public void initialize() {
    	ArrayList<User> userList = cf.readAllUsers();
    	userList.forEach((n) -> usersList.getChildren().add(new Label(n.getUsername())));
    }
	

}
