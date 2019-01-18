/*
 * @author Nicolas GUARY
 */
package dao;

import java.util.ArrayList;

import model.Consummable;
import model.Order;
import model.Table;

/**
 * The Class OrderDAO.
 */
public abstract class OrderDAO extends DAO<Order> {

	/**
	 * Read all.
	 *
	 * @param paid the paid
	 * @return the array list
	 */
	public abstract ArrayList<Order> readAll(boolean paid);
	
	/* (non-Javadoc)
	 * @see dao.DAO#find(int)
	 */
	public abstract Order find(int idOrder);
	
	/* (non-Javadoc)
	 * @see dao.DAO#create(java.lang.Object)
	 */
	public abstract Order create(Order order);
	
	/* (non-Javadoc)
	 * @see dao.DAO#update(java.lang.Object)
	 */
	public abstract void update(Order order);
	
	/* (non-Javadoc)
	 * @see dao.DAO#delete(java.lang.Object)
	 */
	public abstract void delete(Order order);
	
}
