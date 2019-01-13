package dao;

import java.util.ArrayList;

import model.User;

public abstract class UserDAO extends DAO<User> {

	public abstract boolean isAuthentificationValid(String username, String password);
	public abstract boolean addPrivilege(int idUser);
	public abstract boolean delete(int idUser);
	public abstract User find(int idUser);
<<<<<<< HEAD
	public abstract User create( String login, String password, String firstname, String lastname, boolean isSuperAdmin, boolean isConnected) ;
=======
	public abstract boolean create(int idUser, String login, String password, String firstname, String lastname, boolean isSuperAdmin, boolean isConnected) ;
>>>>>>> nathan
	public abstract ArrayList<User> readAll();
	
}
