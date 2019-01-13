package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;
import jdbc.ConnectionToDB;

public class UserDAOMySQL extends UserDAO {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
	
    public UserDAOMySQL() {
    }

    public boolean isAuthentificationValid(String username, String password) {
    	boolean found = false;
    	ResultSet resultSet;
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from user where login = '"+username+"' and password = '"+password+ "'");
	        found = resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            close();
        }
		return found;
    }
    
    public User find(int idUser) {
    	ResultSet resultSet;
    	User tmp = null;
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from user where idUser = '"+idUser+ "'");
			resultSet.first();
			tmp = new User(resultSet.getInt("idUser"),resultSet.getString("user.login"),resultSet.getString("user.lastname"),resultSet.getString("user.firstname"),resultSet.getString("user.password"),resultSet.getBoolean("user.isSuperAdmin") );
			
		}	
		catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            close();
        }

		//On fait une array avec les donnée recup pour que l'on puisse crée un objet ?
		return tmp; //retourne boolean ou objet ?
    }

  
    
    public boolean delete(int idUser) {
    	boolean found = false;
    	ResultSet resultSet;
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("delete * from user where id = '"+idUser + "'");
	        found = resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            close();
        }
		return found;
    }
    
    public boolean addPrivilege(int idUser) {
    	boolean found = false;
    	ResultSet resultSet;
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("update user set isSuperAdmin = true where id = '"+idUser + "'");
	        found = resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            close();
        }
		return found;
    }
    

    public User create( String login, String password, String firstname, String lastname, boolean isSuperAdmin, boolean isConnected) {
    	ResultSet resultSet;
    	User tmp = null;
			try {
				resultSet = ConnectionToDB.getInstance().executeQuery("Insert into user (login,password,firstName,lastname,isSuperAdmin,isConnected) values"
			            + " ('"
			        
			            + login
			            + "', '"
			            + password
			            + "', '"
			            + firstname
			            + "', '"
			            + lastname
			            + "', '"
			            + isSuperAdmin
			            + "', '"
			            + isConnected
			            + "')");
				
				resultSet.first();
				tmp = new User(resultSet.getInt("idUser"),resultSet.getString("user.login"),resultSet.getString("user.lastname"),resultSet.getString("user.firstname"),resultSet.getString("user.password"),resultSet.getBoolean("user.isSuperAdmin") );			

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            close();
	        }

			return tmp;

		} 
    
    public ArrayList<User> readAll() {
    	ResultSet resultSet;
    	ArrayList<User> result = new ArrayList<User>();
			try {
				resultSet = ConnectionToDB.getInstance().executeQuery("select * from user");
				while(resultSet.next()){
					User tmp = new User(resultSet.getInt("idUser"),resultSet.getString("user.login"),resultSet.getString("user.lastname"),resultSet.getString("user.firstname"),resultSet.getString("user.password"),resultSet.getBoolean("user.isSuperAdmin") );
					result.add(tmp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            close();
	        }
			return result;
		} 
		
    
    
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


	@Override
	public User create(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}

	


}