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
    
    public User(String nick, boolean isConnected){
    	this.nick = nick;
    	this.isConnected = isConnected;
    }
    
    public boolean isConnected(){
    	return this.isConnected;
    }

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

}