package dao;

import java.util.ArrayList;

import model.Category;

public abstract class CategoryDAO extends DAO<Category> {

	/**
	 * @param idType
	 * @return
	 */
	public abstract ArrayList<Category> readAll(int idType);
	
}
