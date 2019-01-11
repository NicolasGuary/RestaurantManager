package ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUI extends Application {

    static Stage primaryStage = null;
	@Override
    public void start(Stage primaryStage) throws Exception{
		
		GUI.primaryStage = primaryStage;
		
        Parent root = FXMLLoader.load(getClass().getResource("views/home.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
    public void init() {
    	Router r = Router.getInstance();
    	r.add("index2", "views/consummable/index2.fxml");
    }
}