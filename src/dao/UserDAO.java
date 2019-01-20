/*
 * @Nathan Traineau
 */
package dao;

import java.util.ArrayList;

import model.User;

/**
 * The Class UserDAO.
 */
public abstract class UserDAO extends DAO<User> {

	/**
	 * Find.
	 *
	 * @param nick the nick
	 * @param password the password
	 * @return true, if successful
	 */
	public abstract User find(String nick, String password);

	/**
	 * Read all.
	 *
	 * @return the array list
	 */
	public abstract ArrayList<User> readAll();

	public abstract boolean addPrivilege(User user);
	public abstract boolean removePrivilege(User user);
	
}
