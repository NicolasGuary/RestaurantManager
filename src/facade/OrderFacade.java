/*
 * 
 */
package facade;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import dao.ConsummableDAO;
import dao.MySQLDAOFactory;
import dao.OrderDAO;
import dao.UserDAO;
import model.Consummable;
import model.Order;
import model.Room;
import model.Table;

/**
 * The Class OrderFacade.
 */
public class OrderFacade {

	/** The instance. */
	private static OrderFacade INSTANCE;
	
	/** The current order. */
	private Order currentOrder;
	
	/** The current selection. */
	private ArrayList<Order> currentSelection;
	
	/** The odao. */
	private OrderDAO odao;

	public Order getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}

	/** Updating or creating boolean **/
	private boolean updating = false;
	
	public boolean isUpdating() {
		return updating;
	}

	public void setUpdating(boolean updating) {
		this.updating = updating;
	}
	
	/**
	 * Instantiates a new order facade.
	 */
	private OrderFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.odao = f.getOrderDAO();
	}
	
	 /**
 	 * Gets the single instance of OrderFacade.
 	 *
 	 * @return single instance of OrderFacade
 	 */
 	public static OrderFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new OrderFacade(); 
	        }   
	        return INSTANCE;
	    }
	 
	 /**
 	 * Read all.
 	 *
 	 * @param paid the paid
 	 * @return the array list
 	 */
 	public ArrayList<Order> readAll(boolean paid){
		 if(!this.odao.readAll(paid).isEmpty()){
			 this.currentSelection = this.odao.readAll(paid);
		 } 
		 return this.currentSelection;
	 }
 	
 	/**
 	 * Read.
 	 *
 	 * @return the order
 	 */
 	//Get all the rooms
	 public Order read(Order order){
		 Order result = this.odao.find(order.getIdOrder());
		 return result;
	 }
	 	
	
	/**
	 * Find.
	 *
	 * @param idOrder the id order
	 * @return the order
	 */
	//WARNING: Can return null if not found
	 public Order find(int idOrder) {
		return this.odao.find(idOrder);
	 }
	 
	 /**
 	 * Creates the.
 	 *
 	 * @param order the order
 	 */
 	public void create(Order order) {
		 this.odao.create(order);
	 }
	 
	 /**
 	 * Update.
 	 *
 	 * @param order the order
 	 */
 	public void update(Order order) {
		 this.odao.update(order);
	 }
	 
	 /**
 	 * Delete.
 	 *
 	 * @param order the order
 	 */
 	public void delete(Order order) {
		 this.odao.delete(order);
	 }
	 
	 /**
 	 * Gets the current selection.
 	 *
 	 * @return the current selection
 	 */
 	public ArrayList<Order> getCurrentSelection(){
		 return this.currentSelection;
	 }

	public OrderDAO getOdao() {
		// TODO Auto-generated method stub
		return this.odao;
	}
}
