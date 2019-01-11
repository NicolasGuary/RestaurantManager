package dao;

import java.util.ArrayList;

import model.Order;

public abstract class OrderDAO extends DAO<Order> {

	public abstract ArrayList<Order> readAll(boolean paid);
	
}
