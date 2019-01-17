package ui;

import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Router
{
	
	private HashMap<String, String> screenMap = new HashMap<>();
    
     
    /** Instance unique non pr�initialis�e */
    private static Router INSTANCE = null;
     
    /** Point d'acc�s pour l'instance unique du singleton */
    public static Router getInstance()
    {   
    	
    	if (INSTANCE == null)
        {   
        	INSTANCE = new Router(); 
        }
        
        return INSTANCE;
    }

    private Router() {
    }

    public void add(String name, String pane){
         screenMap.put(name, pane);
    }

    public void remove(String name){
        screenMap.remove(name);
    }

    public void activate(String name){ 	
    	try {
			BorderPane bp = ((BorderPane)(GUI.getPrimaryStage().getScene().lookup("#main-content")));
			bp.getChildren().clear();
			bp.getChildren().add(FXMLLoader.load(getClass().getResource(INSTANCE.getScreenMap().get(name))));
		} catch (Exception e) {	
			e.printStackTrace();
		}
    }
    
    public String toString(){
    	return INSTANCE.screenMap.toString();
    }

	public void setMain(Stage mainScene) {
		GUI.setPrimaryStage(mainScene);
	}

	public HashMap<String, String> getScreenMap() {
		return screenMap;
	}

	
    
    
}