package model;

public class Table {

	private int idTable;
	private int number;
	private int capacity;
	private int maxCapacity;
	private boolean available;
	
	public Table(int idTable, int number, int capacity, int maxCapacity, boolean available) {
		super();
		this.idTable = idTable;
		this.number = number;
		this.capacity = capacity;
		this.maxCapacity = maxCapacity;
		this.available = available;
	}

	public int getIdTable() {
		return idTable;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	
}
