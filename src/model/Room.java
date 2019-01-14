package model;

import java.util.ArrayList;

public class Room {

	private int idRoom;
	private String name;
	private ArrayList<Table> tables;
	private int nbTables;
	private boolean withTables;
	
	public Room(int idRoom, String name, ArrayList<Table> tables, boolean withTables) {
		super();
		this.idRoom = idRoom;
		this.name = name;
		this.tables = tables;
		this.withTables = withTables;
	}

	public Room(int idRoom, String name, int nbTables, boolean withTables) {
		super();
		this.idRoom = idRoom;
		this.name = name;
		this.nbTables=nbTables;
		this.withTables = withTables;
	}
	
	
	//Constructor without any tables
	public Room(int idRoom, String name, boolean withTables) {
		super();
		this.idRoom = idRoom;
		this.name = name;
		this.withTables = withTables;
	}
	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbTables() {
		return nbTables;
	}

	public void setNbTables(int nbTables) {
		this.nbTables = nbTables;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}

	public boolean isWithTables() {
		return withTables;
	}

	public void setWithTables(boolean withTables) {
		this.withTables = withTables;
	}
	
	public int numberTables() {
		return this.tables.size();
	}
	
	
}
