/*
 * 
 */
package model;


/**
 * The Class User.
 */
public class User {
    
	/** The id user. */
	private int idUser;
	
	/** The username. */
	private String username;
	
	/** The lastname. */
	private String lastname;
	
	/** The firstname. */
	private String firstname;
	
	/** The password. */
	private String password;
	
	/** The is super admin. */
	private boolean isSuperAdmin = false;
	
	/** The is connected. */
	private boolean isConnected = false; 
	
    /**
     * Instantiates a new user.
     */
    public User(){}
    
    /**
     * Instantiates a new user.
     *
     * @param idUser the id user
     * @param username the username
     * @param lastname the lastname
     * @param firstname the firstname
     * @param password the password
     * @param isSuperAdmin the is super admin
     */
    public User(int idUser, String username,String lastname, String firstname, String password, boolean isSuperAdmin ){
    	this.setIdUser(idUser);
    	this.setUsername(username);
    	this.setLastname(lastname);
    	this.setFirstname(firstname);
    	this.setPassword(password);
    	this.setSuperAdmin(isSuperAdmin);
    }
    
    /**
     * Instantiates a new user.
     *
     * @param username the username
     * @param isConnected the is connected
     */
    public User(String username, boolean isConnected){
    	this.username = username;
    	this.isConnected = isConnected;
    }
    
    /**
     * Checks if is connected.
     *
     * @return true, if is connected
     */
    public boolean isConnected(){
    	return this.isConnected;
    }

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the connected.
	 *
	 * @param isConnected the new connected
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	/**
	 * Gets the firstname.
	 *
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Sets the firstname.
	 *
	 * @param firstname the new firstname
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * Gets the lastname.
	 *
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Sets the lastname.
	 *
	 * @param lastname the new lastname
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Checks if is super admin.
	 *
	 * @return true, if is super admin
	 */
	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	/**
	 * Sets the super admin.
	 *
	 * @param isSuperAdmin the new super admin
	 */
	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}