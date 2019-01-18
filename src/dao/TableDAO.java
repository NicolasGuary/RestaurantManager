/*
 * @author Nicolas GUARY
 */
package dao;

import java.util.ArrayList;

import model.Table;

/**
 * The Class TableDAO.
 */
public abstract class TableDAO extends DAO<Table> {

	/**
	 * Read all.
	 *
	 * @return the array list
	 */
	public abstract ArrayList<Table> readAll();

	/**
	 * Read all.
	 *
	 * @param idRoom the id room
	 * @return the array list
	 */
	public abstract ArrayList<Table> readAll(int idRoom);
	
}
