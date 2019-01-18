/*
 * @author Nicolas GUARY
 */
package dao;

import java.util.ArrayList;

import model.Consummable;

/**
 * The Class ConsummableDAO.
 */
public abstract class ConsummableDAO extends DAO<Consummable> {

	/**
	 * Read all.
	 *
	 * @return the array list
	 */
	public abstract ArrayList<Consummable> readAll();
	
	/**
	 * Read all.
	 *
	 * @param idCategory the id category
	 * @return the array list
	 */
	public abstract ArrayList<Consummable> readAll(int idCategory);
	
}
