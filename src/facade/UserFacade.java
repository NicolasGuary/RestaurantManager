package facade;

import java.util.ArrayList;
import java.util.Observable;

import dao.AbstractDAOFactory;
import dao.MySQLDAOFactory;
import dao.UserDAO;
import model.User;

public class UserFacade extends Observable {

	private static UserFacade INSTANCE;
	private ArrayList<User> usersList;
	private User userConnected = new User();
	
	private UserDAO udao;
    
	private UserFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.udao = f.getUserDAO();
	}
	
    public boolean login(String login, String password){
    	if(udao.find(login, password)){
    		userConnected = new User(login, true);
            setChanged();
            notifyObservers();
    		return true;
    	} else {
            return false;
    	}
    }
    
    public static UserFacade getInstance(){
    	if (INSTANCE == null)
        {   
        	INSTANCE = new UserFacade(); 
        }
        
        return INSTANCE;
    }
    
    public User getConnectedUser(){
    	return INSTANCE.userConnected;
    }

    public ArrayList<User> readAllUsers(){
		 if(!this.udao.readAll().isEmpty()){
			 this.usersList = this.udao.readAll();
		 }
		 
		 return this.usersList;
	 }
	 
	 public ArrayList<User> getUsersList(){
		 return this.usersList;
	 
	 }

	public void logout() {
		userConnected = new User();
        setChanged();
        notifyObservers();
		
	}
}

