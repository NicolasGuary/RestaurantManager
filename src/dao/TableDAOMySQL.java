/*
 * 
 */
package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Table;
import jdbc.ConnectionToDB;

/**
 * The Class TableDAOMySQL.
 */
public class TableDAOMySQL extends TableDAO {

    /** The connect. */
    private Connection connect = null;
    
    /** The statement. */
    private Statement statement = null;
    
    /** The result set. */
    private ResultSet resultSet = null;
	
    /**
     * Instantiates a new table DAO my SQL.
     */
    public TableDAOMySQL() {
    }

    /* (non-Javadoc)
     * @see dao.TableDAO#readAll()
     */
    public ArrayList<Table> readAll() {
    	ResultSet resultSet;
    	ArrayList<Table> result = new ArrayList<>();
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from Tabl");
			while(resultSet.next()){
				boolean available = resultSet.getInt("available")==0 ?false : true;
				Table tmp = new Table(resultSet.getInt("idTable"),resultSet.getInt("idRoom"),resultSet.getInt("number"),resultSet.getInt("capacity"),resultSet.getInt("maxCapacity"),available);
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
    
    /* (non-Javadoc)
     * @see dao.TableDAO#readAll(int)
     */
    public ArrayList<Table> readAll(int idRoom) {
    	ResultSet resultSet;
    	ArrayList<Table> result = new ArrayList<>();
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from Tabl where idRoom ='"+idRoom+"'");
			while(resultSet.next()){
				boolean available = resultSet.getInt("available")==0 ?false : true;
				Table tmp = new Table(resultSet.getInt("idTable"),resultSet.getInt("idRoom"),resultSet.getInt("number"),resultSet.getInt("capacity"),resultSet.getInt("maxCapacity"),available);
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
    
	/* (non-Javadoc)
	 * @see dao.DAO#find(int)
	 */
	@Override
	public Table find(int idTable) {
		ResultSet resultSet;
    	Table result = null;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("select * from Tabl where idTable = "+idTable);
			while(resultSet.next()){
				boolean available = resultSet.getInt("available")==0 ?false : true;
				result = new Table(resultSet.getInt("idTable"),resultSet.getInt("idRoom"),resultSet.getInt("number"),resultSet.getInt("capacity"),resultSet.getInt("maxCapacity"),available);
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

	/* (non-Javadoc)
	 * @see dao.DAO#create(java.lang.Object)
	 */
	@Override
	public Table create(Table table) {
		int nbRowsAffected = 0;
		Table res = null;
		int availableInt = table.isAvailable() ? 1:0;
		int tableID = -1;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("INSERT INTO Tabl (idTable, idRoom, available, capacity, maxCapacity, number) VALUES (NULL,'"+table.getIdRoom()+"','"+availableInt+"','"+ table.getCapacity() +"','"+table.getMaxCapacity()+"','"+table.getNumber()+"')",Statement.RETURN_GENERATED_KEYS);
			if(nbRowsAffected >0){
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	tableID = generatedKeys.getInt(1);
		            	table.setIdTable(tableID);
		            	res = table;
		            }
		            else {
		                throw new SQLException("Creating table failed, no ID obtained.");
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

	/* (non-Javadoc)
	 * @see dao.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Table table) {
		int nbRowsAffected = 0;
		int availableInt = table.isAvailable() ? 1:0;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("UPDATE Tabl SET available ='"+availableInt+"', capacity= '"+table.getCapacity()+"', maxCapacity='"+ table.getMaxCapacity() +"', idRoom='"+table.getIdRoom()+"',number'"+table.getNumber()+"' WHERE Tabl.idTable = '"+table.getIdTable()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Updating table failed. No rows affected");
		    } 
			statement.close();
		}
		catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/* (non-Javadoc)
	 * @see dao.DAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Table table) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("DELETE FROM Tabl WHERE idTable ='"+table.getIdTable()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Deleting table failed.");
		    } 
			statement.close();
		}
		catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
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

}