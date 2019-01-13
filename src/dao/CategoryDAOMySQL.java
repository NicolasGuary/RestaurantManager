package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Category;
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
	public Category find(int idCategory) {
		ResultSet resultSet;
    	Category result =null;
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from Category where idCategory = "+idCategory);
			while(resultSet.next()){
				result = new Category(resultSet.getInt("idCategory"), resultSet.getInt("idType"), resultSet.getString("Category.name"));
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

	@Override
	public Category create(Category category) {
		int nbRowsAffected = 0;
		Category res = null;
		int catID = -1;
		try {
			statement = ConnectionToDB.getInstance();
			nbRowsAffected = statement.executeUpdate("INSERT INTO Category (idCategory, idType, name) VALUES (NULL,'"+category.getIdType()+"','"+category.getNameCategory()+"')",Statement.RETURN_GENERATED_KEYS);
			if(nbRowsAffected >0){
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	catID = generatedKeys.getInt(1);
		            	res = new Category(catID,category.getIdType(),category.getNameCategory());
		            }
		            else {
		                throw new SQLException("Creating category failed, no ID obtained.");
		            }
		        }
			}
		} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
	            try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
		return res;
	}

	@Override
	public void update(Category category) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getInstance();
			nbRowsAffected = statement.executeUpdate("UPDATE Category SET name ='"+category.getNameCategory()+"', idType= '"+category.getIdType()+"' WHERE Category.idCategory = '"+category.getIdCategory()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Updating category failed.");
		    } 
			statement.close();
		}
		catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void delete(Category category) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getInstance();
			nbRowsAffected = statement.executeUpdate("DELETE FROM Category WHERE idCategory ='"+category.getIdCategory()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Deleting category failed.");
		    } 
			statement.close();
		}
		catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}