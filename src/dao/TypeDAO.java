package dao;

import java.util.ArrayList;

import model.Type;

public abstract class TypeDAO extends DAO<Type> {

	public abstract ArrayList<Type> readAll();
	
}
