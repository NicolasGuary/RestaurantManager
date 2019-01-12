package dao;

import java.util.ArrayList;

import model.Consummable;
import model.Order;
import model.Table;

public abstract class OrderDAO extends DAO<Order> {

	public abstract ArrayList<Order> readAll(boolean paid);
	public abstract Order create(Order order);
	
}
