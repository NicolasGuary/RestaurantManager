package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Consummable;
import jdbc.ConnectionToDB;

public class ConsummableDAOMySQL extends ConsummableDAO {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
	
    public ConsummableDAOMySQL() {
    }

    public ArrayList<Consummable> readAll() {
    	ResultSet resultSet;
    	ArrayList<Consummable> result = new ArrayList<Consummable>();
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from Consummable");
			while(resultSet.next()){
				Consummable tmp = new Consummable(resultSet.getInt("idConsummable"),resultSet.getString("Consummable.name"),resultSet.getFloat("price") );
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
    
    public ArrayList<Consummable> readAll(int idCategory) {
    	ResultSet resultSet;
    	ArrayList<Consummable> result = new ArrayList<Consummable>();
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from Consummable where idCategory = '"+idCategory+"'");
			while(resultSet.next()){
				Consummable tmp = new Consummable(resultSet.getInt("idConsummable"),resultSet.getString("Consummable.name"),resultSet.getFloat("price") );
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
	public Consummable find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consummable create(Consummable obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Consummable obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Consummable obj) {
		// TODO Auto-generated method stub
		
	}



}