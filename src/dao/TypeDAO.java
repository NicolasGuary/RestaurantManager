/*
 * @author Nicolas GUARY
 */
package dao;

import java.util.ArrayList;

import model.Type;

/**
 * The Class TypeDAO.
 */
public abstract class TypeDAO extends DAO<Type> {

	/**
	 * Read all.
	 *
	 * @return the array list
	 */
	public abstract ArrayList<Type> readAll();
	
}
