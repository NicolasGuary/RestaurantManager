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
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from Consummable");
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
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from Consummable where idCategory = '"+idCategory+"'");
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
	public Consummable find(int idConsummable) {
		ResultSet resultSet;
    	Consummable result = null;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from Consummable where idConsummable = "+idConsummable);
			while(resultSet.next()){
				result = new Consummable(resultSet.getInt("idConsummable"), resultSet.getInt("idCategory"), resultSet.getString("name"), resultSet.getFloat("price"));
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
	public Consummable create(Consummable consummable) {
		int nbRowsAffected = 0;
		Consummable res = null;
		int consID = -1;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("INSERT INTO Consummable (idConsummable, idCategory, name, price) VALUES (NULL,'"+consummable.getIdCategory()+"','"+consummable.getNameConsummable()+"','"+consummable.getPrice()+"')",Statement.RETURN_GENERATED_KEYS);
			if(nbRowsAffected >0){
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	consID = generatedKeys.getInt(1);
		            	res = new Consummable(consID,consummable.getIdCategory(),consummable.getNameConsummable(),consummable.getPrice());
		            }
		            else {
		                throw new SQLException("Creating consummable failed, no ID obtained.");
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
	public void update(Consummable consummable) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("UPDATE Consummable SET name ='"+consummable.getNameConsummable()+"', idCategory= '"+consummable.getIdCategory()+"',price= '"+consummable.getPrice()+"' WHERE Consummable.idConsummable = '"+consummable.getIdConsummable()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Updating consummable failed.");
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
	public void delete(Consummable consummable) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("DELETE FROM Consummable WHERE idConsummable ='"+consummable.getIdConsummable()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Deleting consummable failed.");
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