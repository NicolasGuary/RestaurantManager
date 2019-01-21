/*
 * @author Quentin FRANCE
 * 
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.User;
import facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.Router;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;

/**
 * The Class ReadOrderController.
 */
public class ReadUserController {

	/** The scroll P. */
	@FXML
	ScrollPane scrollP;
	
	/** The router. */
	private Router router = Router.getInstance();

	/** The user facade. */
	private UserFacade uf = UserFacade.getInstance();

    
    /**
     * Initialize.
     */
    public void initialize() {
    	readAll();
    }

	/**
	 * Read all.
	 */
	public void readAll(){
    	ArrayList<User> currentSelection = uf.readAllUsers();
    	scrollP.setHbarPolicy(ScrollBarPolicy.NEVER);
        VBox vb = new VBox();
        vb.setSpacing(10);
        scrollP.setContent(vb);
        vb.setPadding(new Insets(10, 10, 10, 10));
		for (User user: currentSelection) {
			try {
				BorderPane bp = FXMLLoader.load(getClass().getResource("../ui/views/user/singleuser.fxml"));
				Label labelFirstName = (Label) bp.lookup("#firstnameUser");
				labelFirstName.setText(user.getFirstname());
				Label labelLastName = (Label) bp.lookup("#lastnameUser");
				labelLastName.setText(user.getLastname());
				Label labelNickName = (Label) bp.lookup("#nicknameUser");
				labelNickName.setText("- " +user.getUsername());
				if(user.isSuperAdmin()){
					Label labelAdmin = (Label) bp.lookup("#admin");
					labelAdmin.setText("- ★");
				}
				Button updateButton = (Button) bp.lookup("#updateButton");
				if(uf.getConnectedUser().isSuperAdmin()){
					updateButton.setVisible(true);
					if(user.isSuperAdmin()){
						updateButton.setOnAction(new EventHandler<ActionEvent>() {
				            @Override public void handle(ActionEvent e) {
				            	uf.removePrivilege(user);
				            	readAll();
				            	Alert alert = new Alert(AlertType.INFORMATION);
				            	alert.setTitle("Success");
				            	alert.setHeaderText(null);
				            	alert.setContentText(user.getFirstname() + " "+ user.getLastname() +" is no longer Super Admin !");
				            	alert.showAndWait();
				            }
				        });
					} else {
						updateButton.setOnAction(new EventHandler<ActionEvent>() {
				            @Override public void handle(ActionEvent e) {
				            	uf.addPrivilege(user);
				            	readAll();
				            	Alert alert = new Alert(AlertType.INFORMATION);
				            	alert.setTitle("Success");
				            	alert.setHeaderText(null);
				            	alert.setContentText(user.getFirstname() + " "+ user.getLastname() +" is now Super Admin !");
				            	alert.showAndWait();
				            }
				        });
					}
		    	} else {
		    		updateButton.setVisible(false);
		    	}
				
				Button deleteBtn = (Button) bp.lookup("#deleteButton");
				deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		                uf.delete(user);
		                readAll();
		            }
		        });
				vb.getChildren().add(bp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Go to homepage
	 */
	public void goToHome(){
		router.activate("home");
	}
	
	public void createUser(){
		router.activate("createUser");
	}
}
