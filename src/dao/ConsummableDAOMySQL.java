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

    public ArrayList<Consummable> readAll(String category) {
    	ResultSet resultSet;
    	ArrayList<Consummable> result = new ArrayList<Consummable>();
		if (category.isEmpty()) {
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
		} else {
			try {
				resultSet = ConnectionToDB.getInstance().executeQuery("select * from consummable, category where Consummable.idCategory = Category.idCategory and Category.name = '"+category+"'");
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
	public Consummable find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consummable create(Consummable obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consummable update(Consummable obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Consummable obj) {
		// TODO Auto-generated method stub
		
	}



}