/*
 * 
 */
package ui;

import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The Class Router.
 */
public class Router
{
	
	/** The screen map. */
	private HashMap<String, String> screenMap = new HashMap<>();
    
     
    /**  Instance unique non pr�initialis�e. */
    private static Router INSTANCE = null;
     
    /**
     *  Point d'acc�s pour l'instance unique du singleton.
     *
     * @return single instance of Router
     */
    public static Router getInstance()
    {   
    	
    	if (INSTANCE == null)
        {   
        	INSTANCE = new Router(); 
        }
        
        return INSTANCE;
    }

    /**
     * Instantiates a new router.
     */
    private Router() {
    }

    /**
     * Adds the.
     *
     * @param name the name
     * @param pane the pane
     */
    public void add(String name, String pane){
         screenMap.put(name, pane);
    }

    /**
     * Removes the.
     *
     * @param name the name
     */
    public void remove(String name){
        screenMap.remove(name);
    }

    /**
     * Activate.
     *
     * @param name the name
     */
    public void activate(String name){ 	
    	try {
			BorderPane bp = ((BorderPane)(GUI.getPrimaryStage().getScene().lookup("#main-content")));
			bp.getChildren().clear();
			bp.getChildren().add(FXMLLoader.load(getClass().getResource(INSTANCE.getScreenMap().get(name))));
		} catch (Exception e) {	
			e.printStackTrace();
		}
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString(){
    	return INSTANCE.screenMap.toString();
    }

	/**
	 * Sets the main.
	 *
	 * @param mainScene the new main
	 */
	public void setMain(Stage mainScene) {
		GUI.setPrimaryStage(mainScene);
	}

	/**
	 * Gets the screen map.
	 *
	 * @return the screen map
	 */
	public HashMap<String, String> getScreenMap() {
		return screenMap;
	}

	
    
    
}