package controller;

import java.io.IOException;
import java.util.ArrayList;

import model.Order;
import model.Room;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ui.Router;
import facade.OrderFacade;
import facade.RoomFacade;

public class ReadRoomController {

	@FXML
	ScrollPane scrollP;
	private Router router = Router.getInstance();
	private RoomFacade rf = RoomFacade.getInstance();

    public void initialize() {
    	readAll();
    	}

	public void readAll(){
    	ArrayList<Room> currentSelection = rf.readAll();
    	scrollP.setHbarPolicy(ScrollBarPolicy.NEVER);
        VBox vb = new VBox();
        vb.setSpacing(10);
        scrollP.setContent(vb);
        vb.setPadding(new Insets(10, 10, 10, 10));
		for (Room room: currentSelection) {
			try {
				BorderPane bp = FXMLLoader.load(getClass().getResource("../ui/views/rooms/singleroom.fxml"));
				Label labelNameRoom = (Label) bp.lookup("#nameRoom");
				labelNameRoom.setText(room.getName());
				Label labelPrice = (Label) bp.lookup("#nbTable");
				String t = "tables";
				if(room.getNbTables() <2){
					t = "table";
				}
				labelPrice.setText(Integer.toString(room.getNbTables()) + " " + t);
				Button deleteBtn = (Button) bp.lookup("#deleteButton");
				deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		                rf.delete(room);
		                readAll();
		            }
		        });
				vb.getChildren().add(bp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateRoom(){
		router.activate("updateRoom");
    }
}
