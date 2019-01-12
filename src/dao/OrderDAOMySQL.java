package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.text.AttributeSet.CharacterAttribute;

import org.omg.CORBA.StringHolder;

import model.Consummable;
import model.Order;
import model.Table;
import jdbc.ConnectionToDB;

public class OrderDAOMySQL extends OrderDAO {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
	
    public OrderDAOMySQL() {
    }

    public ArrayList<Order> readAll(boolean paid) {
    	ResultSet resultSet;
    	ArrayList<Order> result = new ArrayList<Order>();
		if (paid) {
			try {
				resultSet = ConnectionToDB.getInstance().executeQuery("select distinct Orders.idOrder,Orders.discount, Orders.price, Orders.paid, Orders.note, Tabl.idTable, Tabl.number, Tabl.capacity, Tabl.maxCapacity, Tabl.available\n" + 
						"from Orders, Tabl\n" + 
						"where Orders.idTable = Tabl.idTable\n" + 
						"and Orders.paid = 1");
				
				while(resultSet.next()){
					/*Create cnosummable only on find order
					//ArrayList<Consummable> orderConsummables = new ArrayList<>();
					Consummable newConsummable = new Consummable(resultSet.getInt("Consummable.idConsummable"),resultSet.getInt("Consummable.idCategory"), resultSet.getString("Consummable.name"), resultSet.getFloat("Consummable.price"));
					orderConsummables.add(newConsummable);
					tmp.computePrice();
					*/
					Table orderTable = new Table(resultSet.getInt("Tabl.idTable"), resultSet.getInt("Tabl.number"), resultSet.getInt("Tabl.capacity"), resultSet.getInt("Tabl.maxCapacity"), resultSet.getInt("Tabl.available") == 0? false:true);
					Order tmp = new Order(resultSet.getInt("Orders.idOrder"),resultSet.getFloat("discount"),resultSet.getFloat("Orders.price"),false,resultSet.getString("note"),orderTable);
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
				resultSet = ConnectionToDB.getInstance().executeQuery("select distinct Orders.idOrder,Orders.discount, Orders.price, Orders.paid, Orders.note, Tabl.idTable, Tabl.number, Tabl.capacity, Tabl.maxCapacity, Tabl.available\n" + 
						"from Orders, Tabl\n" + 
						"where Orders.idTable = Tabl.idTable\n" + 
						"and Orders.paid = 0");
				
				while(resultSet.next()){
					/*Create cnosummable only on find order
					ArrayList<Consummable> orderConsummables = new ArrayList<>();
					Consummable newConsummable = new Consummable(resultSet.getInt("Consummable.idConsummable"),resultSet.getInt("Consummable.idCategory"), resultSet.getString("Consummable.name"), resultSet.getFloat("Consummable.price"));
					orderConsummables.add(newConsummable);
					tmp.computePrice();
					*/
					Table orderTable = new Table(resultSet.getInt("Tabl.idTable"), resultSet.getInt("Tabl.number"), resultSet.getInt("Tabl.capacity"), resultSet.getInt("Tabl.maxCapacity"), resultSet.getInt("Tabl.available") == 0? false:true);
					Order tmp = new Order(resultSet.getInt("Orders.idOrder"),resultSet.getFloat("discount"),resultSet.getFloat("Orders.price"),false,resultSet.getString("note"),orderTable);
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
    
    
	//Create a new Order.
	public Order create(float discount, float price, boolean paid, String note,  ArrayList<Consummable> consummablesOrder, Table table) {
		int nbRowsAffected = 0;
		Order res = null;
		int orderID = -1;
		ArrayList<String> queries = new ArrayList<>();
		int paidInt = paid? 0:1;
	
		try {
			statement = ConnectionToDB.getInstance();
			nbRowsAffected = statement.executeUpdate("INSERT INTO Orders (idOrder, discount, price, paid, note, idTable) VALUES (NULL,'"+discount+"','"+price+"','"+ paidInt +"','"+note+"','"+table.getIdTable()+"')",Statement.RETURN_GENERATED_KEYS);
			if(nbRowsAffected >0){
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	orderID = generatedKeys.getInt(1);
		            	res = new Order(orderID,discount,price, paid,note, consummablesOrder, table);
		            }
		            else {
		                throw new SQLException("Creating user failed, no ID obtained.");
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			
			for (Consummable consum: consummablesOrder) {
				String query = "insert into Contains (idConsummable, idOrder) values('"+consum.getIdConsummable() + "','" + orderID + "')";
				queries.add(query);
			}
			
			try {
				statement = ConnectionToDB.getConnection().createStatement();
				for (String query : queries) {
					statement.addBatch(query);
				}
				statement.executeBatch();
				statement.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
		return res;
	}

	@Override
	public Order find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create(Order obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Order obj) {
		// TODO Auto-generated method stub
	}
}