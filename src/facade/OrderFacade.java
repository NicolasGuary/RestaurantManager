package facade;

import java.util.ArrayList;

import dao.AbstractDAOFactory;
import dao.ConsummableDAO;
import dao.MySQLDAOFactory;
import dao.OrderDAO;
import model.Consummable;
import model.Order;
import model.Table;

public class OrderFacade {

	private static OrderFacade INSTANCE;
	private Order currentOrder;
	private ArrayList<Order> currentSelection;
	private OrderDAO odao;
	
	private OrderFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.odao = f.getOrderDAO();
	}
	
	 public static OrderFacade getInstance(){
	    	if (INSTANCE == null)
	        {   
	        	INSTANCE = new OrderFacade(); 
	        }   
	        return INSTANCE;
	    }
	 
	 public ArrayList<Order> readAll(boolean paid){
		 if(!this.odao.readAll(paid).isEmpty()){
			 this.currentSelection = this.odao.readAll(paid);
		 } 
		 return this.currentSelection;
	 }
	 
	 public void create(float discount, float price, boolean paid, String note,  ArrayList<Consummable> consummablesOrder, Table table) {
		 this.odao.create(discount, price, paid, note, consummablesOrder, table);
	 }
	 
	 public ArrayList<Order> getCurrentSelection(){
		 return this.currentSelection;
	 }
}
