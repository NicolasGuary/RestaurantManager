package dao;

import java.util.ArrayList;

import model.Table;

public abstract class TableDAO extends DAO<Table> {

	public abstract ArrayList<Table> readAll();

	public abstract ArrayList<Table> readAll(int idRoom);
	
}
