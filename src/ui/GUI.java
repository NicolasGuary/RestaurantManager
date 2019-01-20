/*
 * 
 */
package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * The Class GUI.
 */
public class GUI extends Application {

    /** The primary stage. */
    private static Stage primaryStage = null;
    
    /**
     * Gets the primary stage.
     *
     * @return the primary stage
     */
    public static Stage getPrimaryStage(){
    	return primaryStage;
    }
     
    /**
     * Sets the primary stage.
     *
     * @param st the new primary stage
     */
    public static void setPrimaryStage(Stage st){
    	primaryStage = st;
    }
    
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
    public void start(Stage primaryStage) throws Exception{
		GUI.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("views/index.fxml"));
        primaryStage.setTitle("Restaurant Manager");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /* (non-Javadoc)
     * @see javafx.application.Application#init()
     */
    public void init() {
    	Router r = Router.getInstance();
    	r.add("login", "views/user/login.fxml");
    	r.add("home", "views/home.fxml");
    	r.add("readAllConsummables", "views/consummable/consummables.fxml");
    	r.add("createConsummable", "views/consummable/createConsummable.fxml");
    	r.add("readAllOrders", "views/order/listorders.fxml");
    	r.add("readAllRooms", "views/rooms/listRooms.fxml");
    	r.add("readAllUsers", "views/user/listUser.fxml");
    	r.add("updateOrder", "views/order/updateOrder.fxml");
    	r.add("updateRoom", "views/rooms/updateRoom.fxml");
    	r.add("createUser", "views/user/createAccount.fxml");
    }
}