/*
 * @author Quentin FRANCE
 * 
 */
package controller;


import ui.Router;
import facade.UserFacade;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * The Class LoginController.
 */
public class LoginController {
	
	/** The router. */
	Router router = Router.getInstance();
	
	/** The login TF. */
	@FXML
	TextField loginTF;
	
	/** The password TF. */
	@FXML
	PasswordField passwordTF;
	
	/** The confirmation message. */
	@FXML
	Label confirmationMessage;

	/** The uf. */
	private UserFacade uf = UserFacade.getInstance();
	
    /**
     * Initialize.
     */
    public void initialize() {
    	//TOUCHE ENTR�E
    	loginTF.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                	handleLogin();
                }
            }
        });
    	
    	passwordTF.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                	handleLogin();
                }
            }
        });
    }

    /**
     * Handle login.
     */
    @FXML
    private void handleLogin() {
    	String strLoginTF = loginTF.getText();
    	String strPasswordTF = passwordTF.getText();
    	boolean connectionEstablised = uf.login(strLoginTF, strPasswordTF);
        if(connectionEstablised){
    		router.activate("home");
        } else {
        	confirmationMessage.setText("Connection refused !");
        	confirmationMessage.setTextFill(Color.web("red"));
        }
    }
    
    /**
     * Handle cancel.
     */
    @FXML
    private void handleCancel() {
		router.activate("home");
    }
}