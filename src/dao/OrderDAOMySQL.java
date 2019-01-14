package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
				statement = ConnectionToDB.getConnection().createStatement();
				resultSet = statement.executeQuery("select distinct Orders.idOrder,Orders.discount, Orders.price, Orders.paid, Orders.note, Tabl.idTable, Tabl.idRoom, Tabl.number, Tabl.capacity, Tabl.maxCapacity, Tabl.available\n" + 
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
					Table orderTable = new Table(resultSet.getInt("Tabl.idTable"),resultSet.getInt("Tabl.idRoom"), resultSet.getInt("Tabl.number"), resultSet.getInt("Tabl.capacity"), resultSet.getInt("Tabl.maxCapacity"), resultSet.getInt("Tabl.available") == 0? false:true);
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
				statement = ConnectionToDB.getConnection().createStatement();
				resultSet = statement.executeQuery("select distinct Orders.idOrder,Orders.discount, Orders.price, Orders.paid, Orders.note, Tabl.idTable, Tabl.idRoom, Tabl.number, Tabl.capacity, Tabl.maxCapacity, Tabl.available\n" + 
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
					Table orderTable = new Table(resultSet.getInt("Tabl.idTable"),resultSet.getInt("Tabl.idRoom"), resultSet.getInt("Tabl.number"), resultSet.getInt("Tabl.capacity"), resultSet.getInt("Tabl.maxCapacity"), resultSet.getInt("Tabl.available") == 0? false:true);
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
	public Order create(Order order) {
		int nbRowsAffected = 0;
		Order res = null;
		int orderID = -1;
		ArrayList<String> queries = new ArrayList<>();
		int paidInt = order.isPaid()? 1:0;
	
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("INSERT INTO Orders (idOrder, discount, price, paid, note, idTable) VALUES (NULL,'"+order.getDiscount()+"','"+order.getPrice()+"','"+ paidInt +"','"+order.getNote()+"','"+order.getTable().getIdTable()+"')",Statement.RETURN_GENERATED_KEYS);
			if(nbRowsAffected >0){
				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	orderID = generatedKeys.getInt(1);
		            	res = new Order(orderID,order.getDiscount(),order.getPrice(), order.isPaid(),order.getNote(), order.getConsummablesOrder(), order.getTable());
		            }
		            else {
		                throw new SQLException("Creating order failed, no ID obtained.");
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
			
			for (Consummable consum: order.getConsummablesOrder()) {
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
	public Order find(int idOrder) {
		ResultSet resultSet;
		Order result = null;
		ArrayList<Consummable> orderConsummables = new ArrayList<>();
		Table tableOrder = null;
		try {
			//We look up for all the Order, his Table, and all the Consummables included into it
			statement = ConnectionToDB.getConnection().createStatement();
			resultSet = statement.executeQuery("select Orders.idOrder,Orders.discount, Orders.price, Orders.paid, Orders.note, Tabl.idTable,Tabl.idRoom, Tabl.number, Tabl.capacity, Tabl.maxCapacity, Tabl.available, Consummable.idConsummable, Consummable.name, Consummable.price, Consummable.idCategory\n" + 
					"from Orders, Tabl, Consummable, Contains\n" + 
					"where Tabl.idTable = Orders.idTable\n" + 
					"and Orders.idOrder = Contains.idOrder\n" + 
					"and Contains.idConsummable = Consummable.idConsummable\n" + 
					"and Orders.idOrder = '"+idOrder + "'");
			
			//If there's at least one row returned, we create a new Order and a new Table, and add the Consummable into the array of Consummable
			if(resultSet.next()){
				tableOrder = new Table(resultSet.getInt("Tabl.idTable"),resultSet.getInt("Tabl.idRoom"), resultSet.getInt("Tabl.number"), resultSet.getInt("Tabl.capacity"), resultSet.getInt("Tabl.maxCapacity"), resultSet.getInt("Tabl.available") == 0? false : true);
				result = new Order(resultSet.getInt("Orders.idOrder"),resultSet.getFloat("discount"),resultSet.getFloat("Orders.price"),resultSet.getInt("Orders.paid") == 0? false : true,resultSet.getString("Orders.note"),tableOrder);
				orderConsummables.add(new Consummable(resultSet.getInt("Consummable.idConsummable"),resultSet.getInt("Consummable.idCategory"), resultSet.getString("Consummable.name"), resultSet.getFloat("Consummable.price")));
				//Then we look up for any other Consummable 
				while(resultSet.next()) {
					orderConsummables.add(new Consummable(resultSet.getInt("Consummable.idConsummable"),resultSet.getInt("Consummable.idCategory"), resultSet.getString("Consummable.name"), resultSet.getFloat("Consummable.price")));
				}
				//And we affect the list of Consummables to the order
				result.setConsummablesOrder(orderConsummables);
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
	public void update(Order order) {
		int nbRowsAffected = 0;
		ArrayList<String> queries = new ArrayList<>();
		int paidInt = order.isPaid()? 0:1;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("UPDATE Orders SET discount ='"+order.getDiscount()+"', price= '"+order.getPrice()+"', paid='"+ paidInt +"', note='"+order.getNote()+"',idTable'"+order.getTable().getIdTable()+"' WHERE Orders.idOrder = '"+order.getIdOrder()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Updating order failed.");
		    } 
			statement.close();
		}
		catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		    //We drop all the consummables previously contained by this Order and then we add all the new one
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("DELETE FROM Contains WHERE idOrder = '"+order.getIdOrder()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Updating contains failed.");
		    } 
			statement.close();
		}
		catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			for (Consummable consum: order.getConsummablesOrder()) {
				String query = "insert into Contains (idConsummable, idOrder) values('"+consum.getIdConsummable() + "','" + order.getIdOrder() + "')";
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
	}

	@Override
	public void delete(Order order) {
		int nbRowsAffected = 0;
		try {
			statement = ConnectionToDB.getConnection().createStatement();
			nbRowsAffected = statement.executeUpdate("DELETE FROM Orders WHERE idOrder ='"+order.getIdOrder()+"'");
			if(nbRowsAffected == 0){
				throw new SQLException("Deleting order failed.");
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