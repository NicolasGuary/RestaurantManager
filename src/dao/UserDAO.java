package dao;

import java.util.ArrayList;

import model.User;

public abstract class UserDAO extends DAO<User> {

	public abstract boolean find(String nick, String password);

	public abstract ArrayList<User> readAll();
	
}
