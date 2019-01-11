package dao;

import model.User;

public abstract class UserDAO extends DAO<User> {

	public abstract boolean find(String nick, String password);
	
}
