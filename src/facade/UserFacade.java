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
    	User user = udao.find(login, password);
    	if(user != null){
    		user.setConnected(true);
    		userConnected = user;
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
		return this.udao;
	}

	public User create(String username,String lastname, String firstname, String password) {
		User user = new User(1, username, lastname, firstname, password, false);
		return this.udao.create(user);
	}

	public boolean addPrivilege(User user) {
		return this.udao.addPrivilege(user);
	}
	public boolean removePrivilege(User user) {
		return this.udao.removePrivilege(user);
	}

	public void delete(User user) {
		this.udao.delete(user);
		
	}

}

