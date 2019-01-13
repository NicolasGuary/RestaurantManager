package facade;

import java.util.ArrayList;
import java.util.Observable;

import dao.AbstractDAOFactory;
import dao.CategoryDAO;
import dao.ConsummableDAO;
import dao.MySQLDAOFactory;
import dao.RoomDAO;
import dao.TypeDAO;
import model.Type;
import model.Category;
import model.Consummable;
import model.Room;
import model.Table;

public class RoomFacade {

	private static RoomFacade INSTANCE;
	private Room currentRoom;
	private ArrayList<Room> currentSelection;
	private ArrayList<Table> tables;
	private TableDAO tdao;
	private RoomDAO rdao;

	private RoomFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.tdao = f.getTableDAO();
    	this.rdao = f.getRoomDAO();
	}
	
	 public static RoomFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new RoomFacade(); 
	        }   
	        return INSTANCE;
	    }
	 //Get all the rooms
	 public ArrayList<Room> readAll(){
		 this.currentSelection = this.rdao.readAll(); 
		 return this.currentSelection;
	 }
	 	 
	 public ArrayList<Room> getCurrentSelection(){
		 return this.currentSelection;
	 }

	 //Get all the Tables from a room
	public ArrayList<Table> readAllTables(int idRoom) {
		this.tables = this.tdao.readAll(idRoom);
		return this.tables;
	}

}
