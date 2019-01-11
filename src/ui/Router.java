package ui;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
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
			GUI.primaryStage.getScene().setRoot(FXMLLoader.load(getClass().getResource(INSTANCE.getScreenMap().get(name))));
		} catch (IOException e) {	
			e.printStackTrace();
		}
    }
    
    public String toString(){
    	return INSTANCE.screenMap.toString();
    }

	public void setMain(Stage mainScene) {
		GUI.primaryStage = mainScene;
	}

	public HashMap<String, String> getScreenMap() {
		return screenMap;
	}

	
    
    
}