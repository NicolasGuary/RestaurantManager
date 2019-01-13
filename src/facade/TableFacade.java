package facade;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import dao.MySQLDAOFactory;
import dao.TableDAO;

import model.Table;

public class TableFacade {

	private static TableFacade INSTANCE;
	private Table currentTable;
	private ArrayList<Table> currentSelection;
	private TableDAO tdao;

	private TableFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.tdao = f.getTableDAO();
	}
	
	 public static TableFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new TableFacade(); 
	        }   
	        return INSTANCE;
	    }
	 
	 //Get all the tables
	 public ArrayList<Table> readAll(){
		 this.currentSelection = this.tdao.readAll(); 
		 return this.currentSelection;
	 }
	 
	 //Get all the tables from a room
	 public ArrayList<Table> readAll(int idRoom){
		 this.currentSelection = this.tdao.readAll(idRoom); 
		 return this.currentSelection;
	 }
	 	 
	 public ArrayList<Table> getCurrentSelection(){
		 return this.currentSelection;
	 }
	 
	 public Table getCurrentTable(){
		 return this.currentTable;
	 }
}
