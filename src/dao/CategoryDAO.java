/*
 * 
 */
package dao;

import java.util.ArrayList;

import model.Category;

/**
 * The Class CategoryDAO.
 */
public abstract class CategoryDAO extends DAO<Category> {

	/**
	 * Read all.
	 *
	 * @param idType the id type
	 * @return the array list
	 */
	public abstract ArrayList<Category> readAll(int idType);
	
}
