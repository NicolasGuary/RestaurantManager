package model;


public class User {
    
	private int idUser;
	private String username;
	private String lastname;
	private String firstname;
	private String password;
	private boolean isSuperAdmin = false;
	private boolean isConnected = false; 
	
    public User(){}
    
    public User(int idUser, String username,String lastname, String firstname, String password, boolean isSuperAdmin ){
    	this.setIdUser(idUser);
    	this.setUsername(username);
    	this.setLastname(lastname);
    	this.setFirstname(firstname);
    	this.setPassword(password);
    	this.setSuperAdmin(isSuperAdmin);
    }
    
    public User(String username, boolean isConnected){
    	this.username = username;
    	this.isConnected = isConnected;
    }
    
    public boolean isConnected(){
    	return this.isConnected;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}