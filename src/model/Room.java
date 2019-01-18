/*
 * @author Nicolas GUARY
 */
package model;

import java.util.ArrayList;

/**
 * The Class Room.
 */
public class Room {

	/** The id room. */
	private int idRoom;
	
	/** The name. */
	private String name;
	
	/** The tables. */
	private ArrayList<Table> tables;
	
	/** The nb tables. */
	private int nbTables;
	
	/** The with tables. */
	private boolean withTables;
	
	/**
	 * Instantiates a new room.
	 *
	 * @param idRoom the id room
	 * @param name the name
	 * @param tables the tables
	 * @param withTables the with tables
	 */
	public Room(int idRoom, String name, ArrayList<Table> tables, boolean withTables) {
		super();
		this.idRoom = idRoom;
		this.name = name;
		this.tables = tables;
		this.withTables = withTables;
	}

	/**
	 * Instantiates a new room.
	 *
	 * @param idRoom the id room
	 * @param name the name
	 * @param nbTables the nb tables
	 * @param withTables the with tables
	 */
	public Room(int idRoom, String name, int nbTables, boolean withTables) {
		super();
		this.idRoom = idRoom;
		this.name = name;
		this.nbTables=nbTables;
		this.withTables = withTables;
	}
	
	
	/**
	 * Instantiates a new room.
	 *
	 * @param idRoom the id room
	 * @param name the name
	 * @param withTables the with tables
	 */
	//Constructor without any tables
	public Room(int idRoom, String name, boolean withTables) {
		super();
		this.idRoom = idRoom;
		this.name = name;
		this.withTables = withTables;
	}
	
	/**
	 * Gets the id room.
	 *
	 * @return the id room
	 */
	public int getIdRoom() {
		return idRoom;
	}

	/**
	 * Sets the id room.
	 *
	 * @param idRoom the new id room
	 */
	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the nb tables.
	 *
	 * @return the nb tables
	 */
	public int getNbTables() {
		return nbTables;
	}

	/**
	 * Sets the nb tables.
	 *
	 * @param nbTables the new nb tables
	 */
	public void setNbTables(int nbTables) {
		this.nbTables = nbTables;
	}

	/**
	 * Gets the tables.
	 *
	 * @return the tables
	 */
	public ArrayList<Table> getTables() {
		return tables;
	}

	/**
	 * Sets the tables.
	 *
	 * @param tables the new tables
	 */
	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	/**
	 * Checks if is with tables.
	 *
	 * @return true, if is with tables
	 */
	public boolean isWithTables() {
		return withTables;
	}

	/**
	 * Sets the with tables.
	 *
	 * @param withTables the new with tables
	 */
	public void setWithTables(boolean withTables) {
		this.withTables = withTables;
	}
	
	/**
	 * Number tables.
	 *
	 * @return the int
	 */
	public int numberTables() {
		return this.tables.size();
	}
	
	
}
