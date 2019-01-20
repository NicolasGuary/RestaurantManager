/*
 * @Nathan Traineau
 */
package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Category;
import model.User;
import jdbc.ConnectionToDB;

/**
 * The Class UserDAOMySQL.
 */
public class UserDAOMySQL extends UserDAO {
	
    /** The connect. */
    private Connection connect = null;
    
    /** The statement. */
    private Statement statement = null;
    
    /** The result set. */
    private ResultSet resultSet = null;

    /**
	 * Find.
	 *
	 * @param nick the nick
	 * @param password the password
	 * @return true, if successful
	 */
 	public User find(String username, String password) {
	    	ResultSet resultSet;
	    	User tmp = null;
			try {
				statement = ConnectionToDB.getConnection().createStatement();
				resultSet = statement.executeQuery("select * from user where login = '"+username+"' and password = '"+password+ "'");
				resultSet.first();
				tmp = new User(resultSet.getInt("idUser"),resultSet.getString("user.login"),resultSet.getString("user.lastname"),resultSet.getString("user.firstname"),resultSet.getString("user.password"),resultSet.getBoolean("user.isSuperAdmin") );
		        statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tmp;
	    }
	    
    /**
	 * Find.
	 *
	 * @param idUser, the id of the User
	 * @return an User object
	 */
    	public User find(int idUser) {
	    	ResultSet resultSet;
	    	User tmp = null;
			try {
				statement = ConnectionToDB.getConnection().createStatement();
				resultSet = statement.executeQuery("select * from user where idUser = '"+idUser+ "'");
				resultSet.first();
				tmp = new User(resultSet.getInt("idUser"),resultSet.getString("user.login"),resultSet.getString("user.lastname"),resultSet.getString("user.firstname"),resultSet.getString("user.password"),resultSet.getBoolean("user.isSuperAdmin") );
				statement.close();
			}	
			catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tmp; 
	    }

	  
	    
	    /* (non-Javadoc)
    	 * @see dao.DAO#delete(java.lang.Object)
    	 */
    	public void delete(User user) {
	    	int nbRowsAffected = 0;
			try {
				statement = ConnectionToDB.getConnection().createStatement();
				nbRowsAffected = statement.executeUpdate("delete from user where idUser = '"+user.getIdUser() + "'");
				if(nbRowsAffected == 0){
					throw new SQLException("Deleting user failed.");
			    } 
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            close();
	        }
	    }
	    
	    /**
    	 * Adds the privilege.
    	 *
    	 * @param user the user
    	 * @return true, if successful
    	 */
    	public boolean addPrivilege(User user) {
	    	boolean found = false;
	    	int nbRowsAffected = 0;
			try {
				statement = ConnectionToDB.getConnection().createStatement();
				nbRowsAffected = statement.executeUpdate("update user set isSuperAdmin = 1 where idUser = '"+user.getIdUser() + "'");
				if(nbRowsAffected == 0){
					throw new SQLException("Updating user failed.");
			    } 
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            close();
	        }
			return found;
	    }
    	
    	 /**
    	 * Remove the privilege.
    	 *
    	 * @param user the user
    	 * @return true, if successful
    	 */
    	public boolean removePrivilege(User user) {
	    	boolean found = false;
	    	int nbRowsAffected = 0;
			try {
				statement = ConnectionToDB.getConnection().createStatement();
				nbRowsAffected = statement.executeUpdate("update user set isSuperAdmin = 0 where idUser = '"+user.getIdUser() + "'");
				if(nbRowsAffected == 0){
					throw new SQLException("Updating user failed.");
			    } 
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            close();
	        }
			return found;
	    }
	    

	    /* (non-Javadoc)
    	 * @see dao.DAO#create(java.lang.Object)
    	 */
    	public User create(User user) {
	    	User tmp = null;
				try {
					statement = ConnectionToDB.getConnection().createStatement();
					int nbRowsAffected = statement.executeUpdate("INSERT INTO user (idUser, login, password, firstName, lastName, isSuperAdmin) VALUES (NULL,'"+user.getUsername()+"','"+user.getPassword()+"','"+user.getFirstname()+"','"+user.getLastname()+"',0)",Statement.RETURN_GENERATED_KEYS);
					if(nbRowsAffected >0){
						try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				            if (generatedKeys.next()) {
				            	int userID = generatedKeys.getInt(1);
				            	tmp = new User(userID, user.getUsername() , user.getLastname(), user.getFirstname(), user.getPassword(), false );
				            }
				            else {
				                throw new SQLException("Creating user failed, no ID obtained.");
				            }
				        }
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return tmp;

			} 
	    
    	/**
    	 * Read all users
    	 *
    	 * @return the array list
    	 */
    	public ArrayList<User> readAll() {
	    	ResultSet resultSet;
	    	ArrayList<User> result = new ArrayList<User>();
				try {
					statement = ConnectionToDB.getConnection().createStatement();
					resultSet = statement.executeQuery("select * from user order by isSuperAdmin desc");
					while(resultSet.next()){
						User tmp = new User(resultSet.getInt("idUser"),resultSet.getString("user.login"),resultSet.getString("user.lastname"),resultSet.getString("user.firstname"),resultSet.getString("user.password"),resultSet.getBoolean("user.isSuperAdmin"));
						result.add(tmp);
					}
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return result;
			} 
	    
		/* (non-Javadoc)
		 * @see dao.DAO#update(java.lang.Object)
		 */
		public void update(User user) {
			int nbRowsAffected = 0;
			try {
				statement = ConnectionToDB.getConnection().createStatement();
				nbRowsAffected = statement.executeUpdate("UPDATE User SET firstName ='"+user.getFirstname()+"', lastName = '"+ user.getLastname() +"' password ='"+user.getPassword()+"', WHERE User.idUser = '"+user.getIdUser()+"'");
				if(nbRowsAffected == 0){
					throw new SQLException("Updating user failed.");
			    } 
				statement.close();
			}
			catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
		}
			
	    /**
    	 * Close.
    	 */
    	private void close() {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }

	            if (statement != null) {
	                statement.close();
	            }

	            if (connect != null) {
	                connect.close();
	            }
	        } catch (Exception e) {

	        }
	    }
}