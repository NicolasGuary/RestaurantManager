/*
 * 
 */
package dao;

/**
 * The Class DAO.
 *
 * @param <T> the generic type
 */
public abstract class DAO<T> {
	
	/**
	 * Permet de r�cup�rer un objet via son ID.
	 *
	 * @param id the id
	 * @return the t
	 */
	public abstract T find(int id);
	
	/**
	 * Permet de cr�er une entr�e dans la base de donn�es
	 * par rapport � un objet.
	 *
	 * @param obj the obj
	 * @return the t
	 */
	public abstract T create(T obj);
	
	/**
	 * Permet de mettre � jour les donn�es d'une entr�e dans la base .
	 *
	 * @param obj the obj
	 */
	public abstract void update(T obj);
	
	/**
	 * Permet la suppression d'une entr�e de la base.
	 *
	 * @param obj the obj
	 */
	public abstract void delete(T obj);
}