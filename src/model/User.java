package model;
import dao.AbstractDAOFactory;
import dao.MySQLDAOFactory;
import dao.UserDAO;


public class User {
    
	private String nick;
	private boolean isConnected = false; 
	
    public User(){}
    
    public User(String nick){
    	this.nick = nick;
    }
    
    public boolean isConnected(){
    	return this.isConnected;
    }

    public User login(String nick, String password) {
    	AbstractDAOFactory f = new MySQLDAOFactory();
    	UserDAO udao = f.getUserDAO();

    	if(udao.find(nick, password)){
    		this.nick = nick;
    		this.isConnected = true;
    		return this;
    	} else {
            return new User();
    	}
    }

}