package facade;

import java.util.ArrayList;
import java.util.Observable;

import dao.AbstractDAOFactory;
import dao.CategoryDAO;
import dao.ConsummableDAO;
import dao.MySQLDAOFactory;
import dao.TypeDAO;
import model.Type;
import model.Category;
import model.Consummable;

public class ConsummableFacade {

	private static ConsummableFacade INSTANCE;
	private Consummable currentConsummable;
	private ArrayList<Type> types;
	private ArrayList<Category> categories;
	private ArrayList<Consummable> currentSelection;
	private ConsummableDAO cdao;
	private TypeDAO tdao;
	private CategoryDAO catdao;
	
	private ConsummableFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.cdao = f.getConsummableDAO();
    	this.tdao = f.getTypeDAO();
    	this.catdao = f.getCategoryDAO();
	}
	
	 public static ConsummableFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new ConsummableFacade(); 
	        }   
	        return INSTANCE;
	    }
	 
	 public ArrayList<Consummable> readAll(){
		 this.currentSelection = this.cdao.readAll(); 
		 return this.currentSelection;
	 }
	 
	 public ArrayList<Consummable> readAll(int idCategory){
		this.currentSelection = this.cdao.readAll(idCategory);
		return this.currentSelection;
	 }
	 
	 public ArrayList<Type> readAllType(){
		 if(!this.tdao.readAll().isEmpty()){
			 this.types = this.tdao.readAll();
		 }
		 return this.types;
	 }
	 
	 public ArrayList<Consummable> getCurrentSelection(){
		 return this.currentSelection;
	 }

	public ArrayList<Category> readAllCategory(int idType) {
		this.categories = this.catdao.readAll(idType);
		return this.categories;
	}

}
