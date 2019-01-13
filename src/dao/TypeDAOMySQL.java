package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Type;
import jdbc.ConnectionToDB;

public class TypeDAOMySQL extends TypeDAO {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
	
    public TypeDAOMySQL() {
    }

    public ArrayList<Type> readAll() {
    	ResultSet resultSet;
    	ArrayList<Type> result = new ArrayList<Type>();
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from Type");
			while(resultSet.next()){
				Type tmp = new Type(resultSet.getInt("idType"),resultSet.getString("Type.name"));
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
	public Type find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type create(Type obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Type obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Type obj) {
		// TODO Auto-generated method stub
		
	}


}