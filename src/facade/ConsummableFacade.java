package facade;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import dao.ConsummableDAO;
import dao.MySQLDAOFactory;
import model.Consummable;

public class ConsummableFacade {

	private static ConsummableFacade INSTANCE;
	private Consummable currentConsummable;
	private ArrayList<Consummable> currentSelection;
	private ConsummableDAO cdao;
	
	private ConsummableFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.cdao = f.getConsummableDAO();
	}
	
	 public static ConsummableFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new ConsummableFacade(); 
	        }   
	        return INSTANCE;
	    }
	 
	 public ArrayList<Consummable> readAll(String category){
		 if(!this.cdao.readAll(category).isEmpty()){
			 this.currentSelection = this.cdao.readAll(category);
		 }
		 
		 return this.currentSelection;
	 }
	 
	 public ArrayList<Consummable> getCurrentSelection(){
		 return this.currentSelection;
	 }

}
