/*
 * @author Nicolas GUARY
 */
package dao;

import java.util.ArrayList;

import model.Room;

/**
 * The Class RoomDAO.
 */
public abstract class RoomDAO extends DAO<Room> {

	/**
	 * Read all.
	 *
	 * @return the array list
	 */
	public abstract ArrayList<Room> readAll();
	
}
