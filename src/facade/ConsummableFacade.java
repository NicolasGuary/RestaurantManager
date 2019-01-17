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
import dao.TypeDAO;
import model.Type;
import model.Category;
import model.Consummable;

/**
 * The Class ConsummableFacade.
 */
public class ConsummableFacade {

	/** The instance. */
	private static ConsummableFacade INSTANCE;
	
	/** The current consummable. */
	private Consummable currentConsummable;
	
	/** The types. */
	private ArrayList<Type> types;
	
	/** The categories. */
	private ArrayList<Category> categories;
	
	/** The current selection. */
	private ArrayList<Consummable> currentSelection;
	
	/** The cdao. */
	private ConsummableDAO cdao;
	
	/** The tdao. */
	private TypeDAO tdao;
	
	/** The catdao. */
	private CategoryDAO catdao;
	
	/**
	 * Instantiates a new consummable facade.
	 */
	private ConsummableFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.cdao = f.getConsummableDAO();
    	this.tdao = f.getTypeDAO();
    	this.catdao = f.getCategoryDAO();
	}
	
	 /**
 	 * Gets the single instance of ConsummableFacade.
 	 *
 	 * @return single instance of ConsummableFacade
 	 */
 	public static ConsummableFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new ConsummableFacade(); 
	        }   
	        return INSTANCE;
	    }
	 
	 /**
 	 * Read all.
 	 *
 	 * @return the array list
 	 */
 	public ArrayList<Consummable> readAll(){
		 this.currentSelection = this.cdao.readAll(); 
		 return this.currentSelection;
	 }
	 
	 /**
 	 * Read all.
 	 *
 	 * @param idCategory the id category
 	 * @return the array list
 	 */
 	public ArrayList<Consummable> readAll(int idCategory){
		this.currentSelection = this.cdao.readAll(idCategory);
		return this.currentSelection;
	 }
	 
	 /**
 	 * Read all type.
 	 *
 	 * @return the array list
 	 */
 	public ArrayList<Type> readAllType(){
		 if(!this.tdao.readAll().isEmpty()){
			 this.types = this.tdao.readAll();
		 }
		 return this.types;
	 }
	 
	 /**
 	 * Gets the current selection.
 	 *
 	 * @return the current selection
 	 */
 	public ArrayList<Consummable> getCurrentSelection(){
		 return this.currentSelection;
	 }

	/**
	 * Read all category.
	 *
	 * @param idType the id type
	 * @return the array list
	 */
	public ArrayList<Category> readAllCategory(int idType) {
		this.categories = this.catdao.readAll(idType);
		return this.categories;
	}

}
