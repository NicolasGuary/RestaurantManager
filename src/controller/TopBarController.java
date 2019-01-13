package controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import facade.UserFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import ui.GUI;
import ui.Router;

public class TopBarController implements Observer {

	@FXML
	private ImageView accountImage;
	@FXML
	private Button logButton;
	@FXML
	private Label welcome;
	@FXML
	private BorderPane mainContent;
	private UserFacade uf = UserFacade.getInstance();
	private Router router = Router.getInstance() ;

	public void initialize(){
		uf.addObserver(this);
		try {
			mainContent.getChildren().add(FXMLLoader.load(getClass().getResource("../ui/views/home.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleLogin(){
		router.activate("login");
	}

	public void handleHome(){
		router.activate("home");
	}

	@Override
	public void update(Observable o, Object arg) {
		Image image;
		if(uf.getConnectedUser().isConnected()){
			image = new Image("ui/views/img/logout.png");
			accountImage.setImage(image);
			welcome.setText("Welcome " + uf.getConnectedUser().getUsername() + " !");
			logButton.setOnAction((event) -> {
				uf.logout();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Disconnect");
				alert.setHeaderText(null);
				alert.setContentText("Successfully disconnected");
				alert.showAndWait();
			});
		}
		else {
			welcome.setText("");
			image = new Image("ui/views/img/login.png");
			accountImage.setImage(image);
			logButton.setOnAction((event) -> {
				router.activate("login");
			});
		}
		
	}
}
