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
	public Type find(int idType) {
		ResultSet resultSet;
    	Type result =null;
		try {
			resultSet = ConnectionToDB.getInstance().executeQuery("select * from Type where idType = "+idType);
			while(resultSet.next()){
				result = new Type(resultSet.getInt("idType"), resultSet.getString("name"));
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
	public Type create(Type type) {
		int nbRowsAffected = 0;
		Type res = null;
		int typeID = -1;
		try {
			statement = ConnectionToDB.getInstance();
			nbRowsAffected = statement.executeUpdate("INSERT INTO Type (idType, name) VALUES (NULL,'"+type.getNameType()+"')",Statement.RETURN_GENERATED_KEYS);
			if(nbRowsAffected >0){
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	typeID = generatedKeys.getInt(1);
		            	res = new Type(typeID,type.getNameType());
		            }
		            else {
		                throw new SQLException("Creating type failed, no ID obtained.");
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
	public void update(Type type) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getInstance();
			nbRowsAffected = statement.executeUpdate("UPDATE Type SET name ='"+type.getNameType() +"' WHERE Type.idType = '"+type.getIdType()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Updating type failed.");
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
	public void delete(Type type) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getInstance();
			nbRowsAffected = statement.executeUpdate("DELETE FROM Type WHERE idType ='"+type.getIdType()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Deleting type failed.");
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