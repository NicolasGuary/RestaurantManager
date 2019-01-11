package dao;

import java.util.ArrayList;

import model.Consummable;

public abstract class ConsummableDAO extends DAO<Consummable> {

	public abstract ArrayList<Consummable> readAll(String category);
	
}
