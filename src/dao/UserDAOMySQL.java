/*
 * 
 */
package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
     * Instantiates a new user DAO my SQL.
     */
    public UserDAOMySQL() {
    }

    /* (non-Javadoc)
     * @see dao.UserDAO#find(java.lang.String, java.lang.String)
     */
    public boolean find(String nick, String password) {
    	boolean found = false;
    	ResultSet resultSet;
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from user where login = '"+nick+"' and password = '"+password+ "'");
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
    
    /* (non-Javadoc)
     * @see dao.UserDAO#readAll()
     */
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

	/* (non-Javadoc)
	 * @see dao.DAO#find(int)
	 */
	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.DAO#create(java.lang.Object)
	 */
	@Override
	public User create(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see dao.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(User obj) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}


}