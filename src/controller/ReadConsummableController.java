package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.Category;
import model.Consummable;
import model.Order;
import model.Type;
import facade.ConsummableFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.Router;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;

public class ReadConsummableController {

	@FXML
	ScrollPane scrollP;
	
	private Router router = Router.getInstance();
	private ConsummableFacade cf = ConsummableFacade.getInstance();
	
	@FXML
	Accordion accordion;
	@FXML
	VBox consummablesList;
	
    public void initialize() {
    	
    	ArrayList<Type> types = cf.readAllType();
    	for(Type i : types) {
            TitledPane titledPane = new TitledPane();
            titledPane.setText(i.getNameType());
            VBox content = new VBox();
            ArrayList<Category> categories = cf.readAllCategory(i.getIdType());
            if(!categories.isEmpty()){
            	for(Category category : categories) {
            		Button link = new Button(category.getNameCategory());
            		link.setStyle(
            		        "-fx-background-color: transparent;"
            		        + "-fx-cursor : HAND"
            		    );
            		link.setOnAction(new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent e) {
                        	ArrayList<Consummable> c = cf.readAll(category.getIdCategory());
                            VBox vb = new VBox();
                            vb.setSpacing(10);
                            scrollP.setContent(vb);
                            vb.setPadding(new Insets(10, 10, 10, 10));
                    		for (Consummable consummable: c) {
                    			try {
                    				BorderPane bp = FXMLLoader.load(getClass().getResource("../ui/views/consummable/singleconsummable.fxml"));
                    				Label labelNameConsummable = (Label) bp.lookup("#nameConsummable");
                    				labelNameConsummable.setText(consummable.getNameConsummable());
                    				Label labelPrice = (Label) bp.lookup("#price");
                    				labelPrice.setText(Float.toString(consummable.getPrice()));
                    				vb.getChildren().add(bp);
                    			} catch (IOException ex) {
                    				ex.printStackTrace();
                    			}
                    		}
                        }
                    });
                    content.getChildren().add(link);
            	}
            }
            titledPane.setContent(content);
            accordion.getPanes().add(titledPane);
    		
    	}
   
    	
    	ArrayList<Consummable> currentSelection = cf.readAll();
    	scrollP.setHbarPolicy(ScrollBarPolicy.NEVER);
        VBox vb = new VBox();
        vb.setSpacing(10);
        scrollP.setContent(vb);
        vb.setPadding(new Insets(10, 10, 10, 10));
		for (Consummable consummable: currentSelection) {
			try {
				BorderPane bp = FXMLLoader.load(getClass().getResource("../ui/views/consummable/singleconsummable.fxml"));
				Label labelNameConsummable = (Label) bp.lookup("#nameConsummable");
				labelNameConsummable.setText(consummable.getNameConsummable());
				Label labelPrice = (Label) bp.lookup("#price");
				labelPrice.setText(Float.toString(consummable.getPrice()));
				vb.getChildren().add(bp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
	
	public void readAll(String category){
		
	}

}
