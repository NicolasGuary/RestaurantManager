package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Category;
import model.Type;
import jdbc.ConnectionToDB;

public class CategoryDAOMySQL extends CategoryDAO {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
	
    public CategoryDAOMySQL() {
    }

    public ArrayList<Category> readAll(int idType) {
    	ResultSet resultSet;
    	ArrayList<Category> result = new ArrayList<Category>();
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from Category where idType = "+idType);
			while(resultSet.next()){
				Category tmp = new Category(resultSet.getInt("idCategory"), resultSet.getInt("idType"), resultSet.getString("Category.name"));
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
	public Category find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category create(Category obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category update(Category obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Category obj) {
		// TODO Auto-generated method stub
		
	}
}