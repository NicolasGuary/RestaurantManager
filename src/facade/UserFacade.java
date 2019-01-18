/*
 * 
 */
package facade;

import java.util.ArrayList;
import java.util.Observable;

import dao.AbstractDAOFactory;
import dao.MySQLDAOFactory;
import dao.UserDAO;
import model.User;

/**
 * The Class UserFacade.
 */
public class UserFacade extends Observable {

	/** The instance. */
	private static UserFacade INSTANCE;
	
	/** The users list. */
	private ArrayList<User> usersList;
	
	/** The user connected. */
	private User userConnected = new User();
	
	/** The udao. */
	private UserDAO udao;
    
	/**
	 * Instantiates a new user facade.
	 */
	private UserFacade(){
		AbstractDAOFactory f = new MySQLDAOFactory();
    	this.udao = f.getUserDAO();
	}
	
    /**
     * Login.
     *
     * @param login the login
     * @param password the password
     * @return true, if successful
     */
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
    
    /**
     * Gets the single instance of UserFacade.
     *
     * @return single instance of UserFacade
     */
    public static UserFacade getInstance(){
    	if (INSTANCE == null)
        {   
        	INSTANCE = new UserFacade(); 
        }
        
        return INSTANCE;
    }
    
    /**
     * Gets the connected user.
     *
     * @return the connected user
     */
    public User getConnectedUser(){
    	return INSTANCE.userConnected;
    }

    /**
     * Read all users.
     *
     * @return the array list
     */
    public ArrayList<User> readAllUsers(){
		 if(!this.udao.readAll().isEmpty()){
			 this.usersList = this.udao.readAll();
		 }
		 
		 return this.usersList;
	 }
	 
	 /**
 	 * Gets the users list.
 	 *
 	 * @return the users list
 	 */
 	public ArrayList<User> getUsersList(){
		 return this.usersList;
	 
	 }

	/**
	 * Logout.
	 */
	public void logout() {
		userConnected = new User();
        setChanged();
        notifyObservers();
		
	}

	public UserDAO getudao() {
		// TODO Auto-generated method stub
		return this.udao;
	}

	public User create(String string, String string2, String string3,
			String string4, boolean b, boolean c) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPrivilege(int id1) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Integer next) {
		// TODO Auto-generated method stub
		
	}

}

