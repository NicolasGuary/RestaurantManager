/*
 * 
 */
package facade;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import dao.MySQLDAOFactory;
import dao.TableDAO;

import model.Table;

/**
 * The Class TableFacade.
 */
public class TableFacade {

	/** The instance. */
	private static TableFacade INSTANCE;
	
	/** The current table. */
	private Table currentTable;
	
	/** The current selection. */
	private ArrayList<Table> currentSelection;
	
	/** The tdao. */
	private TableDAO tdao;

	/**
	 * Instantiates a new table facade.
	 */
	private TableFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.tdao = f.getTableDAO();
	}
	
	 /**
 	 * Gets the single instance of TableFacade.
 	 *
 	 * @return single instance of TableFacade
 	 */
 	public static TableFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new TableFacade(); 
	        }   
	        return INSTANCE;
	    }
	 
	 /**
 	 * Read all.
 	 *
 	 * @return the array list
 	 */
 	//Get all the tables
	 public ArrayList<Table> readAll(){
		 this.currentSelection = this.tdao.readAll(); 
		 return this.currentSelection;
	 }
	 
	 /**
 	 * Read all.
 	 *
 	 * @param idRoom the id room
 	 * @return the array list
 	 */
 	//Get all the tables from a room
	 public ArrayList<Table> readAll(int idRoom){
		 this.currentSelection = this.tdao.readAll(idRoom); 
		 return this.currentSelection;
	 }
	 	 
	 /**
 	 * Gets the current selection.
 	 *
 	 * @return the current selection
 	 */
 	public ArrayList<Table> getCurrentSelection(){
		 return this.currentSelection;
	 }
	 
	 /**
 	 * Gets the current table.
 	 *
 	 * @return the current table
 	 */
 	public Table getCurrentTable(){
		 return this.currentTable;
	 }
}
