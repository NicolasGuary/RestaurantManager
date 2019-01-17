/*
 * 
 */
package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Room;
import model.Table;
import jdbc.ConnectionToDB;

/**
 * The Class RoomDAOMySQL.
 */
public class RoomDAOMySQL extends RoomDAO {

    /** The connect. */
    private Connection connect = null;
    
    /** The statement. */
    private Statement statement = null;
    
    /** The result set. */
    private ResultSet resultSet = null;
	
    /**
     * Instantiates a new room DAO my SQL.
     */
    public RoomDAOMySQL() {}

    /* (non-Javadoc)
     * @see dao.RoomDAO#readAll()
     */
    //We get all the rooms but we don't need the tables they have (we just display the name of the room and the id to view a specific room)
    public ArrayList<Room> readAll() {
		ArrayList<Room> res = new ArrayList<>();
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("SELECT distinct (Room.idRoom), COUNT(T.idTable) AS nbTable, Room.name, Room.WithTables FROM Room JOIN Tabl T ON T.idRoom = Room.idRoom GROUP BY Room.idRoom;");
			while (resultSet.next()) {
				res.add(new Room(resultSet.getInt("Room.idRoom"), resultSet.getString("Room.name"),resultSet.getInt("nbTable"), resultSet.getInt("Room.WithTables") == 0? false : true));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            close();
        }
	
		return res;
    }
    
	/* (non-Javadoc)
	 * @see dao.DAO#find(int)
	 */
	@Override
	public Room find(int idRoom) {
		ResultSet resultSet;
		Room result = null;
		ArrayList<Table> roomTables = new ArrayList<>();
		try {
			//We search for the room and all the tables included into it
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("SELECT Room.idRoom, Room.name, Room.WithTables, Tabl.idTable,Tabl.capacity,Tabl.maxCapacity,Tabl.number,Tabl.available,Tabl.idRoom\n" + 
					"FROM Room, Tabl \n" + 
					"WHERE Room.idRoom = Tabl.idRoom\n" + 
					"AND Room.idRoom ='"+idRoom + "'");
			
			//If there's at least one row returned, we create a fresh new Room and a new Table, and add the Table into the array of Tables owned by the room. After the iteration we set the Array of Tables to the room
			if(resultSet.next()){
				roomTables.add(new Table(resultSet.getInt("Tabl.idTable"),resultSet.getInt("Tabl.idRoom"), resultSet.getInt("Tabl.number"), resultSet.getInt("Tabl.capacity"), resultSet.getInt("Tabl.maxCapacity"), resultSet.getInt("Tabl.available") == 0? false : true));
				result = new Room(resultSet.getInt("Room.idRoom"), resultSet.getString("Room.name"), resultSet.getInt("Room.WithTable") == 0? false : true);
				//Then we look up for any other Table 
				while(resultSet.next()) {
					roomTables.add(new Table(resultSet.getInt("Tabl.idTable"),resultSet.getInt("Tabl.idRoom"), resultSet.getInt("Tabl.number"), resultSet.getInt("Tabl.capacity"), resultSet.getInt("Tabl.maxCapacity"), resultSet.getInt("Tabl.available") == 0? false : true));
				}
				//And we affect the list of Tables to the order
				result.setTables(roomTables);
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
	public Room create(Room room) {
		int nbRowsAffected = 0;
		int isWithTableInt = room.isWithTables()? 1:0;
		Room res = null;
		int roomID = -1;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("INSERT INTO Room (idRoom, name, WithTables) VALUES ((NULL,'"+room.getName()+"','"+isWithTableInt+"')",Statement.RETURN_GENERATED_KEYS);
			if(nbRowsAffected >0){
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	roomID = generatedKeys.getInt(1);
		            	res = new Room(roomID,room.getName(),room.isWithTables());
		            }
		            else {
		                throw new SQLException("Creating room failed, no ID obtained.");
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
	public void update(Room room) {
		int isWithTableInt = room.isWithTables()? 1:0;
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("UPDATE Room SET name ='"+room.getName()+"', WithTables = '"+ isWithTableInt +"' WHERE Room.idRoom = '"+room.getIdRoom()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Updating room failed.");
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
	public void delete(Room room) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("DELETE FROM Tabl WHERE idRoom ='"+room.getIdRoom()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Deleting room failed.");
		    } 
			statement.close();
		}
		catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("DELETE FROM Room WHERE idRoom ='"+room.getIdRoom()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Deleting room failed.");
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