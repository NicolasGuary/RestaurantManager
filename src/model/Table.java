/*
 * 
 */
package model;

/**
 * The Class Table.
 */
public class Table {

	/** The id table. */
	private int idTable;
	
	/** The id room. */
	private int idRoom;
	
	/** The number. */
	private int number;
	
	/** The capacity. */
	private int capacity;
	
	/** The max capacity. */
	private int maxCapacity;
	
	/** The available. */
	private boolean available;
	
	/**
	 * Instantiates a new table.
	 *
	 * @param idTable the id table
	 * @param idRoom the id room
	 * @param number the number
	 * @param capacity the capacity
	 * @param maxCapacity the max capacity
	 * @param available the available
	 */
	public Table(int idTable,int idRoom,int number, int capacity, int maxCapacity, boolean available) {
		super();
		this.idTable = idTable;
		this.idRoom = idRoom;
		this.number = number;
		this.capacity = capacity;
		this.maxCapacity = maxCapacity;
		this.available = available;
	}

	/**
	 * Gets the id table.
	 *
	 * @return the id table
	 */
	public int getIdTable() {
		return idTable;
	}

	/**
	 * Sets the id table.
	 *
	 * @param idTable the new id table
	 */
	public void setIdTable(int idTable) {
		this.idTable = idTable;
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
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * Gets the capacity.
	 *
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the capacity.
	 *
	 * @param capacity the new capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the max capacity.
	 *
	 * @return the max capacity
	 */
	public int getMaxCapacity() {
		return maxCapacity;
	}

	/**
	 * Sets the max capacity.
	 *
	 * @param maxCapacity the new max capacity
	 */
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	/**
	 * Checks if is available.
	 *
	 * @return true, if is available
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Sets the available.
	 *
	 * @param available the new available
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	
}
