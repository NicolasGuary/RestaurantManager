/*
 * 
 */
package facade;

import java.util.ArrayList;
import java.util.Observable;

import dao.AbstractDAOFactory;
import dao.CategoryDAO;
import dao.ConsummableDAO;
import dao.MySQLDAOFactory;
import dao.RoomDAO;
import dao.TableDAO;
import dao.TypeDAO;
import model.Type;
import model.Category;
import model.Consummable;
import model.Room;
import model.Table;

/**
 * The Class RoomFacade.
 */
public class RoomFacade {

	/** The instance. */
	private static RoomFacade INSTANCE;
	
	/** The current room. */
	private Room currentRoom;
	
	/** The current selection. */
	private ArrayList<Room> currentSelection;
	
	/** The tables. */
	private ArrayList<Table> tables;
	
	/** The tdao. */
	private TableDAO tdao;
	
	/** The rdao. */
	private RoomDAO rdao;

	/**
	 * Instantiates a new room facade.
	 */
	private RoomFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.tdao = f.getTableDAO();
    	this.rdao = f.getRoomDAO();
	}
	
	 /**
 	 * Gets the single instance of RoomFacade.
 	 *
 	 * @return single instance of RoomFacade
 	 */
 	public static RoomFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new RoomFacade(); 
	        }   
	        return INSTANCE;
	    }
	 
 	/**
 	 * Read all.
 	 *
 	 * @return the array list
 	 */
 	//Get all the rooms
	 public ArrayList<Room> readAll(){
		 this.currentSelection = this.rdao.readAll(); 
		 return this.currentSelection;
	 }
	 	 
	 /**
 	 * Gets the current selection.
 	 *
 	 * @return the current selection
 	 */
 	public ArrayList<Room> getCurrentSelection(){
		 return this.currentSelection;
	 }

	 /**
 	 * Read all tables.
 	 *
 	 * @param idRoom the id room
 	 * @return the array list
 	 */
 	//Get all the Tables from a room
	public ArrayList<Table> readAllTables(int idRoom) {
		this.tables = this.tdao.readAll(idRoom);
		return this.tables;
	}

	/**
	 * Delete.
	 *
	 * @param room the room
	 */
	public void delete(Room room) {
		 this.rdao.delete(room);
	}
	
	/**
	 * Creates the.
	 *
	 * @param room the room
	 */
	public void create(Room room){
		this.rdao.create(room);
	}

}
