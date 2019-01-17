/*
 * 
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Class ConnectionToDB.
 */
public class ConnectionToDB {

	/** The statement. */
	private static Statement statement = null;
	
	/** The connection. */
	private static Connection connection = null;
	
    /**
     * Gets the single instance of ConnectionToDB.
     *
     * @return single instance of ConnectionToDB
     * @throws Exception the exception
     */
    public static Statement getInstance() throws Exception{
    	if(statement==null){
        	try {
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                Connection connect = DriverManager
                        .getConnection("jdbc:mysql://db4free.net:3306/restotmanager?"
                                + "user=restotmanager&password=restotmanager");
                // Statements allow to issue SQL queries to the database
                statement = connect.createStatement();
        	} catch (Exception e) {
                throw e;
            }
    	}
    	return statement;
    }
    
    /**
     * Gets the connection.
     *
     * @return the connection
     * @throws Exception the exception
     */
    public static Connection getConnection() throws Exception{
    	if(connection==null){
        	try {
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                connection = DriverManager
                        .getConnection("jdbc:mysql://db4free.net:3306/restotmanager?"
                                + "user=restotmanager&password=restotmanager");
        	} catch (Exception e) {
                throw e;
            }
    	}
    	return connection;
    }
}