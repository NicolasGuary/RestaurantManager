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

public class LoginController {
	
	Router router = Router.getInstance();
	
	@FXML
	TextField loginTF;
	
	@FXML
	PasswordField passwordTF;
	
	@FXML
	Label confirmationMessage;

	private UserFacade uf = UserFacade.getInstance();
	
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
}