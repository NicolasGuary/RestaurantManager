package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionToDB {

	private static Statement statement = null;
	
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
}